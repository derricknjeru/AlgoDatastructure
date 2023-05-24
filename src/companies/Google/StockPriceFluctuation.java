package companies.Google;

import java.util.PriorityQueue;
import java.util.TreeMap;

public class StockPriceFluctuation {
    //https://leetcode.com/problems/stock-price-fluctuation/solutions/1513303/java-2-heaps/
    TreeMap<Integer, Integer> map;
    PriorityQueue<int[]> min = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
    PriorityQueue<int[]> max = new PriorityQueue<int[]>((a, b) -> b[1] - a[1]);


    public StockPriceFluctuation() {
        map = new TreeMap<>();

    }

    public void update(int timestamp, int price) {
        map.put(timestamp, price);
        min.add(new int[]{timestamp, price});
        max.add(new int[]{timestamp, price});
    }

    public int current() {
        return map.lastEntry().getValue();
    }

    public int maximum() {
        while (map.get(max.peek()[0]) != max.peek()[1]) {
            max.poll();
        }
        return max.peek()[1];
    }

    public int minimum() {
        while (map.get(min.peek()[0]) != min.peek()[1]) {
            min.poll();
        }
        return min.peek()[1];
    }
}
