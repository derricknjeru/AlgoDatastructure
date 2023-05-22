package hackerrack;

import java.util.Scanner;

public class DataTypes {
    public static void main(String[] args) {
        int i = 4;
        double d = 4.0;
        String s = "HackerRank ";

        Scanner scan = new Scanner(System.in);

        /* Declare second integer, double, and String variables. */
        int in;

        double db;

        String sv;

        /* Read and save an integer, double, and String to your variables.*/

        in = scan.nextInt();

        db = scan.nextFloat();

        sv = scan.next();
        sv += scan.nextLine();

        // Note.md.md.md.md: If you have trouble reading the entire String, please go back and review the Tutorial closely.


        /* Print the sum of both integer variables on a new line. */
        System.out.println(i + in);

        /* Print the sum of the double variables on a new line. */
        String str = String.format("%.1f", d + db);
        System.out.println(str);

        /* Concatenate and print the String variables on a new line;
            the 's' variable above should be printed first. */
        String con = s + sv;
        System.out.println(con);

        scan.close();

    }
}
