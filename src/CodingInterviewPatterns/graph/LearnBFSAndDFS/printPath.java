package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;

public class printPath {
    //You are given below list of folders, print the path from source, zero to the target folder.I'd represent the folder.

    //list = Folder[0, [7,3] "ABX"], Folder[7,[9],"ACD", Folder[8,[],"XYX"], Folder[9,[],"ERD"]

    //printPath(9) ---ABX ---ACD---ERD

    //printPath(8) =" ";

    //print path from source = 0;

    // Define the Folder class
    static class Folder {
        private int id;
        private List<Integer> neighbors;
        private String name;

        public Folder(int id, List<Integer> neighbors, String name) {
            this.id = id;
            this.neighbors = neighbors;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public List<Integer> getNeighbors() {
            return neighbors;
        }

        public String getName() {
            return name;
        }
    }

    // Define the Graph class

    public static Map<Integer, Folder> createAdjacencyList() {
        Map<Integer, Folder> adjacencyList = new HashMap<>();

        // Folder[0, [7, 3], "ABX"]
        List<Integer> neighbors0 = new ArrayList<>();
        neighbors0.add(7);
        neighbors0.add(3);
        Folder folder0 = new Folder(0, neighbors0, "ABX");
        adjacencyList.put(0, folder0);

        // Folder[7, [9], "ACD"]
        List<Integer> neighbors7 = new ArrayList<>();
        neighbors7.add(9);
        Folder folder7 = new Folder(7, neighbors7, "ACD");
        adjacencyList.put(7, folder7);

        // Folder[8, [], "XYX"]
        List<Integer> neighbors8 = new ArrayList<>();
        Folder folder8 = new Folder(8, neighbors8, "XYX");
        adjacencyList.put(8, folder8);

        // Folder[9, [], "ERD"]
        List<Integer> neighbors9 = new ArrayList<>();
        Folder folder9 = new Folder(9, neighbors9, "ERD");
        adjacencyList.put(9, folder9);

        return adjacencyList;
    }


    public static void main(String[] args) {
        Map<Integer, Folder> graph = createAdjacencyList();
        //Map<Integer, Folder> graph = createAdjacencyList2();
        int src = 0;
        int dest = 9;

        /*Set<Integer> visited = new HashSet<>();
        Stack<String> path = new Stack<>();

        dfs(graph, visited, src, dest, path);

        if (path.isEmpty()) {
            System.out.println("No path found to the target folder.");
        } else {
            System.out.print("Path from " + graph.get(src).getName() + " to " + graph.get(dest).getName() + ": ");
            String prefix = "";
            for (String folderName : path) {
                System.out.print(prefix + folderName);
                prefix = "--->";
            }
            System.out.println();
        }

        Stack<String> path2 = new Stack<>();
        if (dfs(graph, src, dest, path2)) {
            printPath(path2);
        } else {
            System.out.println("No path found to the target folder.");
        }*/

        Stack<String> path3 = new Stack<>();
        dfs2(graph, src, dest, path3);
        //printPath(path3);
    }

    private static void printPath(Stack<String> path) {
        StringJoiner joiner = new StringJoiner(" ---> ");
        path.forEach(joiner::add);
        System.out.println("Path: " + joiner);
    }

    /*private static boolean dfs(Map<Integer, Folder> graph, Set<Integer> visited, int src, int dest, Stack<String> path) {
        if (graph.get(src) == null) {
            return false;
        }
        visited.add(src);
        path.add(graph.get(src).getName());

        if (src == dest) return true;

        List<Integer> neighbors = graph.get(src).getNeighbors();
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    if (dfs(graph, visited, neighbor, dest, path)) {
                        return true;
                    }
                }
            }
        }
        // backtrack: remove the current node from the path
        // If the current folder is not part of the path to the destination, remove it
        path.pop();
        return false;
    }

    private static boolean dfs(Map<Integer, Folder> graph, int src, int dest, Stack<String> path) {
        if (graph.get(src) == null) {
            return false;
        }

        path.add(graph.get(src).getName());

        if (src == dest) {
            return true;
        }

        for (int neighbor : graph.get(src).getNeighbors()) {
            if (dfs(graph, neighbor, dest, path)) {
                return true;
            }
        }
        // backtrack: remove the current node from the path
        // If the current folder is not part of the path to the destination, remove it
        path.pop();
        return false;
    }*/

    private static void dfs2(Map<Integer, Folder> graph, int src, int dest, Stack<String> path) {
        if (!graph.containsKey(src)) {
            return;
        }

        path.add(graph.get(src).getName());

        if (src == dest) {
            printPath(path);
            return;
        }

        for (int neighbor : graph.get(src).getNeighbors()) {
            dfs2(graph, neighbor, dest, path);
        }
        // backtrack: remove the current node from the path
        // If the current folder is not part of the path to the destination, remove it
       path.pop();
    }


    public static Map<Integer, Folder> createAdjacencyList2() {
        Map<Integer, Folder> adjacencyList = new HashMap<>();

        // Folder[0, [1, 2], "Root"]
        List<Integer> neighbors0 = new ArrayList<>();
        neighbors0.add(1);
        neighbors0.add(2);
        Folder folder0 = new Folder(0, neighbors0, "Root");
        adjacencyList.put(0, folder0);

        // Folder[1, [3, 4], "Folder1"]
        List<Integer> neighbors1 = new ArrayList<>();
        neighbors1.add(3);
        neighbors1.add(4);
        Folder folder1 = new Folder(1, neighbors1, "Folder1");
        adjacencyList.put(1, folder1);

        // Folder[2, [5], "Folder2"]
        List<Integer> neighbors2 = new ArrayList<>();
        neighbors2.add(5);
        Folder folder2 = new Folder(2, neighbors2, "Folder2");
        adjacencyList.put(2, folder2);

        // Folder[3, [], "Folder3"]
        List<Integer> neighbors3 = new ArrayList<>();
        Folder folder3 = new Folder(3, neighbors3, "Folder3");
        adjacencyList.put(3, folder3);

        // Folder[4, [6], "Folder4"]
        List<Integer> neighbors4 = new ArrayList<>();
        neighbors4.add(6);
        Folder folder4 = new Folder(4, neighbors4, "Folder4");
        adjacencyList.put(4, folder4);

        // Folder[5, [], "Folder5"]
        List<Integer> neighbors5 = new ArrayList<>();
        Folder folder5 = new Folder(5, neighbors5, "Folder5");
        adjacencyList.put(5, folder5);

        // Folder[6, [], "Folder6"]
        List<Integer> neighbors6 = new ArrayList<>();
        Folder folder6 = new Folder(6, neighbors6, "Folder6");
        adjacencyList.put(6, folder6);

        return adjacencyList;
    }

}








