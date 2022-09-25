package hackerrack;

import java.util.Scanner;

public class DifferenceIndates {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int dAct = in.nextInt();
        int mAct = in.nextInt();
        int yAct = in.nextInt();

        int dExp = in.nextInt();
        int mExp = in.nextInt();
        int yExp = in.nextInt();

        in.close();

        getFine(dAct, mAct, yAct, dExp, mExp, yExp);
    }

    private static void getFine(int dAct, int mAct, int yAct, int dExp, int mExp, int yExp) {
        if (yAct > yExp) {
            System.out.println(10000);
        } else if (mAct > mExp && yAct == yExp) {
            System.out.println(500 * (mAct - mExp));
        } else if (dAct > dExp && mAct == mExp) {
            System.out.println(15 * (dAct - dExp));
        } else {
            System.out.println(0);
        }
    }
}
