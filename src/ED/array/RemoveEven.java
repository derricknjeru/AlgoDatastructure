package ED.array;

public class RemoveEven {
    /**
     * In this problem, you have to implement the int [] removeEven(int[] arr) method,
     * which removes all the even elements from the array and returns updated array.
     * <p>
     * Input
     * An array with integers.
     * <p>
     * Output
     * An array with only odd integers.
     * <p>
     * Sample Input#
     * arr = {1, 2, 4, 5, 10, 6, 3}
     * Sample Output
     * arr = {1, 5, 3}
     */
    /**
     * Time Complexity
     * Since the entire array has to be iterated over, this solution is in O(n)
     * ​​.
     * @param arr
     * @return
     */

    public static int[] removeEven(int[] arr) {
        int oddElements = 0;

        //Find number of odd elements in arr
        for (int a : arr) {
            if (a % 2 != 0) oddElements++;
        }

        //Create result array with size equal to the number of odd elements in arr
        int[] result = new int[oddElements];
        int result_index = 0;

        //Put odd values from arr to the resulted array
        for (int a : arr) {
            if (a % 2 != 0) result[result_index++] = a;
        } //end of for loop

        return result;
    } //end of removeEven

    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size]; //declaration and instantiation

        System.out.print("Before removing Even Numbers: ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i; // assigning values
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int[] newArr = removeEven(arr); // calling removeEven

        System.out.print("After removing Even Numbers: ");
        for (int a : newArr) {
            System.out.print(a + " "); // printing array
        }
    }

}
