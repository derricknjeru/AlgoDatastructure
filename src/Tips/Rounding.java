package Tips;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounding {

    public static void main(String[] args) {
        double input = 3.14159265359;
        BigDecimal bd = new BigDecimal(input).setScale(2, RoundingMode.HALF_UP);
        double newInput = bd.doubleValue();

       // System.out.println(newInput);

        int [] x = new int[4];
        for(int c  : x){
            System.out.println(c);
        }

    }


}
