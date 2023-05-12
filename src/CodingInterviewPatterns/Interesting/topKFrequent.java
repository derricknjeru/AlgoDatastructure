package CodingInterviewPatterns.Interesting;

import java.util.*;

public class topKFrequent {
    //https://www.geeksforgeeks.org/priority-queue-class-in-java/
    //https://leetcode.com/problems/top-k-frequent-words/discuss/2192423/Java-Solution-or-Hash-Map-and-Priority-Queue-or-min-heap
    public List<String> topKFrequent4(String[] words, int k) {

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

    public List<String> topKFrequent2(String[] words, int k) {

        Map<String, Integer> occurrences = new HashMap<>();

        for (String word : words) {
            occurrences.put(word, occurrences.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>
                ((a, b) -> a.getValue() == b.getValue() ?
                        a.getKey().compareTo(b.getKey()) : //a.getKey().compareTo(b.getKey) is preferred as we want to store the word with high alphabetical order above the word with lower alphabetical order
                        b.getValue() - a.getValue());  //min heap


        for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
            maxHeap.add(entry);
        }

        List<String> ans = new ArrayList<>();

        int index = 0;

        while (!maxHeap.isEmpty() && index++ < k)
            ans.add(maxHeap.poll().getKey());


        return ans;
    }

    public List<String> topKFrequent3(String[] words, int k) {
        HashMap<String, Integer> occ = new HashMap<>();
        for (String word : words) {
            occ.put(word, occ.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());

        maxHeap.addAll(occ.entrySet());

        List<String> result = new ArrayList<>();

        int i = 1;
        while (!maxHeap.isEmpty() && i <= k) {
            Map.Entry<String, Integer> entry = maxHeap.poll();
            result.add(entry.getKey());
            i++;
        }

        return result;

    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> occ = new HashMap<>();
        for (String word : words) {
            occ.put(word, occ.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> maxHeap = new PriorityQueue<>((a, b) -> occ.get(a) == occ.get(b) ? a.compareTo(b) : occ.get(b) - occ.get(a));

        maxHeap.addAll(occ.keySet());

        List<String> result = new ArrayList<>();

        int i = 1;
        while (!maxHeap.isEmpty() && i <= k) {
            result.add(maxHeap.poll());
            i++;
        }

        return result;

    }
}
