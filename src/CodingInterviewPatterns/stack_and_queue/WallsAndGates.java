package CodingInterviewPatterns.stack_and_queue;

import java.util.*;

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
     * Contact me on wechat to get companies.Amazon、companies.Google requent Interview questions . (wechat id : jiuzhang0607)
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

    /**
     * The time complexity of the given algorithm is O(m * n), where 'm' represents the number of rows in the grid and 'n' represents the number of columns.
     * <p>
     * The main reason for this time complexity is the BFS traversal of the grid. In the worst case, each cell of the grid may be visited once. Since there are 'm' rows and 'n' columns, the total number of cells is m * n. Therefore, the time complexity is proportional to the number of cells in the grid.
     * <p>
     * Additionally, other operations such as enqueueing and dequeuing positions from the queue take constant time on average. So, they don't significantly affect the overall time complexity.
     * <p>
     * Hence, the time complexity of the algorithm is O(m * n), where 'm' and 'n' represent the dimensions of the grid.
     * <p>
     * Here's the algorithmic explanation of the Java solution:
     * <p>
     * Initialize a queue to store the positions of the gates.
     * Enqueue all gate positions (0s) into the queue.
     * While the queue is not empty, do the following:
     * Dequeue a position from the queue.
     * Process the neighboring cells of the dequeued position.
     * If a neighboring cell is an empty room (INF), update its distance to the nearest gate and enqueue its position.
     * Repeat step 3 until all empty rooms are processed.
     * The grid will be updated with the distances filled in.
     * Detailed steps within the while loop:
     * <p>
     * Dequeue a position from the queue.
     * Get the row and column indices from the dequeued position.
     * Iterate over the four possible directions: right, left, down, and up.
     * For each direction, calculate the new row and column indices.
     * If the new indices are out of bounds or the neighboring cell is not an empty room (INF), continue to the next iteration.
     * If the neighboring cell is an empty room, update its distance to the nearest gate by incrementing the distance of the current position (row, col) by 1.
     * Enqueue the position of the neighboring cell into the queue.
     * Repeat steps 1-3 until the queue is empty.
     * This algorithm uses a breadth-first search (BFS) approach to traverse the grid starting from the gate positions (0s) and updates the distances of the empty rooms (INF) to their nearest gate. By exploring the grid level by level, the algorithm ensures that the distances are filled in the shortest possible order.
     */

    private static final int INF = 2147483647;
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void wallsAndGates(int[][] rooms) {
        //https://www.youtube.com/watch?v=yJvA5MNx5LM&ab_channel=VivekSharma
        //https://chat.openai.com/
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        int m = rooms.length;
        int n = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Enqueue all gate positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int row = position[0];
            int col = position[1];

            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || rooms[newRow][newCol] != INF) {
                    continue;
                }

                rooms[newRow][newCol] = rooms[row][col] + 1;
                queue.offer(new int[]{newRow, newCol});
            }
        }
    }

    public static void main(String[] args) {
        int[][] rooms = {
                {INF, -1, 0, INF},
                {INF, INF, INF, -1},
                {INF, -1, INF, -1},
                {0, -1, INF, INF}
        };

        WallsAndGates roomDistance = new WallsAndGates();
        roomDistance.wallsAndGates(rooms);

        for (int[] row : rooms) {
            System.out.println(Arrays.toString(row));
        }
    }


    public void wallsAndGates2(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) {
            return;
        }

        int m = rooms.length;
        int n = rooms[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int row, int col, int distance) {
        //https://www.youtube.com/watch?v=Pj9378ZsCh4&ab_channel=KevinNaughtonJr.
        int m = rooms.length;
        int n = rooms[0].length;

        if (row < 0 || row >= m || col < 0 || col >= n || rooms[row][col] < distance) {
            return;
        }
        rooms[row][col] = distance;

        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            dfs(rooms, newRow, newCol, distance + 1);
        }
    }
}
