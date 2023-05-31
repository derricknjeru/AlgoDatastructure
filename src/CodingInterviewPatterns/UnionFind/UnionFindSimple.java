package CodingInterviewPatterns.UnionFind;

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
}