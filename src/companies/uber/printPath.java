package companies.uber;

import java.util.*;

public class printPath {
    //Print the path from the source folder (0) to a target folder using graphs, you can modify the provided code as follows:
    //You are given below list of folders, print the path from source, zero to the target folder.Id represent the folder.

    //list = //Folder[0, [7,3] "ABX"], Folder[7,[9],"ACD", Folder[8,[],"XYX"], Folder[9,[],"ERD"]

    //printPath(9) ---ABX ---ACD---ERD

    //printPath(8) =" ";

    //print path from source = 0;

    //https://www.geeksforgeeks.org/find-paths-given-source-destination/


    // Define the Folder class
    static class Folder {
        int id;
        List<Integer> children;
        String name;

        public Folder(int id, List<Integer> children, String name) {
            this.id = id;
            this.children = children;
            this.name = name;
        }
    }

    // Define the Graph class
    static class Graph {
        Map<Integer, Folder> vertices;

        public Graph() {
            vertices = new HashMap<>();
        }

        public void addVertex(int id, List<Integer> children, String name) {
            vertices.put(id, new Folder(id, children, name));
        }
    }


    // Function to find the path from source to target folder
    public static void printPath(Graph graph, int targetId) {
        // Create a map to store the paths
        Map<Integer, List<Integer>> paths = new HashMap<>();

        // Recursive function to find paths
        List<Integer> currentPath = new ArrayList<>();
        findPath(graph, 0, targetId, currentPath, paths);

        // Print the path to the target folder
        if (paths.containsKey(targetId)) {
            List<Integer> pathToTarget = paths.get(targetId);
            for (int folderId : pathToTarget) {
                System.out.print(graph.vertices.get(folderId).name + "---");
            }
            System.out.println();
        } else {
            System.out.println("No path found to the target folder.");
        }
    }

    private static void findPath(Graph graph, int currentId, int targetId, List<Integer> currentPath, Map<Integer, List<Integer>> paths) {
        // Add the current folder to the path
        currentPath.add(currentId);

        // If the current folder is the target folder, store the path
        if (currentId == targetId) {
            paths.put(targetId, new ArrayList<>(currentPath));
        }

        // Recursively explore the children folders if they exist
        Folder currentFolder = graph.vertices.get(currentId);
        if (currentFolder != null && currentFolder.children != null) {
            for (int childId : currentFolder.children) {
                findPath(graph, childId, targetId, currentPath, paths);
            }
        }

        // Remove the current folder from the path
        currentPath.remove(currentPath.size() - 1);
    }


    public static void main(String[] args) {
        // Create the CodingInterviewPatterns.graph of folders
        Graph graph = new Graph();
        graph.addVertex(0, Arrays.asList(7, 3), "ABX");
        graph.addVertex(7, Collections.singletonList(9), "ACD");
        graph.addVertex(8, Collections.emptyList(), "XYX");
        graph.addVertex(9, Collections.emptyList(), "ERD");

        // Example usage:
        printPath(graph, 9);  // Output: ABX---ACD---ERD
        printPath(graph, 8);  // Output: No path found to the target folder.
        printPath(graph, 0);  // Output: (Empty line)
    }


}








