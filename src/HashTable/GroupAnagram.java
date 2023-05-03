package HashTable;

import java.util.*;

public class GroupAnagram {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {

            String sortedString = getSortedString(strs[i]);

            if (map.containsKey(sortedString)) {
                map.get(sortedString).add(strs[i]);
            } else {
                map.put(sortedString, new ArrayList<>());
                map.get(sortedString).add(strs[i]);
            }

        }

        List<List<String>> list = new ArrayList<>();
        list.addAll(map.values()); //Collections.addAll();
        return list;
    }

    private String getSortedString(String str) {
        char array[] = str.toCharArray();
        Arrays.sort(array);
        return new String(array);
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            String sorted_word = getSortedString(str);
            if (!map.containsKey(sorted_word)) {
                map.put(sorted_word, new ArrayList<>());
            }

            map.get(sorted_word).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
