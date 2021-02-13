package revision.strings_and_arrays;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        // String[] strs = {"dog","racecar","car"};
        LongestCommonPrefix l = new LongestCommonPrefix();
        System.out.println("The answer is::" + l.longestCommonPrefix(strs));
    }

    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     * Example 2:
     * <p>
     * Input: strs = ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] consists of only lower-case English letters.
     */

    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) return "";
   ///["flower","flow","flight"]
        for (int i = 0; i < strs[0].length(); i++) {

            char c = strs[0].charAt(i); //f   l    o  wer

            System.out.println("Character on String first is " + c);

            for (int j = 1; j < strs.length; j++) {

                System.out.printf("String at %d is %s", i, strs[j]);
                 //f l o  w
                //f  l i ght
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}
