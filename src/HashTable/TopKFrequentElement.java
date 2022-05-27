package HashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElement {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int a : nums){
            map.put(a, map.getOrDefault(a,0)+1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a, b)->a.getValue()-b.getValue());

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){

            pq.add(entry);

            if(pq.size() > k){
                pq.poll();
            }
        }

        int[] result = new int[pq.size()];
        int index=0;

        while(!pq.isEmpty()){
            result[index++] = pq.poll().getKey();
        }

        return result;

    }
}
