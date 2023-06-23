package CodingInterviewPatterns.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PermutationsInt {
    //https://leetcode.com/problems/permutations/
    //https://leetcode.com/problems/permutations/editorial/
    //Time complexity, what you should say in an interview: O(n⋅n!) ===> Time to print permutation * number of permutation
    //https://www.youtube.com/watch?v=GuTPwotSdYw&ab_channel=Techdose

    class Solution {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            int n = nums.length;
            permute(ans, nums, 0, n - 1);
            return ans;
        }

        /* Function to swap values at two indices in an array */
        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        /* Function to generate permutations of an array */
        private void permute(List<List<Integer>> ans, int[] arr, int l, int r) {
            if (l == r) {
                List<Integer> permutation = new ArrayList<>();
                for (int a : arr) {
                    permutation.add(a);
                }
                ans.add(permutation);
            } else {
                for (int i = l; i <= r; i++) {
                    swap(arr, l, i);
                    permute(ans, arr, l + 1, r);
                    swap(arr, l, i); // backtrack
                }
            }
        }

    }
}
