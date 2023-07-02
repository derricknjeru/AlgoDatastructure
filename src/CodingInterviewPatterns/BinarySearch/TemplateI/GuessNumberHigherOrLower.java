package CodingInterviewPatterns.BinarySearch.TemplateI;

public class GuessNumberHigherOrLower {
    //https://leetcode.com/problems/guess-number-higher-or-lower/description/


    public static class Solution extends GuessGame {
        //O(log n)
        //space O(1)
        public int guessNumber(int n) {
            int left = 1;
            int right = n;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int pick = guess(mid);

                if (pick == 0) {
                    return mid;
                } else if (pick == 1) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }
    }

    private static class GuessGame {

        // Dummy guess method for demonstration purposes
        int guess(int num) {
            int pickedNumber = 6; // The actual picked number
            return Integer.compare(num, pickedNumber);
        }
    }
}
