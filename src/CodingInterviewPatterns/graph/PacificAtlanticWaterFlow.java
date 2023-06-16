package CodingInterviewPatterns.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {
    //https://www.youtube.com/watch?v=s-VkcjHqkGI&ab_channel=NeetCode
    //https://leetcode.com/problems/pacific-atlantic-water-flow/description/
    //time complexity O(m * n)
    //space is O(m * n)

    //int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int row = heights.length;
        List<List<Integer>> res = new ArrayList<>();
        if (row == 0) return res;
        int col = heights[0].length;

        boolean[][] pacific = new boolean[row][col];
        boolean[][] atlantic = new boolean[row][col];

        for (int i = 0; i < col; i++) {
            dfs(heights, 0, i, Integer.MIN_VALUE, pacific);
            dfs(heights, row - 1, i, Integer.MIN_VALUE, atlantic);
        }

        for (int i = 0; i < row; i++) {
            dfs(heights, i, 0, Integer.MIN_VALUE, pacific);
            dfs(heights, i, col - 1, Integer.MIN_VALUE, atlantic);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int[][] heights, int i, int j, int prev, boolean[][] ocean) {
        if (i < 0 || i >= heights.length || j < 0 || j >= heights[0].length
                || ocean[i][j] || heights[i][j] < prev) return;

        ocean[i][j] = true;

        dfs(heights, i + 1, j, heights[i][j], ocean);
        dfs(heights, i, j + 1, heights[i][j], ocean);
        dfs(heights, i - 1, j, heights[i][j], ocean);
        dfs(heights, i, j - 1, heights[i][j], ocean);

        /*for(int[] dir : directions){
           int x = i + dir[0];
           int y = j + dir[1];
            dfs(heights,x,y,heights[i][j],ocean);
        }*/

    }

}
