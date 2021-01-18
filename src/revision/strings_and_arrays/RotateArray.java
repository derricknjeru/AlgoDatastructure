package revision.strings_and_arrays;

public class RotateArray {
    public static void main(String[] args) {

    }

    //Approach 1: Using Extra Array
    public int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        int[] nums = new int[A.length];

        int size = A.length;

        for (int i = 0; i < size; i++) {

            int rotatedIndex = (i + K) % size;

            nums[rotatedIndex] = A[i];

        }

        return nums;
    }
}
