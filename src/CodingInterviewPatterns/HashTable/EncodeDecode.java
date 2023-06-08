package CodingInterviewPatterns.HashTable;

import java.util.ArrayList;
import java.util.List;

public class EncodeDecode {
    /**
     * Design an algorithm to encode a list of strings to a single string. The encoded string is then sent over the network and decoded back to the original list of strings.
     *
     * Implement to encode and decode methods to accomplish this task.
     *
     * Example:
     *
     * Example 1:
     * Input: ["lint", "code", "love", "you"]
     * Output: ["lint", "code", "love", "you"]
     * Explanation: One possible encode method is: "lint:;code:;love:;you"
     *
     * Example 2:
     * Input: ["we", "say", ":", "yes"]
     * Output: ["we", "say", ":", "yes"]
     * Explanation: One possible encode method is: "we:;say:;:::;yes"
     *
     * Tags: string manipulation, encoding, decoding, networking
     */


    /**
     * Encodes a list of strings to a single string.
     *
     * @param strs: a list of strings
     * @return: encoded string
     */
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append(":;").append(s);
        }
        return sb.toString();
    }

    /**
     * Decodes a single string to a list of strings.
     *
     * @param s: encoded string
     * @return: list of decoded strings
     */
    public static List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (s.charAt(j) != ':' || s.charAt(j + 1) != ';') {
                j++;
            }
            int length = Integer.parseInt(s.substring(i, j));
            res.add(s.substring(j + 2, j + 2 + length));
            i = j + 2 + length;
        }
        return res;
    }

    public static void main(String[] args) {


        // Example usage
        List<String> input1 = new ArrayList<>();
        input1.add("lint");
        input1.add("code");
        input1.add("love");
        input1.add("you");
        String encoded1 = encode(input1);
        System.out.println("Encoded string: " + encoded1);
        List<String> decoded1 = decode(encoded1);
        System.out.println("Decoded list: " + decoded1);

        List<String> input2 = new ArrayList<>();
        input2.add("we");
        input2.add("say");
        input2.add(":");
        input2.add("yes");
        String encoded2 = encode(input2);
        System.out.println("Encoded string: " + encoded2);
        List<String> decoded2 = decode(encoded2);
        System.out.println("Decoded list: " + decoded2);
    }


}
