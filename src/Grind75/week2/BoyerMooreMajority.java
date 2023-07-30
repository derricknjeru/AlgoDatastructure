package Grind75.week2;

public class BoyerMooreMajority {
    public static void main(String[] args) {

    }

    public int majorityElement(int[] nums) {
        /**
         * Time complexity O(n)
         * Space complexity O(n)
         */
        //https://leetcode.com/problems/majority-element/description/
        //https://www.youtube.com/watch?v=gY-I8uQrCkk
        //https://leetcode.com/problems/majority-element/editorial/
        //https://www.geeksforgeeks.org/boyer-moore-majority-voting-algorithm/
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
