package arrays_and_strings;

public class MaximumAverageSubarrayK {
    // https://www.ideserve.co.in/learn/maximum-average-subarray

    private static int getMaxAvgSubarrayStartIndex(int input[], int k) {
        int n = input.length;

        if (k > n) {
            throw new IllegalArgumentException("K should be less than or equal to n");
        }

        if (k == n) return 0;

        //total sum
        int sum = 0;
        for (int num : input) {
            sum += num;
        }

        int maxIndex = 0;
        int maxMumSum = sum;
        for (int i = k; i < n; i++) {
            sum = sum - input[i - k] + input[i];
            if (sum > maxMumSum){
                maxMumSum = sum;
                maxIndex = i-k;
            }
        }

        return maxIndex + 1;
    }

    public static void main(String[] args) {
        int[] input = {11, -8, 16, -7, 24, -2, 3};
        int k = 3;
        int index = getMaxAvgSubarrayStartIndex(input, k);
        for(int i =0 ; i < k; i++) {
            System.out.println(input[index++]);
        }
    }
}
