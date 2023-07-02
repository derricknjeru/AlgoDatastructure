package CodingInterviewPatterns.stack_and_queue.quickreview;

import java.util.*;

public class CarFleet {
    public static void main(String[] args) {
        //https://leetcode.com/problems/car-fleet/
    }

    /**
     * The time complexity of the carFleet algorithm is O(n log n), where n is the number of cars.
     * <p>
     * The main factors contributing to this time complexity are the sorting of the cars based on their positions and the subsequent iteration over the sorted array.
     * <p>
     * Sorting the cars: The sorting operation takes O(n log n) time complexity since we are sorting the cars based on their positions using the Arrays.sort method. This is the dominant factor in the overall time complexity.
     * <p>
     * Iterating over the sorted array: After sorting the cars, we iterate over the array once to calculate the number of car fleets. This iteration takes O(n) time complexity since we process each car once.
     * <p>
     * Therefore, the overall time complexity is O(n log n) due to the sorting operation, which dominates the linear iteration.
     */

    public class Solution {
        /**
         * Here's the step-by-step algorithm to solve the problem:
         * <p>
         * Create a list of pairs (position[i], speed[i]) and sort it in descending order of positions.
         * This will ensure that the cars closer to the destination are considered first.
         * <p>
         * Initialize the number of fleets count as 0 and the current maximum arrival time maxArrivalTime as 0.
         * <p>
         * Iterate over each car from the first to the last in the sorted list:
         * <p>
         * Calculate the arrival time for the current car using the formula: (target - position) / speed.
         * <p>
         * If the arrival time is greater than maxArrivalTime, it means the current car forms a new fleet.
         * Increment the count by 1 and update maxArrivalTime with the arrival time of the current car.
         * <p>
         * Return the value of count, which represents the number of car fleets that will arrive at the destination.
         *
         * @param target
         * @param position
         * @param speed
         * @return
         */

        public int carFleet(int target, int[] position, int[] speed) {
            int n = position.length;

            // Combine the position and speed arrays into a 2D array
            int[][] pair = new int[n][2];
            for (int i = 0; i < n; i++) {
                pair[i] = new int[]{position[i], speed[i]};
            }

            // Sort the pair array based on the position in descending order
            Arrays.sort(pair, (a, b) -> b[0] - a[0]);
            int count = 0;
            double maxArrivalTime = 0;

            for (int i = 0; i < n; i++) {
                int p = pair[i][0];
                int s = pair[i][1];
                double arrivalTime = (double) (target - p) / s;

                if (arrivalTime > maxArrivalTime) {
                    count++;
                    maxArrivalTime = arrivalTime;
                }
            }
            return count;
        }
    }

    public class Solution2 {

        public int carFleet(int target, int[] position, int[] speed) {
            int n = position.length;
            int count = 0;

            // Combine the position and speed arrays into a 2D array
            int[][] pair = new int[n][2];
            for (int i = 0; i < n; i++) {
                pair[i] = new int[]{position[i], speed[i]};
            }

            // Sort the pair array based on the position in descending order
            Arrays.sort(pair, (a, b) -> b[0] - a[0]);

            Stack<Double> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                int p = pair[i][0];
                int s = pair[i][1];
                double timeToDestination = (double) (target - p) / s;

                if (stack.isEmpty() || timeToDestination > stack.peek()) {
                    count++;
                    stack.push(timeToDestination);
                }
            }

            return count;
        }
    }
}
