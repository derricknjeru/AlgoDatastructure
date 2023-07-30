package Grind75.week2;

public class AddBinary {
    public static void main(String[] args) {
        Solution sln = new Solution();
        String a = "11";
        String b = "1";
        System.out.println(sln.addBinary(a, b)); // Output: "100"

        a = "1010";
        b = "1011";
        System.out.println(sln.addBinary(a, b)); // Output: "10101"
    }

    static public class Solution {
        public String addBinary(String a, String b) {
            int i = a.length() - 1;
            int j = b.length() - 1;
            StringBuilder result = new StringBuilder();
            int carry = 0;

            while (i >= 0 || j >= 0 || carry > 0) {
                int sum = carry;

                if (i >= 0) {
                    sum += a.charAt(i--) - '0';
                }

                if (j >= 0) {
                    sum += b.charAt(j--) - '0';
                }

                result.insert(0, sum % 2);
                carry = sum / 2;
            }

            return result.toString();
        }

    }

}
