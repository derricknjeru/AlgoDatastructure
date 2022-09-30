package hackerrack;

import java.util.HashMap;
import java.util.Scanner;

public class StringToken {
    public static void main(String[] args) {
        //https://www.hackerrank.com/challenges/java-string-tokens/
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        s = s.trim();
        if (s.length() == 0) {
            System.out.println(0);
        } else {
            String[] splStrings = s.split("[ !,?._'@]+");
            System.out.println(splStrings.length);
            for (String str : splStrings) {
                if (!str.isEmpty()) {
                    System.out.println(str);
                }
            }
        }
        scan.close();
    }
}
