package dynamic_programming_and_recursion;

public class UniquePaths {
    //https://leetcode.com/problems/unique-paths/
    //https://www.youtube.com/watch?v=-fS1pS1mxQc
    //https://www.youtube.com/watch?v=6qMFjFC9YSc
    public int uniquePaths(int m, int n) {
        int mat[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            mat[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            mat[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                mat[i][j] = mat[i - 1][j] + mat[i][j - 1];
            }
        }

        return mat[m - 1][n - 1];
    }
}
