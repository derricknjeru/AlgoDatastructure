package hackerrack;

import java.io.*;

class Result {

    /*
     * Complete the 'highestValuePalindrome' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER n
     *  3. INTEGER k
     */

    public static String highestValuePalindrome(String s, int n, int k) {
        // Write your code here

        if (n == 1) {
            if (k > 0) return "9";
            else return s;
        }
        int[] change = new int[n];
        char[] charArray = s.toCharArray();

        //part 1, convert the string to a palindrome
        int ptr1 = 0;
        int ptr2 = n - 1;

        while (ptr1 <= ptr2) {
            if (charArray[ptr1] == charArray[ptr2]) {
            } else {
                if (k == 0) return "-1";
                if (charArray[ptr1] > charArray[ptr2]) {
                    charArray[ptr2] = charArray[ptr1];
                    change[ptr2] = 1;
                    k--;
                } else if (charArray[ptr2] > charArray[ptr1]) {
                    charArray[ptr1] = charArray[ptr2];
                    change[ptr1] = 1;
                    k--;
                }
            }

            ptr2--;
            ptr1++;
        }

        //part 2, forming the highest value palindrome

        if (k > 0) {
            ptr1 = 0;
            ptr2 = n - 1;

            while (ptr1 <= ptr2 && k > 0) {
                if (charArray[ptr1] != '9') {
                    if (change[ptr1] == 1 || change[ptr2] == 1) {
                        charArray[ptr1] = '9';
                        charArray[ptr2] = '9';
                        k--;
                    } else {
                        if (k > 1) {
                            charArray[ptr1] = '9';
                            charArray[ptr2] = '9';
                            k -= 2;
                        }
                    }
                }

                ptr2--;
                ptr1++;

                if (ptr1 == ptr2 && k >= 1) {
                    charArray[ptr1] = '9';
                    break;
                }
            }
        }


        return new String(charArray);

    }

}

public class HighestValuePalindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        String s = bufferedReader.readLine();

        String result = Result.highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
