package CodingInterviewPatterns.BinarySearch;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {
    /**
     *
     The time complexity of the solution provided is O(3^N * 4^M), where N is the number of digits in the input string that map to 3 letters ('2', '3', '4', '5', '6', '8') and M is the number of digits in the input string that map to 4 letters ('7', '9').

     In the worst case scenario, where the input string contains only digits that map to 3 letters, the time complexity is O(3^N). This is because for each digit, there are 3 possible letters to consider. Therefore, the total number of combinations will be 3 multiplied by itself N times.

     In the case where the input string contains only digits that map to 4 letters, the time complexity is O(4^M). Similarly, for each digit, there are 4 possible letters to consider, resulting in a total of 4 multiplied by itself M times.

     Since the input string can contain both types of digits, the overall time complexity is the product of the two, resulting in O(3^N * 4^M).

     It's worth noting that the maximum length of the input string is constrained to 4, which means the algorithm will run in a reasonable amount of time even for the worst-case scenario.
     */
    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    //https://www.youtube.com/watch?v=21OuwqIC56E&t=603s
    //https://www.youtube.com/watch?v=0snEunUacZY
    //https://www.youtube.com/watch?v=0snEunUacZY&ab_channel=NeetCode

    /**
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
     * <p>
     * A mapping of digits to letters (just like on the telephone buttons) is given below. Note.md.md.md.md that 1 does not map to any letters.
     * <p>
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: digits = "23"
     * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * Example 2:
     * <p>
     * Input: digits = ""
     * Output: []
     * Example 3:
     * <p>
     * Input: digits = "2"
     * Output: ["a","b","c"]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= digits.length <= 4
     * digits[i] is a digit in the range ['2', '9'].
     *
     * @param digits
     * @return
     */
    /**
     * Time Complexity: O(4^n) as we get set of all possible numbers of length n. In worst case, for each number there can be 4 possibilities.
     * Auxiliary Space: O(4^n)
     *
     * @param
     * @return
     */

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        String digits1 = "23";
        List<String> result1 = solution.letterCombinations(digits1);
        System.out.println(result1); // Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

        // Example 2
        String digits2 = "";
        List<String> result2 = solution.letterCombinations(digits2);
        System.out.println(result2); // Output: []

        // Example 3
        String digits3 = "2";
        List<String> result3 = solution.letterCombinations(digits3);
        System.out.println(result3); // Output: ["a","b","c"]
    }

    static class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> combinations = new ArrayList<>();

            if (digits == null || digits.length() == 0) {
                return combinations;
            }

            Map<Character, String> digitToLetters = new HashMap<>();
            digitToLetters.put('2', "abc");
            digitToLetters.put('3', "def");
            digitToLetters.put('4', "ghi");
            digitToLetters.put('5', "jkl");
            digitToLetters.put('6', "mno");
            digitToLetters.put('7', "pqrs");
            digitToLetters.put('8', "tuv");
            digitToLetters.put('9', "wxyz");

            backtrack(combinations, digitToLetters, digits, new StringBuilder(), 0);

            return combinations;
        }

        private void backtrack(List<String> combinations, Map<Character, String> digitToLetters, String digits, StringBuilder current, int index) {
            if (index == digits.length()) {
                combinations.add(current.toString());
                return;
            }

            char digit = digits.charAt(index);
            String letters = digitToLetters.get(digit);

            for (char letter : letters.toCharArray()) {
                current.append(letter);
                backtrack(combinations, digitToLetters, digits, current, index + 1);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }

   static class Solution2 {
        public List<String> letterCombinations(String digits) {

            List<String> result = new ArrayList<>();

            if(digits==null || digits.length()==0){
                return result;
            }

            String[] table = { "0",   "1",   "abc",  "def", "ghi",
                    "jkl", "mno", "pqrs", "tuv", "wxyz" };

            //a ->d,e f
            //b ->d,e f
            //c ->


            letterCombinations(result,digits,table,"",0);

            return result;

        }

        private void letterCombinations(List<String> result,String digits,String[] table, String s, int index){

            if(index==digits.length()){
                result.add(s);
                return;
            }


            String letters = table[digits.charAt(index) - '0'];

            for(int i = 0; i<letters.length(); i++){
                char c = letters.charAt(i);
                letterCombinations(result,digits,table,s+c,index+1);
            }

        }


    }

    static class Solution3 {
        public List<String> letterCombinations(String digits) {

            List<String> result = new ArrayList<>();

            if (digits == null || digits.length() == 0) {
                return result;
            }

            String[] table = {"0", "1", "abc", "def", "ghi",
                    "jkl", "mno", "pqrs", "tuv", "wxyz"};

            //a ->d,e f
            //b ->d,e f
            //c ->


            letterCombinations(result, digits, table, "", 0);

            return result;

        }

        private void letterCombinations(List<String> result, String digits, String[] table, String x, int indexx) {

            Queue<String> q = new LinkedList<>();
            q.add("");

            int n = digits.length();

            while (!q.isEmpty()) {
                String s = q.remove();

                // If complete word is generated
                // push it in the list
                if (s.length() == n)
                    result.add(s);
                else {
                    int index = digits.charAt(s.length()) - '0';
                    String letters = table[index];
                    for (int i = 0; i < letters.length(); i++) {
                        q.add(s + letters.charAt(i));
                    }
                }
            }

        }


    }
}
