package CodingInterviewPatterns.Heaps;

import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPointsToOrigin {
    /**
     * The time complexity of the given code is O(n log k), where n is the number of points and k is the value passed as the parameter.
     *
     * The code uses a max heap (implemented as a PriorityQueue) to keep track of the k closest points. Initially, all points are added to the max heap. If the size of the max heap exceeds k, the farthest point is removed from the heap. This ensures that at any point, the max heap contains the k closest points encountered so far.
     *
     * The process of adding and removing elements from the max heap takes O(log k) time. Since there are n points in total, the overall time complexity of the loop that adds points to the max heap is O(n log k).
     *
     * After all the points have been processed, the code creates a 2D array and populates it with the points from the max heap. This step takes O(k) time because there are at most k points in the max heap.
     *
     * Therefore, the overall time complexity of the code is O(n log k + k) = O(n log k), since the O(n log k) term dominates the O(k) term as k is generally much smaller than n.
     */
    //https://www.youtube.com/watch?v=1rEUgAG7f_c&ab_channel=KevinNaughtonJr.
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
    class Solution {
        public int[][] kClosest(int[][] points, int k) {
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                    (a,b) -> Integer.compare(dist(b), dist(a)));

            for(int[] point : points){
                maxHeap.offer(point);
                if(maxHeap.size()>k) maxHeap.remove();
            }

            int[][] res = new int[k][2];
            for (int i = 0; i < k; i++) {
                int[] cur = maxHeap.remove();
                res[i][0] = cur[0];
                res[i][1] = cur[1];
            }
            return res;

        }

        private int dist(int[] points){
            int x = points[0];
            int y = points[1];
            return x*x + y*y;
        }
    }

    public int[][] kClosest(int[][] points, int k) {

        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> dist(b) - dist(a));

        /*PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a,b) -> Integer.compare(dist(b), dist(a)));*/

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
        /** int[][] res = new int[k][2];
         for (int i = 0; i < k; i++) {
         int[] cur = maxHeap.remove();
         res[i][0] = cur[0];
         res[i][1] = cur[1];
         }*/

        return res;
    }

    private int dist(int[] point) {
        int x = point[0];
        int y = point[1];
        return x * x + y * y;
    }
}
