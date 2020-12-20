package revision.strings_and_arrays;

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
     * Note:
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


}
