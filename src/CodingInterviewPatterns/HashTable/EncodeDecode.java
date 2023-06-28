package CodingInterviewPatterns.HashTable;

import java.util.ArrayList;
import java.util.List;

public class EncodeDecode {
    /**
     * Design an algorithm to encode a list of strings to a single string. The encoded string is then sent over the network and decoded back to the original list of strings.
     * <p>
     * Implement to encode and decode methods to accomplish this task.
     * <p>
     * Example:
     * <p>
     * Example 1:
     * Input: ["lint", "code", "love", "you"]
     * Output: ["lint", "code", "love", "you"]
     * Explanation: One possible encode method is: "lint:;code:;love:;you"
     * <p>
     * Example 2:
     * Input: ["we", "say", ":", "yes"]
     * Output: ["we", "say", ":", "yes"]
     * Explanation: One possible encode method is: "we:;say:;:::;yes"
     * <p>
     * Tags: string manipulation, encoding, decoding, networking
     */

    static class Solution {

        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for (String s : strs) {
                sb.append(s.length()).append('#').append(s);
            }
            return sb.toString();
        }

        public List<String> decode(String str) {
            List<String> ans = new ArrayList<>();
            int i = 0;
            while (i < str.length()) {
                int j = i;
                while (str.charAt(j) != '#') j++;
                int len = Integer.parseInt(str.substring(i, j));
                //move i
                i = j + 1 + len;
                ans.add(str.substring(j + 1, i));
            }
            return ans;
        }

        //if it is only letters

        /**public List<String> decode(String str) {
         List<String> ans = new ArrayList<>();
         int i = 0;
         // int j = 0;
         while (i < str.length()) {
         int j = i;
         while (j < str.length() && !Character.isLetter(str.charAt(j))) {
         j++;
         i++;
         }
         while (j < str.length() && Character.isLetter(str.charAt(j))) {
         j++;
         }
         i = j + 1;
         }
         return ans;
         }**/
    }


    public static void main(String[] args) {

        // Example usage
        List<String> input1 = new ArrayList<>();
        input1.add("lint");
        input1.add("code");
        input1.add("love");
        input1.add("you");
        Solution en = new Solution();
        String encoded1 = en.encode(input1);
        System.out.println("Encoded string: " + encoded1);
        List<String> decoded1 = en.decode(encoded1);
        System.out.println("Decoded list: " + decoded1);

        List<String> input2 = new ArrayList<>();
        input2.add("we");
        input2.add("say");
        input2.add(":");
        input2.add("yes");
        String encoded2 = en.encode(input2);
        System.out.println("Encoded string: " + encoded2);
        List<String> decoded2 = en.decode(encoded2);
        System.out.println("Decoded list: " + decoded2);
    }


}
