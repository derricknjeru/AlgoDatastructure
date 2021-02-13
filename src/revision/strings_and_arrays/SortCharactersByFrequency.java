package revision.strings_and_arrays;

import java.util.*;
import java.util.stream.Collectors;

public class SortCharactersByFrequency {
    public static void main(String[] args) {
       // https://leetcode.com/problems/sort-characters-by-frequency/
    }


    public String frequencySort(String s) {

        if (s.length() == 0) return "";

        char[] arr = s.toCharArray();

        HashMap<Character, Integer> occurences = new HashMap<>();


        for (char c : arr) {
            occurences.put(c, occurences.getOrDefault(c, 0) + 1);
        }


        //sort the map by value

        //Add it to ArrayList and Use collections.sort function

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(occurences.entrySet());

        //Collections.sort(list, (x, y) -> y.getValue() - x.getValue());
        list.sort((x, y) -> y.getValue() - x.getValue());

        //Loop through the sorted list
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Character, Integer> m : list) {
            char c = m.getKey();
            int count = m.getValue();

            for (int i = 0; i < count; i++) {
                sb.append(c);
            }

        }

        return sb.toString();

    }

    public String frequencySort2(String s) {
        if (s.length() == 0) return "";

        char[] arr = s.toCharArray();

        HashMap<Character, Integer> occurences = new HashMap<>();


        for (char c : arr) {
            occurences.put(c, occurences.getOrDefault(c, 0) + 1);
        }

        //sorting using Stream API
        StringBuilder sb = new StringBuilder();
        occurences.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(m -> {
                    char c = m.getKey();
                    int count = m.getValue();

                    for (int i = 0; i < count; i++) {
                        sb.append(c);
                    }

                });

        return sb.toString();

    }

    public String frequencySort3(String s) {
        if (s.length() == 0) return "";

        char[] arr = s.toCharArray();

        HashMap<Character, Integer> occurences = new HashMap<>();


        for (char c : arr) {
            occurences.put(c, occurences.getOrDefault(c, 0) + 1);
        }


        //sorting using Stream API
        StringBuilder sb = new StringBuilder();

        List<Map.Entry<Character, Integer>> list = occurences.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        for (Map.Entry<Character, Integer> m : list) {
            char c = m.getKey();
            int count = m.getValue();

            for (int i = 0; i < count; i++) {
                sb.append(c);
            }

        }

        return sb.toString();

    }

    public String frequencySort4(String s) {
        if (s.length() == 0) return "";

        char[] arr = s.toCharArray();

        HashMap<Character, Integer> occurences = new HashMap<>();


        for (char c : arr) {
            occurences.put(c, occurences.getOrDefault(c, 0) + 1);
        }

        //sorting using Stream API
        StringBuilder sb = new StringBuilder();

        //create a maxheap using priorityQueue
        PriorityQueue<Character> maxHeap = new PriorityQueue<>
                ((a, b) -> occurences.get(b) - occurences.get(a));
        maxHeap.addAll(occurences.keySet());

        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            System.out.println(c);
            int count = occurences.get(c);
            for (int i = 0; i < count; i++) {
                sb.append(c);
            }
        }

        return sb.toString();

    }

    public String frequencySort8(String s) {
        var map = new HashMap<Character, Integer>();

        for (char c : s.toCharArray()) map.put(c, 1 + map.getOrDefault(c, 0));

        var pq = new PriorityQueue<Character>((a, b) -> map.get(b) - map.get(a));

        map.forEach((key, value) -> pq.add(key));

        var res = new StringBuilder();
        while (!pq.isEmpty()) {
            char c = pq.poll();
            int freq = map.get(c);
            while (freq-- > 0) res.append(c);
        }
        return res.toString();
    }


    public String frequencySort10(String s) {
        //count the freq of all chars
        int[] count = new int[128];
        for (int value : s.toCharArray()) count[value]++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            int max = findMax(count); //call the method that finds where the max index is
            if (count[max] == 0) break;
            while (count[max] > 0) {  //decrement the count char by char
                sb.append((char) (max));
                count[max]--;
            }
        }
        return sb.toString();
    }

    //find where the max index is
    public int findMax(int[] arr) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++)
            if (max < arr[i]) {
                index = i;
                max = arr[i];
            }
        return index;
    }


    public String frequencySort11(String s) {
        int[] freq = new int[256];
        for (char ch : s.toCharArray()) freq[ch]++;

        TreeMap<Integer, List<Character>> tree = new TreeMap<>();

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                if (!tree.containsKey(freq[i])) {
                    tree.put(freq[i], new LinkedList<>());
                }
                tree.get(freq[i]).add((char) i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (tree.size() > 0) {
            Map.Entry<Integer, List<Character>> entry = tree.pollLastEntry();
            for (Character ch : entry.getValue()) {
                sb.append(new String(new char[entry.getKey()]).replace('\0', ch));
            }
        }
        return sb.toString();
    }

    public String frequencySort12(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        List<Character>[] bucket = new List[s.length() + 1];
        for (char key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null) {
                for (char num : bucket[pos]) {
                    for (int i = 0; i < map.get(num); i++) {
                        sb.append(num);
                    }
                }
            }
        }
        return sb.toString();
    }


    public String frequencySort7(String s) {

        Map<Character, Integer> charToCount = new HashMap<>();

        for (char c : s.toCharArray()) charToCount.merge(c, 1, Integer::sum);

        StringBuilder sb = new StringBuilder(s.length());
        charToCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .forEachOrdered(e -> sb.append(e.getKey().toString().repeat(e.getValue())));

        return sb.toString();
    }


    public String frequencySort9(String s) {
        int[] freq = new int[256];
        for (char ch : s.toCharArray()) freq[ch]++;

        TreeMap<Integer, List<Character>> tree = new TreeMap<>();

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                if (!tree.containsKey(freq[i])) {
                    tree.put(freq[i], new LinkedList<>());
                }
                tree.get(freq[i]).add((char) i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (tree.size() > 0) {
            Map.Entry<Integer, List<Character>> entry = tree.pollLastEntry();
            for (Character ch : entry.getValue()) {
                sb.append(new String(new char[entry.getKey()]).replace('\0', ch));
            }
        }
        return sb.toString();

    }

    public String frequencySort13(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();

        // Count frequency
        for (int i = 0; i < s.length(); ++i) {
            Character c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        List<Character>[] buckets = new ArrayList[n + 1]; // or 256

        // Build buckets
        for (char c : map.keySet()) {
            int count = map.get(c);
            if (buckets[count] == null) {
                buckets[count] = new ArrayList<>();
            }
            buckets[count].add(c);
        }

        // Build output string
        StringBuilder sb = new StringBuilder();

        for (int i = n; i >= 0; --i) {
            if (buckets[i] != null) {
                continue;
            }
            assert buckets[i] != null;
            for (char c : buckets[i]) {
                int count = i;
                while (count > 0) {
                    sb.append(c);
                    count -= 1;
                }
            }
        }
        return sb.toString();
    }

}
