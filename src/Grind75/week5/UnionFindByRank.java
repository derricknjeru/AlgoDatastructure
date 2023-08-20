package Grind75.week5;

//https://www.youtube.com/watch?v=kaBX2s3pYO4
//https://www.geeksforgeeks.org/union-by-rank-and-path-compression-in-union-find-algorithm/


public class UnionFindByRank {
    private final int[] parent;
    private final int[] rank;
    private int count;

    public UnionFindByRank(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    // FIND operation with Path Compression
    // Time Complexity: O(log(n)) (amortized)
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path Compression
        }
        return parent[x];
    }

    /**
     * Performs the UNION operation on two elements by rank-based merging.
     *
     * @param element1 the first element to be unioned
     * @param element2 the second element to be unioned
     */
    // Time Complexity: O(1) (amortized)
    public void union(int element1, int element2) {
        int root1 = find(element1);
        int root2 = find(element2);

        if (root1 != root2) {
            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }

            count--; // Decrease count when union operation is performed
        }
    }

    /**
     * Returns the current count of disjoint sets.
     *
     * @return the count of disjoint sets
     */
    // Time Complexity: O(1)
    public int getCount() {
        return count;
    }
}



