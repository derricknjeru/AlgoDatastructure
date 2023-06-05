package CodingInterviewPatterns.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    //https://leetcode.com/problems/3sum/description/
    public List<List<Integer>> threeSum(int[] integers) {
        Arrays.sort(integers); //nlog n
        List<List<Integer>> result = new ArrayList<>();
        int n = integers.length;
        //two loops. One to get first item and the other one has two sum
        for (int index = 0; index < n; index++) {

            if (index > 0 && integers[index] == integers[index - 1]) {
                //avoid duplicate
                continue;
            }

            int left = index + 1;
            int right = n - 1;

            while (left < right) {
                int threeSum = integers[index] + integers[left] + integers[right];
                if (threeSum < 0) {
                    left++;
                } else if (threeSum > 0) {
                    right--;
                } else {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(integers[index]);
                    triplet.add(integers[left]);
                    triplet.add(integers[right]);
                    result.add(triplet);

                    left++;
                    right--;

                    while (left < right && integers[left] == integers[left - 1]) {
                        left++;
                    }
                }
            }
        }

        return result;
    }

}
