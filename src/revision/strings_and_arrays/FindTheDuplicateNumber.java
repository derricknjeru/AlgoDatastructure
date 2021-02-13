package revision.strings_and_arrays;

import java.util.Arrays;
import java.util.HashSet;

public class FindTheDuplicateNumber {
    public static void main(String[] args) {

    }

    public int findDuplicate(int[] nums) {
        Arrays.sort(nums); //0(nlogn)

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    public int findDuplicate2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (set.contains(num)) {
                return num;
            } else {
                set.add(num);
            }

        }
        return -1;
    }


}
