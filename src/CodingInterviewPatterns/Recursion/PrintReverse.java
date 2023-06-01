package CodingInterviewPatterns.Recursion;

public class PrintReverse {
    public static void main(String[] args) {
        char[] s = "Hello".toCharArray();

        printReverse(s);
    }

    private static void printReverse(char[] s) {
        helper(0, s);
    }

    private static void helper(int index, char[] s) {
        if (s == null || index >= s.length) {
            return;
        }
        //This process continues recursively until the index exceeds the length of the string,
        // at which point the recursion starts unwinding.
        helper(index + 1, s);
        //As the recursion unwinds, the System.out.print(str[index]) statement is executed for each recursive call,
        // printing the characters in reverse order.
        System.out.print(s[index]);
    }
    /**
     * Complexity
     * The time complexity of the printReverse function, as well as the helper recursive function, is O(n),
     * where n is the length of the input string.
     *
     * The space complexity of the recursion is O(n) as well. Each recursive call adds a new frame to the call stack,
     * which requires space to store the local variables and parameters of that call.
     * Since there can be at most n recursive calls in this case, the space complexity is linear with respect to the length of the input string.
     */

    /**
     * Detailed explanation:
     * For example, if we call printReverse with the string "hello", the recursion steps are as follows:
     *
     * helper(0, "hello")
     * -> recursive call: helper(1, "hello") ->
     * recursive call: helper(2, "hello") ->
     * recursive call: helper(3, "hello") ->
     * recursive call: helper(4, "hello") ->
     * base case condition met, returns to previous call.
     * At this point, the recursion starts unwinding:
     * System.out.print(str[4]) -> prints "o"
     * System.out.print(str[3]) -> prints "l"
     * System.out.print(str[2]) -> prints "l"
     * System.out.print(str[1]) -> prints "e"
     * System.out.print(str[0]) -> prints "h"
     * The recursion has completed, and the final output is "olleh", which is the reverse of the input string "hello".
     */
}
