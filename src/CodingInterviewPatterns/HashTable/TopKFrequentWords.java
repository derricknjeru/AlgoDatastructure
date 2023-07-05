package CodingInterviewPatterns.HashTable;

import java.util.*;

public class TopKFrequentWords {

    class Solution {
        public List<String> topKFrequent(String[] words, int k) {

            HashMap<String, Integer> occurences = new HashMap<>();
            for (String word : words) {
                occurences.put(word, occurences.getOrDefault(word, 0) + 1);
            }

            PriorityQueue<String> minHeap = new PriorityQueue<>(
                    (a, b) -> occurences.get(a) == occurences.get(b) ? b.compareTo(a) : occurences.get(a) - occurences.get(b)
            );

            for (String word : occurences.keySet()) {
                minHeap.add(word);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }


            List<String> result = new ArrayList<>();
            while (!minHeap.isEmpty()) {
                result.add(0, minHeap.poll());
            }

            return result;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> occurrences = new HashMap<>();

        for (String word : words) {
            occurrences.put(word, occurrences.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>
                ((a, b) -> a.getValue() == b.getValue() ?
                        b.getKey().compareTo(a.getKey()) : //b.getKey().compareTo(a.getKey) is preferred as we want to store the word with high alphabetical order above the word with lower alphabetical order
                        a.getValue() - b.getValue());  //min heap


        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            minHeap.add(entry);

            //poll the top item since it's the smallest
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        List<String> ans = new ArrayList<>();

        while (!minHeap.isEmpty())
            ans.add(minHeap.poll().getKey());

        Collections.reverse(ans);

        return ans;
    }
}
