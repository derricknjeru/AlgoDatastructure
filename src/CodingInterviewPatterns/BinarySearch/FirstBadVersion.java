package CodingInterviewPatterns.BinarySearch;

public class FirstBadVersion extends VersionControl {
    public static void main(String[] args) {
        //https://leetcode.com/problems/first-bad-version/editorial/
        //https://leetcode.com/problems/first-bad-version/description/

    }

    /**
     * The firstBadVersion() method takes an integer n as input and returns the first bad version.
     *
     * Inside the method, we initialize two variables: left and right. left represents the leftmost version in the search range, which is initially set to 1 (the first version). right represents the rightmost version in the search range, which is initially set to n (the last version).
     *
     * We enter a while loop with the condition left < right. This loop continues until the search range is narrowed down to a single version.
     *
     * Inside the while loop, we calculate the middle version using the formula mid = left + (right - left) / 2. This ensures that we avoid integer overflow when calculating the middle version.
     *
     * We call the isBadVersion() method with the mid version. This method, which is assumed to be implemented in a separate class called VersionControl, returns true if the version is bad and false otherwise.
     *
     * If isBadVersion(mid) returns true, it means the current version mid is either bad or a later version is bad. In this case, we update right = mid to search for the first bad version in the left half of the range.
     *
     * If isBadVersion(mid) returns false, it means the current version mid is good. In this case, we update left = mid + 1 to search for the first bad version in the right half of the range.
     *
     * The loop continues until left and right are equal, which indicates that the search range has been narrowed down to a single version. At this point, we have found the first bad version, and we return either left or right.
     *
     * Note that the code assumes that the isBadVersion() method is implemented in a class called VersionControl, which you will need to define separately. The implementation of the isBadVersion() method will depend on your specific logic for determining whether a version is bad or not.
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int i = 1;
        int j = n;

        while (i < j) {
            int mid = i + (j - i) / 2;

            if (isBadVersion(mid)) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }

        return i; //you can return either
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
