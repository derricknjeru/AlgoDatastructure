package Tips;

public class BinaryRepresentation {
    StringBuilder join = new StringBuilder();

    public static void main(String[] args) {
        String binary = new BinaryRepresentation().bin(7);
        System.out.println();
        System.out.println(binary);

        //or
        Integer.toBinaryString(7);
    }

    public String reverseOnlyLetters(String S) {
        if (S.length() == 0) return S;

        S = S.trim();

        int i = 0;
        int j = S.length() - 1;

        char[] a = S.toCharArray();

        while (i < j) {
            while (!Character.isLetter(S.charAt(i))) {
                i++;
            }
            while (!Character.isLetter(S.charAt(j))) {
                j--;
            }

            char temp = a[j];
            a[j] = S.charAt(i);
            a[i] = temp;

            i++;
            j--;

        }

        return new String(a);
    }

    String bin(int d) {
        String binary = Integer.toBinaryString(d);

        //or algo
        /**
         * step 1) if NUM > 1
         *     a) push NUM on stack
         *     b) recursively call function with 'NUM / 2'
         * step 2)
         *     a) pop NUM from stack, divide it by 2 and print it's remainder.
         */
        /* step 1 */
        if (d > 1) {
            bin(d / 2);
        }
        /* step 2 */
        System.out.print(d % 2);

        return join.append(d % 2).toString();

    }
}
