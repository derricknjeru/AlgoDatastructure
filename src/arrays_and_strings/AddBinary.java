package arrays_and_strings;

import java.math.BigInteger;

public class AddBinary {
    public static void main(String[] args) {
        String a = "11", b = "1";

        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;

        if (j > i) {
            return addBinary(b, a);
        }

        int carry = 0;

        StringBuilder sb = new StringBuilder();

        int sum = 0;

        while (i >= 0 || j >= 0) {

            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }

            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }

            sb.insert(0, sum % 2); // if sum is 0, we will add 0. If sum is 1, we will add 1.if it's 2 we will Add 0

            carry = sum / 2;  //calculate the carry  if sum is 2, we carry 1 otherwise we carry 0

            sum = carry;

        }

        if (carry > 0) {
            sb.insert(0, 1);
        }

        return sb.toString();

    }

    //Not recommended for an interview
    public String addBinary2(String a, String b) {
        return new BigInteger(a, 2).add(new BigInteger(b, 2)).toString(2);
    }


}
