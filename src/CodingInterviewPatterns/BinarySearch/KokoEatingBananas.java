package CodingInterviewPatterns.BinarySearch;

import java.util.Arrays;

public class KokoEatingBananas {
    //https://www.youtube.com/watch?v=U2SozAs9RzA

    static class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            int left = 1;
            int right = getMax(piles);

            while (left <= right) {
                int mid = left + (right - left) / 2;
                int hours = calculateHrs(piles, mid);
                if (hours <= h) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        private int calculateHrs(int[] piles, int k) {
            int hours = 0;
            for (int p : piles) {
                double speed = (double) p / k;
                hours += Math.ceil(speed);
            }
            return hours;
        }

        private int getMax(int[] piles) {
            int max = Integer.MIN_VALUE;
            for (int a : piles) {
                if (a > max) {
                    max = a;
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println(solution.minEatingSpeed(piles1, h1));  // Output: 4

        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println(solution.minEatingSpeed(piles2, h2));  // Output: 30

        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println(solution.minEatingSpeed(piles3, h3));  // Output: 23

    }


}
