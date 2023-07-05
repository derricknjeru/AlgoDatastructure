package CodingInterviewPatterns.graph.Bfs;

import java.util.PriorityQueue;
import java.util.Queue;

public class SwimInRisingWater {
    //https://leetcode.com/problems/swim-in-rising-water/description/
    //https://www.youtube.com/watch?v=amvrKlMLuGY&ab_channel=NeetCode

    // Solution: Greedy Approach with Min Heap
// Time Complexity: O((n^2)*log(n))
    class Solution {

        private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        public int swimInWater(int[][] grid) {
            int len = grid.length;

            if (len == 1) {
                return 0;
            }

            boolean[][] seen = new boolean[len][len];
            seen[0][0] = true;

            Queue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> a[0] - b[0]);
            minHeap.add(new int[]{grid[0][0], 0, 0});

            int result = 0;

            while (!minHeap.isEmpty()) {
                int[] curr = minHeap.poll();

                result = Math.max(result, curr[0]);

                if (curr[1] == len - 1 && curr[2] == len - 1) {
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int x = curr[1] + dirs[i][0];
                    int y = curr[2] + dirs[i][1];

                    if (x < 0 || x >= len || y < 0 || y >= len || seen[x][y]) {
                        continue;
                    }

                    minHeap.add(new int[]{grid[x][y], x, y});
                    seen[x][y] = true;
                }
            }

            return result;
        }
    }
}
