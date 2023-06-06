package CodingInterviewPatterns.HashTable;

import java.util.*;

public class GroupAnagram {

    /**
     * The time complexity of the simplified solution to group anagrams is O(n * k * log(k)),
     * where n is the length of the input array strs and k is the maximum length of a string in strs.
     * <p>
     * Here's the breakdown of the time complexity:
     * <p>
     * Iterating through the input array: O(n)
     * <p>
     * For each string in strs, we perform the following operations.
     * Sorting each string: O(k * log(k))
     * <p>
     * Sorting a string of length k takes O(k * log(k)) time complexity using a comparison-based sorting algorithm like Arrays.sort().
     * Hash map operations: O(1)
     * <p>
     * The hash map operations, such as putIfAbsent(), get(), and add(), generally take constant time on average.
     * Therefore, the overall time complexity is O(n * k * log(k)).
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String key = getSortedString(s);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * The time complexity of the given code is O(n * k), where n is the length of the input array strs and k is the maximum length of a string in strs.
     * <p>
     * Here's the breakdown of the time complexity:
     * <p>
     * Iterating through the input array: O(n)
     * <p>
     * For each string in strs, we perform the following operations.
     * Counting the frequency of characters: O(k)
     * <p>
     * For each string, we iterate through its characters to count the frequency of each character. Since the strings only consist of lowercase English letters, the count array has a fixed size of 26, so the loop has a constant time complexity of O(26) or simply O(1).
     * Constructing the key: O(k)
     * <p>
     * After counting the frequency of characters, we construct a string representation of the count array. This operation takes O(k) time because we iterate through the count array of fixed size 26.
     * Hash map operations: O(1)
     * <p>
     * The hash map operations, such as containsKey(), get(), add(), and put(), generally take constant time on average.
     * Therefore, the overall time complexity is O(n * k), where n is the number of strings in the input array and k is the maximum length of a string.
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] count = new int[26];  // Count array for lowercase English letters

            // Count the frequency of each character in the string
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            // Convert the count array to a string representation
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();

            // If key is already in the map, add the current string to its list
            // Otherwise, create a new entry in the map with key as the key
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        // Convert the values of the map to a list of lists
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            String sortedString = getSortedString(str);

            if (map.containsKey(sortedString)) {
                map.get(sortedString).add(str);
            } else {
                map.put(sortedString, new ArrayList<>());
                map.get(sortedString).add(str);
            }

        }

        return new ArrayList<>(map.values());
    }

    private String getSortedString(String str) {
        char[] array = str.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }


}
