package revision.tips;

public class BinaryToString {
    public static void main(String[] args) {
        BinaryToString toBinary = new BinaryToString();
        String input = "1001";
        System.out.println(convertStringToBinary(input));

        Integer.parseInt(input, 2);

    }

    public static String convertStringToBinary(String input) {
        int b1 = Integer.parseInt(input, 2);
        System.out.println(b1);
        return Integer.toBinaryString(b1);
    }
}
