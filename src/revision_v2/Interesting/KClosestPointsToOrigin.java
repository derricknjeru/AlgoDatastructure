package revision_v2.Interesting;

import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPointsToOrigin {
    /**
     * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
     * <p>
     * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
     * <p>
     * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: points = [[1,3],[-2,2]], k = 1
     * Output: [[-2,2]]
     * Explanation:
     * The distance between (1, 3) and the origin is sqrt(10).
     * The distance between (-2, 2) and the origin is sqrt(8).
     * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
     * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
     * Example 2:
     * <p>
     * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
     * Output: [[3,3],[-2,4]]
     * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= k <= points.length <= 104
     * -104 < xi, yi < 104
     */

    public int[][] kClosest(int[][] points, int k) {

        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> dist(b) - dist(a));

        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[k][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();
        }

        return res;
    }

    private int dist(int[] point) {
        int x = point[0];
        int y = point[1];
        return x * x + y * y;
    }
}
