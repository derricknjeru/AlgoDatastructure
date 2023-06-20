package CodingInterviewPatterns.Greedy;

import java.util.Arrays;

public class BoatsToSavePeople {
    //https://leetcode.com/problems/boats-to-save-people/
    //https://www.youtube.com/watch?v=XbaxWuHIWUs
    //https://leetcode.com/problems/boats-to-save-people/editorial/


    class Solution {
        /**
         * Time Complexity: O(Nlog⁡N), where NNN is the length of people.
         * Space Complexity: O(log⁡N)
         */
        public int numRescueBoats(int[] people, int limit) {
            Arrays.sort(people); // Sort the people array in ascending order

            int boats = 0;
            int left = 0;
            int right = people.length - 1;

            while (left <= right) {
                // If the heaviest person and the lightest person can fit in the same boat, load them together
                if (people[left] + people[right] <= limit) {
                    left++;
                    right--;
                } else {
                    // Otherwise, only the heaviest person can be loaded in the boat
                    right--;
                }
                boats++;
            }

            return boats;
        }
    }
}
