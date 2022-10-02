package arrays_and_strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitStrings {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";

        String[] arr = SplitStrings.split(s);

        System.out.println(Arrays.toString(arr));
    }

    private static String[] split(String s) {
        List<String> stringList = new ArrayList<>();
        //
        char[] arr = s.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (char c : arr) {
            if (c == ' ') {
                stringList.add(sb.toString());
                // //reset StringBuilder;
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }

        stringList.add(sb.toString());

        return stringList.toArray(new String[0]);
    }
}
