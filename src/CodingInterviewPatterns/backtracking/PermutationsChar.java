package CodingInterviewPatterns.backtracking;

public class PermutationsChar {

    /* Function to swap values at two indices in an array */
    //https://www.youtube.com/watch?v=GuTPwotSdYw&ab_channel=Techdose
    //Time complexity, what you should say in an interview: O(n⋅n!) ===> Time to print permutation * number of permutation

    public static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* Function to print permutations of a string */
    public static void permute(char[] arr, int l, int r) {
        if (l == r) {
            System.out.println(String.valueOf(arr));
        } else {
            for (int i = l; i <= r; i++) {
                swap(arr, l, i);
                permute(arr, l + 1, r);
                swap(arr, l, i); // backtrack
            }
        }
    }

    public static void main(String[] args) {
        String str = "ABC";
        int n = str.length();
        char[] arr = str.toCharArray();
        permute(arr, 0, n - 1);
    }

}
