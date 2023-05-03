package revision_v2.Interesting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LetterCombinationsOfPhoneNumber {
    //https://leetcode.com/problems/letter-combinations-of-a-phone-number/
    //https://www.youtube.com/watch?v=21OuwqIC56E&t=603s
    //https://www.youtube.com/watch?v=0snEunUacZY

    /**
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
     * <p>
     * A mapping of digits to letters (just like on the telephone buttons) is given below. Note.md that 1 does not map to any letters.
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
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] table = {"0", "1", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};

        letterCombinations(result, digits, "", 0, table);

        return result;

    }

    private void letterCombinations(List<String> result, String digits, String res, int index, String[] table) {

        if (index == digits.length()) {
            result.add(res);
            return;
        }

        String letters = table[digits.charAt(index) - '0'];

        for (int i = 0; i < letters.length(); i++) {
            char c = letters.charAt(i);
            letterCombinations(result, digits, res + c, index + 1, table);
        }


    }

    public List<String> letterCombinations2(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] table = {"0", "1", "abc", "def", "ghi",
                "jkl", "mno", "pqrs", "tuv", "wxyz"};

        letterCombinationsBFS(result, digits, table);

        return result;

    }

    //https://www.geeksforgeeks.org/iterative-letter-combinations-of-a-phone-number/
    private void letterCombinationsBFS(List<String> result, String digits, String[] table) {
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
                    System.out.println(s);
                    q.add(s + letters.charAt(i));
                }
            }
        }
    }
}
