package CodingInterviewPatterns.Greedy;

import java.util.HashSet;
import java.util.Set;

public class MergeTripletsToFormTargetTriplet {
    //https://leetcode.com/problems/merge-triplets-to-form-target-triplet/description/
    //https://www.youtube.com/watch?v=kShkQLQZ9K4

    class Solution {
        public boolean mergeTriplets(int[][] triplets, int[] target) {
            boolean foundX = false, foundY = false, foundZ = false;

            for (int[] triplet : triplets) {
                int currentX = triplet[0];
                int currentY = triplet[1];
                int currentZ = triplet[2];

                if (currentX <= target[0] && currentY <= target[1] && currentZ <= target[2]) {
                    if (currentX == target[0]) {
                        foundX = true;
                    }
                    if (currentY == target[1]) {
                        foundY = true;
                    }
                    if (currentZ == target[2]) {
                        foundZ = true;
                    }
                }
            }

            return foundX && foundY && foundZ;
        }
    }

    class Solution2 {
        public boolean mergeTriplets(int[][] triplets, int[] target) {
            Set<Integer> good = new HashSet<>();

            for (int[] t : triplets) {
                if (t[0] > target[0] || t[1] > target[1] || t[2] > target[2]) {
                    continue;
                }
                for (int i = 0; i < 3; i++) {
                    if (t[i] == target[i]) {
                        good.add(i);
                    }
                }
            }
            return good.size() == 3;
        }
    }


}
