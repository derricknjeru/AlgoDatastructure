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

    static class Folder {
        int id;
        List<Integer> subfolderIds;
        String name;

        Folder(int id, List<Integer> subfolderIds, String name) {
            this.id = id;
            this.subfolderIds = subfolderIds;
            this.name = name;
        }
    }

    static class Graph {
        Map<Integer, Folder> folderMap;
        Map<Integer, List<Integer>> adjacencyList;

        Graph(List<Folder> folders) {
            folderMap = new HashMap<>();
            adjacencyList = new HashMap<>();

            for (Folder folder : folders) {
                folderMap.put(folder.id, folder);
                adjacencyList.putIfAbsent(folder.id, new ArrayList<>());
                List<Integer> adjacentFolders = adjacencyList.get(folder.id);
                adjacentFolders.addAll(folder.subfolderIds);
            }
        }

        public String getFolderName(int folderId) {
            Folder folder = folderMap.get(folderId);
            return (folder != null) ? folder.name : "";
        }
    }

    public static void printPath(int targetId, List<Folder> folders) {
        Graph graph = new Graph(folders);
        List<String> path = new ArrayList<>();
        dfs(0, targetId, graph, path);
        printPath(path);
    }

    private static void dfs(int currentId, int targetId, Graph graph, List<String> path) {
        Folder currentFolder = graph.folderMap.get(currentId);
        if (currentFolder == null) {
            return;
        }

        path.add(currentFolder.name);

        if (currentId == targetId) {
            return;
        }

        List<Integer> adjacentFolders = graph.adjacencyList.get(currentId);
        for (int subfolderId : adjacentFolders) {
            dfs(subfolderId, targetId, graph, path);
            if (path.get(path.size() - 1).equals(graph.getFolderName(targetId))) {
                return;
            }
            path.remove(path.size() - 1);
        }
    }

    private static void printPath(List<String> path) {
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println(String.join(" --- ", path));
        }
    }

    public static void main(String[] args) {
        List<Folder> folders = new ArrayList<>();
        folders.add(new Folder(0, Arrays.asList(7, 3), "ABX"));
        folders.add(new Folder(7, Arrays.asList(9), "ACD"));
        folders.add(new Folder(8, Collections.emptyList(), "XYX"));
        folders.add(new Folder(9, Collections.emptyList(), "ERD"));

        printPath(9, folders); // Output: ABX --- ACD --- ERD
        printPath(8, folders); // Output: No path found.
        printPath(0, folders); // Output: ABX

        TreeMap<Integer, Integer> map = new TreeMap<>();

        Map.Entry<Integer, Integer> x = map.floorEntry(0);

    }
}






