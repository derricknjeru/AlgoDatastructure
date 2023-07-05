package CodingInterviewPatterns;

public class CharacterExample {
    public static void main(String[] args) {
        char c = 'a';
        System.out.println(Character.isLetterOrDigit(c));//Alphanumeric
        System.out.println(Character.isDigit(c));
        System.out.println(Character.isLetter(c));
        System.out.println(Character.isLowerCase(c));
        System.out.println(Character.toLowerCase(c));
        System.out.println(Character.toUpperCase(c));


        char ch = '9';
        int digitValue = Character.digit(ch, 10);

        if (digitValue != -1) {
            System.out.println("The character is a digit.");
            System.out.println("Digit value: " + digitValue);
        } else {
            System.out.println("The character is not a digit.");
        }


    }
}
