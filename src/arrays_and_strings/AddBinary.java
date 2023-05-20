package arrays_and_strings;

public class AddBinary {
    public static void main(String[] args) {
        String a = "11", b = "1";

        System.out.println(addBinary(a, b));

        StringBuilder sb = new StringBuilder();
        sb.insert(0,1);
        sb.insert(0,2);

        System.out.println(sb.toString());
    }

    public static String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;

        int carry = 0;

        StringBuilder sb = new StringBuilder();

        int sum = 0;

        while (i >= 0 || j >= 0) {

            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }

            if (j >= 0) {
                sum += Character.getNumericValue(b.charAt(j--));
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


}
