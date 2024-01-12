package CodingInterviewPatterns.graph.ConnectedComponents;


import CodingInterviewPatterns.graph.UnionFind.UnionFindByRank;

public class NumberOfProvincesUF {
    //https://leetcode.com/problems/number-of-provinces/editorial/

    static class Solution {
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            UnionFindByRank dsu = new UnionFindByRank(n);

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isConnected[i][j] == 1) {
                        dsu.union(i, j);
                    }
                }
            }

            return dsu.getCount();
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example usage:
        int[][] isConnectedExample = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        int result = solution.findCircleNum(isConnectedExample);
        System.out.println("Number of provinces: " + result); // Output: 2
    }
}
