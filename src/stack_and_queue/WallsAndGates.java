package stack_and_queue;

public class WallsAndGates {
    /**
     * Description
     * You are given a m x n 2D grid initialized with these three possible values.
     * <p>
     * -1 - A wall or an obstacle.
     * 0 - A gate.
     * INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate, that room should remain filled with INF
     * <p>
     * Contact me on wechat to get Amazon、Google requent Interview questions . (wechat id : jiuzhang0607)
     * <p>
     * <p>
     * Example
     * Example1
     * <p>
     * Input:
     * [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
     * Output:
     * [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
     * <p>
     * Explanation:
     * the 2D grid is:
     * INF  -1  0  INF
     * INF INF INF  -1
     * INF  -1 INF  -1
     * 0  -1 INF INF
     * the answer is:
     * 3  -1   0   1
     * 2   2   1  -1
     * 1  -1   2  -1
     * 0  -1   3   4
     * Example2
     * <p>
     * Input:
     * [[0,-1],[2147483647,2147483647]]
     * Output:
     * [[0,-1],[1,2]]
     */

    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                int room = rooms[i][j];
                if (room == 0) { //it's a gate
                    dfs(i, j, 0, rooms);
                }
            }
        }
    }

    //https://www.youtube.com/watch?v=Pj9378ZsCh4
    private void dfs(int i, int j, int distance, int[][] rooms) {
        //we are in the grid and distance is small than the current value on the room
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[i].length || rooms[i][j] < distance) {
            return;
        }

        rooms[i][j] = distance;

        dfs(i + 1, j, distance + 1, rooms);
        dfs(i - 1, j, distance + 1, rooms);
        dfs(i, j + 1, distance + 1, rooms);
        dfs(i, j - 1, distance + 1, rooms);
    }

    public static void main(String[] args) {
        String word = "abcdefd", ch = "d";
    }
}
