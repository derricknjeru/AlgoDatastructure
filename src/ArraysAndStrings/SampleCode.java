package ArraysAndStrings;

import java.util.Arrays;
import java.util.List;

public class SampleCode {
    public static void main(String[] ars) {

        List<String> list = Arrays.asList("1", "2", "3");

        int[] A = {8,1,4,2};

        list.stream().mapToInt(Integer::parseInt).filter(num -> num % 2 == 0).forEach(
                System.out::println
        );

    }


}
