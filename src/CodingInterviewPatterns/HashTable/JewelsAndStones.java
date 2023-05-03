package CodingInterviewPatterns.HashTable;

import java.util.HashMap;

public class JewelsAndStones {
    /**
     * You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.
     *
     * Letters are case sensitive, so "a" is considered a different type of stone from "A".
     *
     *
     *
     * Example 1:
     *
     * Input: jewels = "aA", stones = "aAAbbbb"
     * Output: 3
     * Example 2:
     *
     * Input: jewels = "z", stones = "ZZ"
     * Output: 0
     *
     *
     * Constraints:
     *
     * 1 <= jewels.length, stones.length <= 50
     * jewels and stones consist of only English letters.
     * All the characters of jewels are unique.
     * @param jewels
     * @param stones
     * @return
     */

    //https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1136/

    public int numJewelsInStones(String jewels, String stones) {

        HashMap<Character,Integer> stonesMap = new HashMap<>();

        for(char c : stones.toCharArray()){
            stonesMap.put(c,stonesMap.getOrDefault(c,0)+1);
        }

        int sum = 0;

        for(int i = 0; i<jewels.length(); i++){

            char c = jewels.charAt(i);

            if(stonesMap.containsKey(c)){
                sum += stonesMap.get(c);
            }

        }

        return sum;

    }
}
