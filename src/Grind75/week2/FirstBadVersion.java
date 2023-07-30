package Grind75.week2;

public class FirstBadVersion extends VersionControl {
    public static void main(String[] args) {
        //https://leetcode.com/problems/first-bad-version/editorial/
        //https://leetcode.com/problems/first-bad-version/description/

    }

    /**
     * The firstBadVersion() method takes an integer n as input and returns the first bad version.
     * <p>
     * Inside the method, we initialize two variables: left and right. left represents the leftmost version in the search range, which is initially set to 1 (the first version). right represents the rightmost version in the search range, which is initially set to n (the last version).
     * <p>
     * We enter a while loop with the condition left < right. This loop continues until the search range is narrowed down to a single version.
     * <p>
     * Inside the while loop, we calculate the middle version using the formula mid = left + (right - left) / 2. This ensures that we avoid integer overflow when calculating the middle version.
     * <p>
     * We call the isBadVersion() method with the mid version. This method, which is assumed to be implemented in a separate class called VersionControl, returns true if the version is bad and false otherwise.
     * <p>
     * If isBadVersion(mid) returns true, it means the current version mid is either bad or a later version is bad. In this case, we update right = mid to search for the first bad version in the left half of the range.
     * <p>
     * If isBadVersion(mid) returns false, it means the current version mid is good. In this case, we update left = mid + 1 to search for the first bad version in the right half of the range.
     * <p>
     * The loop continues until left and right are equal, which indicates that the search range has been narrowed down to a single version. At this point, we have found the first bad version, and we return either left or right.
     * <p>
     * Note that the code assumes that the isBadVersion() method is implemented in a class called VersionControl, which you will need to define separately. The implementation of the isBadVersion() method will depend on your specific logic for determining whether a version is bad or not.
     *
     * @param
     * @return
     */
    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */



    public class Solution extends VersionControl {
        //Approach 3: Find Lower bound
        //Different from the previous method, here we are looking for the leftmost insert position.
        //Explanation:
        //you have array int[] array = new int[]{-7, -4, 3, 9, 9, 9, 12};
        //Assume you want to insert 9 into the array.
        //if we look at the lower bound, we have to insert 9 to the left of all existing 9.
        //This will help a lot when we have duplicate values
        //Now we do binary search --We have left ==0 and right ==array.length (Note that the maximum insert position can be nums.size);
        //If nums[mid] = target, the insert position is on mid's left, so we let right = mid to discard the right half.
        //If nums[mid] > target, the insert position is on mid's left, so we let right = mid to discard the right half.
        //f nums[mid] < target, the insert position is on mid's right, so we let left = mid + 1 to discard the left half and mid.
        //Therefore, we merged the two conditions nums[mid] = target and nums[mid] > target and there are only two conditions in the if-else statement!
        //Once the loop stops, left stands for the insert position and nums[left] is the smallest element that is no less than target. We just need to check if nums[left] equals target. Note this boundary condition left = nums.size, which means all elements in nums are smaller than target, so there is no target in nums.
        public int firstBadVersion(int n) {
            int left = 0;
            int right = n;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (isBadVersion(mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }
    }
}

// VersionControl class
class VersionControl {
    // Dummy implementation of isBadVersion() method
    // Replace this with your actual implementation
    boolean isBadVersion(int version) {
        // Your logic to determine if a version is bad or not
        // Return true if version is bad, false otherwise
        return false;
    }
}
