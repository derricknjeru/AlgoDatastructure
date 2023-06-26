package CodingInterviewPatterns.KWayMerge;

import java.util.PriorityQueue;

public class KthSmallestElementInSortedMatrix {
    public static void main(String[] args) {

    }

    class Solution {
        /**
         * The time complexity of this implementation is O(k log n), where n is the size of the matrix.
         * Since k is usually much smaller than n^2,
         * this approach provides better performance than sorting the entire matrix.
         *
         * @param matrix
         * @param k
         * @return
         */

        public int kthSmallest(int[][] matrix, int k) {
            // storing the number of rows in the matrix to use it in later
            int rowCount = matrix.length;
            // declaring a min-heap to keep track of smallest elements
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

            for (int i = 0; i < rowCount; i++) {
                // pushing the first element of each row in the min-heap
                // The offer() method pushes an element into an existing heap
                // in such a way that the heap property is maintained.
                minHeap.offer(new int[]{matrix[i][0], i, 0});
            }

            int numbersChecked = 0;
            int smallestElement = 0;
            // iterating over the elements pushed in our minHeap
            while (!minHeap.isEmpty()) {
                // get the smallest number from top of heap and its corresponding row and column
                int[] curr = minHeap.poll();
                smallestElement = curr[0];
                int rowIndex = curr[1];
                int colIndex = curr[2];
                numbersChecked++;
                // when numbersChecked equals k, we'll return smallestElement
                if (numbersChecked == k) {
                    break;
                }
                // if the current element has a next element in its row,
                // add the next element of that row to the minHeap
                if (colIndex + 1 < matrix[rowIndex].length) {
                    minHeap.offer(new int[]{matrix[rowIndex][colIndex + 1], rowIndex, colIndex + 1});
                }
            }
            // return the Kth smallest number found in the matrix
            return smallestElement;
        }

    }

    class Solution2 {
        //Binary search
        //This optimization reduces the time complexity to O(n log range),
        // where range is the difference between the smallest and largest elements in the matrix.
        public int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int left = matrix[0][0];
            int right = matrix[n - 1][n - 1];

            while (left < right) {
                int mid = left + (right - left) / 2;
                int count = countLessOrEqual(matrix, mid);

                if (count < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            return left;
        }

        private int countLessOrEqual(int[][] matrix, int target) {
            int count = 0;
            int n = matrix.length;
            int row = n - 1;
            int col = 0;

            while (row >= 0 && col < n) {
                if (matrix[row][col] <= target) {
                    count += row + 1;
                    col++;
                } else {
                    row--;
                }
            }

            return count;
        }
    }


    class Solution3 {
        /**
         * The time complexity of this implementation is O(n * m * log k),
         * where n is the number of rows, m is the number of columns,
         * and k is the desired position of the smallest element.
         *
         * @param matrix
         * @param k
         * @return
         */
        public int kthSmallest(int[][] matrix, int k) {
            int rows = matrix.length;
            if (rows == 0) return 0;
            int cols = matrix[0].length;

            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int a = matrix[i][j];
                    maxHeap.add(a);
                    if (maxHeap.size() > k) {
                        maxHeap.remove();
                    }

                }
            }

            return maxHeap.remove();

        }
    }
}
