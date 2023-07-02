package CodingInterviewPatterns.Greedy;

public class GasStation {
    //https://leetcode.com/problems/gas-station/description/
    //https://www.youtube.com/watch?v=lJwbPZGo05A
    class Solution {
        //https://www.youtube.com/watch?v=xmJZSYSvgfE&t=16s

        public int canCompleteCircuit(int[] gas, int[] cost) {
            int start = 0;
            int total_surplus = 0;
            int surplus = 0;

            for (int i = 0; i < gas.length; i++) {
                total_surplus += gas[i] - cost[i];
                surplus += gas[i] - cost[i];
                if (surplus < 0) {
                    surplus = 0;
                    start = i + 1;
                }
            }
            return total_surplus < 0 ? -1 : start;
        }
    }
}
