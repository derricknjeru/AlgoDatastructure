package CodingInterviewPatterns.Interesting;

import java.util.HashMap;
import java.util.PriorityQueue;

public class ReorganizeString {
    /**
     * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
     * <p>
     * Return any possible rearrangement of s or return "" if not possible.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "aab"
     * Output: "aba"
     * Example 2:
     * <p>
     * Input: s = "aaab"
     * Output: ""
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= s.length <= 500
     * s consists of lowercase English letters.
     */

    public String reorganizeString1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));

        maxHeap.addAll(map.keySet());

        StringBuilder res = new StringBuilder();


        while (maxHeap.size() > 1) {
            char current = maxHeap.remove();
            char next = maxHeap.remove();

            res.append(current);
            res.append(next);

            map.put(current, map.get(current) - 1);
            map.put(next, map.get(next) - 1);

            if (map.get(current) > 0) {
                maxHeap.add(current);
            }

            if (map.get(next) > 0) {
                maxHeap.add(next);
            }

        }

        if (!maxHeap.isEmpty()) {
            char last = maxHeap.remove();

            if (map.get(last) > 1) return "";
            res.append(last);
        }

        return res.toString();

    }

    public String reorganizeString(String s) {
        int[] map = new int[26];
        for(char c : s.toCharArray()){
            map[c-'a']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->map[b] - map[a]);

        for(int i=0; i<26; i++) {
            if(map[i] > 0) {
                maxHeap.offer(i);
            }
        }

        StringBuilder res = new StringBuilder();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";


        while(maxHeap.size() > 1){
            int currentIndex = maxHeap.remove();
            int nextIndex = maxHeap.remove();

            res.append(alphabet.charAt(currentIndex));
            res.append(alphabet.charAt(nextIndex));

            map[currentIndex]--;
            map[nextIndex]--;

            if(map[currentIndex]>0){
                maxHeap.add(currentIndex);
            }

            if(map[nextIndex]>0){
                maxHeap.add(nextIndex);
            }

        }

        if(!maxHeap.isEmpty()){
            int lastIndex = maxHeap.remove();

            if(map[lastIndex]>1) return "";
            res.append(alphabet.charAt(lastIndex));
        }

        return res.toString();

    }



    public static void main(String[] args) {
        char c = 'b';
        int test[]=new int[2];
        test[c-'a']++;
        System.out.println((c - 'a'));

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(alphabet.charAt(2));
    }
}
