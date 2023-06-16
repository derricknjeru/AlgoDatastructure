package CodingInterviewPatterns.graph.UnionFind;

import java.util.*;

//https://www.youtube.com/watch?v=kaBX2s3pYO4
//https://www.geeksforgeeks.org/union-by-rank-and-path-compression-in-union-find-algorithm/

/**
 * UnionFind class represents the disjoint set data structure with path compression and union by rank optimizations.
 */
public class UnionFindByRank {
    class Node {
        int parent;
        int rank;
    }


    private List<Node> dsuf;

    /**
     * Constructs a UnionFind object with the specified size and initializes the disjoint set.
     *
     * @param size the number of elements in the disjoint set
     */
    public UnionFindByRank(int size) {
        dsuf = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Node node = new Node();
            node.parent = i;
            node.rank = 0;
            dsuf.add(node);
        }
    }

    /**
     * Finds the absolute parent of the subset to which a particular element belongs.
     * Performs path compression optimization by updating the parent during the find operation.
     *
     * @param v the element for which to find the parent
     * @return the absolute parent of the subset
     */
    public int find(int v) {
        if (dsuf.get(v).parent != v) {
            dsuf.get(v).parent = find(dsuf.get(v).parent);  // Path compression: recursively update the parent of x to the root
        }
        return dsuf.get(v).parent;
    }

    /**
     * Performs the UNION operation of two subsets based on their ranks.
     * Updates the parent and rank values accordingly.
     *
     * @param absoluteParent1 the absolute parent of the first subset
     * @param absoluteParent2 the absolute parent of the second subset
     */
    public void union(int absoluteParent1, int absoluteParent2) {
        if (dsuf.get(absoluteParent1).rank > dsuf.get(absoluteParent2).rank)
            dsuf.get(absoluteParent2).parent = absoluteParent1;
        else if (dsuf.get(absoluteParent1).rank < dsuf.get(absoluteParent2).rank)
            dsuf.get(absoluteParent1).parent = absoluteParent2;
        else {
            dsuf.get(absoluteParent1).parent = absoluteParent2;
            dsuf.get(absoluteParent2).rank += 1;
        }
    }
}
