package SearchingAndSorting;

public class BinarySearchImplementation {
    private static boolean binarySearchIterative(int[] array, int target) {

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (array[mid] == target) return true;
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return false;
    }

    private static boolean binarySearchRecursive(int[] array, int target, int left, int right) {
        if(left>right){ //important -> ">" not >=
            return false;
        }

        int mid = left + ((right - left) / 2);

        if (array[mid] == target) return true;

        if (array[mid] < target) {
                left = mid + 1;
        } else {
                right = mid - 1;
        }
        return binarySearchRecursive(array,target,left,right);

    }
}
