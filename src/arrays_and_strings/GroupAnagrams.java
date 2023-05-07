package arrays_and_strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class GroupAnagrams {
    /**
     * Given an array of strings, group anagrams together.
     * <p>
     * Example:
     * <p>
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * Note.md.md:
     * <p>
     * All inputs will be in lowercase.
     * The order of your output does not matter.
     */

    public static void main(String[] args) {
        GroupAnagrams anagrams = new GroupAnagrams();
        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        //String[] arr2 = {"",""};

        GroupAnagrams gp = new GroupAnagrams();
        System.out.println(gp.groupAnagrams(arr));

        //https://www.youtube.com/watch?v=ptgykfAEax8&t=1s

    }

    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] character = str.toCharArray();
            Arrays.sort(character);
            String sorted_word = new String(character);
            if (!map.containsKey(sorted_word)) {
                map.put(sorted_word, new ArrayList<>());
            }

            map.get(sorted_word).add(str);
        }

        List<List<String>> result = new ArrayList<>(map.values());

        return result;
    }


    public List<List<String>> groupAnagrams2(String[] strs) {

        if (strs.length == 0) {
            return new ArrayList<>();
        }

        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);

            String newString = new String(arr);

            if (!map.containsKey(newString)) {
                map.put(newString, new ArrayList<>());
            }

            map.get(newString).add(s);
        }

        return new ArrayList<>(map.values());

    }


    public List<List<String>> groupAnagrams3(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        List<List<String>> result = new ArrayList<List<String>>();

        for (String s : strs) {

            String sortedString = sort(s);

            if (map.containsKey(sortedString)) {
                List list = map.get(sortedString);
                list.add(s);
                map.put(sortedString, list);

            } else {
                List list = new ArrayList<>();
                list.add(s);
                map.put(sortedString, list);
            }

        }

        return new ArrayList<List<String>>(map.values());

    }

    private String sort(String s) {
        char arr[] = s.toCharArray();

        Arrays.sort(arr);

        return new String(arr);

    }


}
