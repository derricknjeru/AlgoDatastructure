package CodingInterviewPatterns.Maths;

public class Rounding {
    public static void main(String[] args) {
        double num1 = 4.2;
        double num2 = 4.8;

        int rounded1 = (int) Math.round(num1); // rounded1 = 4
        int rounded2 = (int) Math.round(num2); // rounded2 = 5

        int ceil1 = (int) Math.ceil(num1); // ceil1 = 5
        int ceil2 = (int) Math.ceil(num2); // ceil2 = 5

        int floor1 = (int) Math.floor(num1); // floor1 = 4
        int floor2 = (int) Math.floor(num2); // floor2 = 4

        System.out.println("Rounded 1: " + rounded1);
        System.out.println("Rounded 2: " + rounded2);
        System.out.println("Ceil 1: " + ceil1);
        System.out.println("Ceil 2: " + ceil2);
        System.out.println("Floor 1: " + floor1);
        System.out.println("Floor 2: " + floor2);
    }

}
