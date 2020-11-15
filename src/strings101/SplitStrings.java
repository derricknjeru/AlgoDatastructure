package strings101;


import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SplitStrings {
    public static void main(String[] args) {
        //String s = "Welcome to Viusasa. \n1. Win na Jipange \n2. Download Viusasa App \n3. Buy Airtime \n4. Kenya Power \n5. Pay Tv \n6. Nairobi Water \n7. Order Gas \n8. Register for Skiza \n9. My Profile \n10. Contact us \n11. Tell us more \n12. Uptown music ";
        //String s = "1. Best of Makochia\n2. Best of Man Njoro\n3. Best of Mc Jessy\n4. Best of Jemutai\n";
        //String s = "Your request to pay KES 499.0 for GOtv has been received.\nYou will be taken to the mpesa menu shortly";
        //String s = "Please select: \n1. Pay KPLC Postpaid Bill \n2. Buy KPLC Prepaid Tokens \n\n0 Back \n00 Home";

        //String s = "Enter Amount:\n\n0 Back \n00 Home";

        String s="Invalid Account number \nEnter account number:";

        System.out.println(split(s));


    }

    public static Map<String, String> split(String str) {
        return Stream.of(str.split("\n"))
                .filter(elem -> !"".equals(elem))//check the value is not empty
                .collect(Collectors.toMap(
                        value -> value, SplitStrings::getValue));
    }


    private static String getValue(String value) {
        String keyValue;
        if (Character.isDigit(value.charAt(0)) && Character.isDigit(value.charAt(1))) {
            keyValue = value.charAt(0) + "" + value.charAt(1);
        } else if (Character.isDigit(value.charAt(0))) {
            keyValue = String.valueOf(value.charAt(0));
        } else {
            if (value.contains("Enter")) {
                keyValue = "form";
            } else {
                keyValue = "title";
            }
        }
        return keyValue;
    }


}
