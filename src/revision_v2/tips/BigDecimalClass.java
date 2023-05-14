package revision_v2.tips;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class BigDecimalClass {
    public static void main(String[] args) {
        /**
         * https://www.geeksforgeeks.org/bigdecimal-class-java/
         * https://www.journaldev.com/16409/java-bigdecimal
         * https://codegym.cc/groups/posts/216-how-to-use-bigdecimal-in-java
         *
         */


        //BigDecimals are Immutable, arbitrary-precision signed decimal numbers.
        //Java BigDecimal also provides us convenient control over scale and precision.

        /**
         * By Definition, precision indicates the length of arbitrary precision integer
         * whereas scale means the number of digits to the right of the decimal point.
         * For example if we have a number “100.25” then precision is
         * 5 because we need five digits to write down this number and scale is 2.
         */

        //Difference between scale and precision
        //https://stackoverflow.com/questions/2377174/how-do-i-interpret-precision-and-scale-of-a-number-in-a-database


        //Creating BigDecimal object
        /*BigDecimal(BigInteger val): It takes BigInteger as an argument
         and creates a BigDecimal Object out of it.**/

        BigInteger bigInt = new BigInteger("233233233233");
        BigInteger bigInt2 = new BigInteger(String.valueOf(Integer.MAX_VALUE));

        BigDecimal bigDecimal = new BigDecimal(bigInt);

        System.out.println(bigDecimal);

        /**
         * Similarly, there are constructors available in the class which accept int, double, long, char[]
         * as well as String as an argument to create BigDecimal object:
         */
        int a = 9;
        String y = "12324";
        char[] c = new char[]{'1', '2'};
        double m = 5.014;

        BigDecimal bigDecimal1 = new BigDecimal(m);
        System.out.println(bigDecimal1);

        /**
         * BigDecimal(BigInteger unscaledVal, int scale):
         * We can also provide scale along with the value while creating the object.
         */


        int scale = 2;
        BigDecimal bigDecimal2 = new BigDecimal(bigInt, scale);
        System.out.println(bigDecimal2);

        /***
         * BigDecimal(BigInteger val, MathContext mc): It also gives us control to provide MathContext instance while creating the object.
         * Basically, it provides precision matching IEEE 754R Format. Following are the possible values for java.math.MathContext:
         * MathContext.DECIMAL128 ? to provide precision of 34 digits.
         * MathContext.DECIMAL64 – to provide precision of 16 digits.
         * MathContext.DECIMAL32 ? to provide precision of 7 digits.
         * MathContext.UNLIMITED ? to provide unlimited precision.
         * Let’s look at the code:
         */


        BigDecimal bigDecimal3 = new BigDecimal(bigInt, MathContext.DECIMAL64);
        System.out.println(bigDecimal3);

        //Java BigDecimal Methods
        //https://www.journaldev.com/16409/java-bigdecimal
        //https://codegym.cc/groups/posts/216-how-to-use-bigdecimal-in-java

    }
}
