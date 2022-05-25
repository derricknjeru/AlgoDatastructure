package HashTable;

import java.util.*;

public class GroupShiftedString {
    /**
     * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
     *
     * "abc" -> "bcd" -> ... -> "xyz"
     * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
     *
     * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], Return:
     *
     * [
     *   ["abc","bcd","xyz"],
     *   ["az","ba"],
     *   ["acef"],
     *   ["a","z"]
     * ]
     * Note: For the return value, each inner list's elements must follow the lexicographic order.
     * @param strs
     * @return
     */
    //Explanation https://www.geeksforgeeks.org/group-shifted-string/
    public static ArrayList<ArrayList<String>> groupShiftedStrings(String[] strs) {

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        for(int i = 0; i<strs.length; i++){

            String hashKey = getHashKey(strs[i]); // get the hashkey by getting string with same shifting diff

            if(!map.containsKey(hashKey)){
                map.put(hashKey, new ArrayList<String>());
            }

            map.get(hashKey).add(strs[i]);
        }

        /*ArrayList<ArrayList<String>> result = new ArrayList<>();

        for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()){

            result.add(entry.getValue());
        }*/

        return new ArrayList<>(map.values());
    }

    private static String getHashKey(String s){
        String key = "";
        for(int i = 0; i< s.length()-1; i++){
            int diff = s.charAt(i) - s.charAt(i+1);

            if(diff<0){
                diff +=26;
            }

            key += (diff + 'a'); //convert diff to char
        }

        return key;
    }
}
