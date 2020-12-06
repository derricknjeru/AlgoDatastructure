package revision.strings_and_arrays;

import java.util.*;
import java.util.stream.Collectors;

public class SortCharactersByFrequency {
    public static void main(String[] args) {

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
}
