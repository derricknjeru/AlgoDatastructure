package CodingInterviewPatterns.Recursion;

public class ReverseString {
    public static void main(String[] args) {
        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s1);
        System.out.println(s1);  // Output: ["o","l","l","e","h"]

        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        reverseString(s2);
        System.out.println(s2);  // Output: ["h","a","n","n","a","H"]
    }

    public static void reverseString(char[] s) {
        reverseStringHelper(s, 0, s.length - 1);
    }

    private static void reverseStringHelper(char[] s, int left, int right) {
        if (left >= right) {
            return;  // Base case: All characters have been swapped
        }

        // Swap characters at left and right indices
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;

        // Recursively call the helper function for the remaining substring
        reverseStringHelper(s, left + 1, right - 1);
    }
    /**
     * The time complexity of the reverseString function, as well as the reverseStringHelper recursive function, is O(n),
     * where n is the length of the input string.
     *
     * The recursion makes n/2 recursive calls, as each recursive call reduces the size of the problem by 2 characters.
     * In each recursive call, a constant amount of work is performed, including the swapping of characters at the left and right indices.
     * Therefore, the overall time complexity is linear, proportional to the length of the input string.
     *
     * The space complexity of the recursion is O(n) as well.
     * Each recursive call adds a new frame to the call stack, which requires space to store the local variables and parameters of that call. Since there can be at most n/2 recursive calls in this case,
     * the space complexity is linear with respect to the length of the input string.
     *
     * It's important to note that the space complexity of the algorithm is considered O(1) in terms of extra memory,
     * as it doesn't use any additional data structures proportional to the input size. Instead,
     * it modifies the input array in-place.
     */

}
