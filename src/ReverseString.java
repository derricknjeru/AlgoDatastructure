public class ReverseString {
    public static void main(String[] args) {
        char[] word = {'H', 'e', 'l', 'l', 'o'};
        System.out.println(reverseString(word));
    }

    /**
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     * <p>
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * <p>
     * You may assume all the characters consist of printable ascii characters.
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
