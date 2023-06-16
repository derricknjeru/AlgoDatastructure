package CodingInterviewPatterns.graph.UnionFind;

public class UnionFindSimple {
    //https://www.youtube.com/watch?v=eTaWFhPXPz4
    UnionFindSimple() {

    }
    public int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    public void union(int[] parent, int x, int y) {
        int X = find(parent, x);
        int Y = find(parent, y);
        parent[X] = Y;
    }

    public static void main(String[] args) {
        // Test the UnionFindSimple class
        UnionFindSimple unionFind = new UnionFindSimple();

        int[] parent = {-1, -1, -1, -1, -1};

        unionFind.union(parent, 0, 1);
        unionFind.union(parent, 2, 3);

        System.out.println(unionFind.find(parent, 0)); // Output: 1
        System.out.println(unionFind.find(parent, 1)); // Output: 1
        System.out.println(unionFind.find(parent, 2)); // Output: 3
        System.out.println(unionFind.find(parent, 3)); // Output: 3
        System.out.println(unionFind.find(parent, 4)); // Output: 4
    }
}