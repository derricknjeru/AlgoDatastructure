package graph;

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        //https://www.youtube.com/watch?v=iJGr1OtmH0c
        //https://leetcode.com/problems/max-area-of-island/
        //https://github.com/neetcode-gh/leetcode/blob/main/695-Max-Area-of-Island.py
        int m = grid.length;
        int n = grid[0].length;

        int area=0;
        boolean[][] seen=new boolean[grid.length][grid[0].length];


        for(int i = 0; i<m; i++){
            for(int j =0; j<n; j++){
                if(grid[i][j]==1){
                    area=Math.max(area,dfs(grid,i,j,seen));
                }
            }
        }

        return area;
    }

    private int dfs(int[][] grid, int i, int j, boolean[][] seen){

        if(i<0 || i>=grid.length || j<0 || j>=grid[i].length || grid[i][j]==0 || seen[i][j]){
            return 0;
        }
        //grid[i][j]=0;

        seen[i][j] = true;

        return (1+dfs(grid,i+1,j, seen)+
                dfs(grid,i-1,j, seen)+
                dfs(grid,i,j+1, seen)+
                dfs(grid,i,j-1, seen));

    }
}
