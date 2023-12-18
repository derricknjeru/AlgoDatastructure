package CodingInterviewPatterns.SlidingWindow;

public class LongestRepeatingCharacterReplacement {
    //https://www.youtube.com/watch?v=gqXU1UyA8pk
    //https://leetcode.com/problems/longest-repeating-character-replacement/description/

    /**
     * The time complexity of this algorithm is O(n), where n is the length of the input string.
     * This is because we only iterate through the string once with the right pointer.
     * Each character is processed once, and there are no nested loops or recursive calls.
     * The space complexity is O(1) since we are using a fixed-size array charCount
     * to keep track of character counts, and the other variables used for pointers
     * and counters require constant space regardless of the input size.
     * Please README.md.md that the assumption made in the code is that the input string contains only uppercase English letters.
     * If the input can include lowercase letters or other characters, the code needs to be modified accordingly.
     *
     * @param s
     * @param k
     * @return
     */

    public static int characterReplacement(String s, int k) {
        int[] charCount = new int[26]; // Assuming only uppercase English letters
        int left = 0;
        int maxCount = 0;
        int maxLength = 0;
        int len = s.length();

        for (int right = 0; right < len; right++) {
            // Update the count of the character at the right pointer
            int index = s.charAt(right) - 'A';
            charCount[index]++;
            // Update the maximum count of any character in the current window
            maxCount = Math.max(maxCount, charCount[index]);
            // Calculate the length of the current window
            int windowLength = right - left + 1;

            // If the number of replacements needed exceeds k, move the left pointer to the right
            if (windowLength - maxCount > k) {
                int index2 = s.charAt(left) - 'A';
                charCount[index2]--;
                left++;
            }

            // Update the maximum length of substring with the same repeating character
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2));      // Output: 4
        System.out.println(characterReplacement("AABABBA", 1));  // Output: 4
    }
}
