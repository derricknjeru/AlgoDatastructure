public class ReverseString {
    public static void main(String[] args) {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        System.out.println(reverseString(word));
    }

    /**
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * You may assume all the characters consist of printable ascii characters.
     *
     *
     *
     * Example 1:
     *
     * Input: ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     * Example 2:
     *
     * Input: ["H","a","n","n","a","h"]
     * Output: ["h","a","n","n","a","H"]
     * Hide Hint #1
     * The entire logic for reversing a string is based on using the opposite directional two-pointer approach!
     */
    static String reverseString(char[] s) {
        int i = 0; //first index
        int j = s.length - 1;  //last index

        while (i < j) {
            char temp = s[i]; //store character
            s[i] = s[j]; //Swap
            s[j] = temp; //Swap

            i++;
            j--;
        }

        String finalString=new String(s);
        String anotherOption=String.valueOf(s);

        return finalString;

    }
}
