package CodingInterviewPatterns.BinarySearch;

import java.util.Arrays;

public class KokoEatingBananas {
    //https://www.youtube.com/watch?v=U2SozAs9RzA
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        //int right = getMaxPiles(piles);
        int right = Arrays.stream(piles).max().getAsInt();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canEatAll(piles, mid, h)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int getMaxPiles(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int bananas : piles) {
            max = Math.max(max, bananas);
        }
        return max;
    }

    private boolean canEatAll(int[] piles, int k, int h) {
        int hours = 0;
        for (int bananas : piles) {
            hours += (bananas - 1) / k + 1;
        }
        return hours <= h;
    }

    public static void main(String[] args) {
        KokoEatingBananas solution = new KokoEatingBananas();

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
