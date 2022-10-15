package Amazon;

import java.util.Arrays;

public class NoOfVehicles {
    public static void main(String[] args) {
        /**
         *
         * Given n, the total number of wheels there are, how many different ways can there be 2 and 4 wheeled vehicles? A way is different if and only if the number of 2 wheeled vehicles and 4 wheeled vehicles is different from any other way. All wheels must be used, no wheel can be left behind
         *
         * Some examples:
         *
         * 4 Wheels:  2 -> 2,2 and 4   i.e. you can have two 2-wheeled vehicles or one 4-wheeled vehicle
         * 6 Wheels:  2 -> 2,2,2 and 4,2
         * 10 Wheels: 3 -> 2,2,2,2,2; 2,2,2,4; 2,4,4
         */

        /**
         *  When the input is even, the number of possibilities is directly determined by the number of 4-wheel cars you can get from the input number.
         *
         * Let's say the input is 22, then the number of 4s you can get from that is 22//4 = 5. This corresponds to the following combinations:
         *
         * 4 2 2 2 2 2 2 2 2 2
         * 4 4 2 2 2 2 2 2 2
         * 4 4 4 2 2 2 2 2
         * 4 4 4 4 2 2 2
         * 4 4 4 4 4 2
         * There is one more combination that should be counted, which is the one without any 4:
         *
         * 2 2 2 2 2 2 2 2 2 2 2
         * So in general the solution is n//4 + 1
         *
         * def uniqueCount(wheels):
         *     if wheels % 2 == 1:
         *         return 0
         *     return wheels // 4 + 1
         */

        int[] wheels2 = {4, 5, 2};
        int[] wheels = {4, 6, 10};
        int index = 0;
        int n = wheels.length;
        int[] res = new int[n];

        for (int wheel : wheels) {
            res[index++] = getUniqueCount(wheel);
        }

        System.out.println(Arrays.toString(res));
    }

    private static int getUniqueCount(int wheels) {
        if (wheels % 2 == 1) {
            return 0;
        }
        return (wheels / 4) + 1;
    }
}
