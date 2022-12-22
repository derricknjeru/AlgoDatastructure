import java.util.Arrays;

public class TwoWayRoadRenovation {
    public static void main(String[] args) {

        String l1 = "..xx.x.";
        String l2 = "x.x.x..";
        //String l1 = "xxxxx";
        //String l2 = ".x..x";
        //String l1 = "..xx.x.";
       // String l2 = "x.x.x..";
        //https://leetcode.com/discuss/interview-question/algorithms/1769043/microsoft-oa-sde2

        System.out.println(solution(l1, l2));

    }

    public static int solution(String L1, String L2) {

        int n = L1.length();
        int[] preL1 = new int[n];
        int[] preL2 = new int[n];

        // Calculate the prefix sum (total number of potholes
        // from the beginning of the road)
        for (int i = 0; i < n; i++) {
            if (L1.charAt(i) == 'x')
                preL1[i] = 1;
            if (L2.charAt(i) == 'x')
                preL2[i] = 1;
            if (i > 0) {
                preL1[i] += preL1[i - 1];
                preL2[i] += preL2[i - 1];
            }
        }

        System.out.println(Arrays.toString(preL1));
        System.out.println(Arrays.toString(preL2));

        // Calculate the total number of potholes
        int potholesTot = preL1[n - 1] + preL2[n - 1];

        System.out.println(potholesTot);
        // Calculate the minimum of potholes going straight
        // down one road
        int potholes = Math.min(preL1[n - 1], preL2[n - 1]);

        System.out.println(potholes);
        //min 3

        for (int i = 1; i < n; i++) {
            // Calculate the minimum number of potholes going down
            // road L1 to point i and then changing to travel on L2
            if ((preL1[i] + (preL2[n - 1] - preL2[i])) < potholes)
                potholes = preL1[i] + (preL2[n - 1] - preL2[i - 1]);

            // Calculate the minimum number of potholes going down
            // road L2 to point i and then changing to travel on L1
            if ((preL2[i] + (preL1[n - 1] - preL1[i])) < potholes)
                potholes = preL2[i] + (preL1[n - 1] - preL1[i - 1]);
        }

        return (potholesTot - potholes);
    }
}
