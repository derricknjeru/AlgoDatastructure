package arrays_and_strings;

public class PairSum {
    public static void main(String[] args) {
        /**
         * Given a sorted array of unique integers and a target integer,
         * return true if there exists a pair of numbers that sum to target, false otherwise.
         * This problem is similar to Two Sum.
         *
         * For example, given nums = [1, 2, 4, 6, 8, 9, 14, 15] and target = 13, return true because 4 + 9 = 13.
         */
        int[] sortedArray = {-30, -22, -19, -16, 3, 5, 12, 17, 26, 30};
        int[] res = sum(sortedArray);
        System.out.println("The pair is " + res[0] + " and " + res[1]);
    }

    //find pair who/'s sum is 10
    //time complexity is O(n)
    private static int[] sum(int[] arr) {
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            if (arr[i] + arr[j] == 10) {
                //return true
                return new int[]{arr[i], arr[j]};
            } else if (arr[i] + arr[j] < 10) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{};
    }
}
