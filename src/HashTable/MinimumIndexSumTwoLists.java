package HashTable;

import java.util.*;

public class MinimumIndexSumTwoLists {
    public static void main(String[] args) {

    }

    //https://leetcode.com/problems/minimum-index-sum-of-two-lists/description/
    //https://leetcode.com/problems/minimum-index-sum-of-two-lists/editorial/

    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    if (!map.containsKey(i + j))
                        map.put(i + j, new ArrayList<String>());
                    map.get(i + j).add(list1[i]);
                }
            }
        }
        int min_index_sum = Integer.MAX_VALUE;
        for (int key : map.keySet())
            min_index_sum = Math.min(min_index_sum, key);

        return map.get(min_index_sum).toArray(new String[0]);
    }

    public String[] findRestaurant2(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        HashMap<Integer, List<String>> mapRes = new HashMap<>();

        for (int i = 0; i < list2.length; i++) {
            String word = list2[i];
            if (map.containsKey(word)) {
                int sum = i + map.get(word);
                if (!mapRes.containsKey(sum)) {
                    mapRes.put(sum, new ArrayList<>());
                }
                mapRes.get(sum).add(word);
            }
        }


        int min_index_sum = Integer.MAX_VALUE;
        for (int key : mapRes.keySet())
            min_index_sum = Math.min(min_index_sum, key);

        return mapRes.get(min_index_sum).toArray(new String[0]);

    }

    public String[] findRestaurant3(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }

        HashMap<Integer, List<String>> mapRes = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < list2.length; i++) {
            String word = list2[i];
            if (map.containsKey(word)) {
                int sum = i + map.get(word);
                if (!mapRes.containsKey(sum)) {
                    mapRes.put(sum, new ArrayList<>());
                }
                mapRes.get(sum).add(word);
            }
        }
        minHeap.addAll(mapRes.keySet());
        //Iterator i = minHeap.iterator();
        //int index = (int)i.next();
        int index = minHeap.peek();


        return mapRes.get(index).toArray(new String[0]);

    }


}
