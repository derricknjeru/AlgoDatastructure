package CodingInterviewPatterns.graph.LearnBFSAndDFS;

import java.util.*;
import java.util.stream.Collectors;

public class FileTreeTraversal {
    /*
     *   files = [ "/webapp/assets/html/a.html",
     *             "/webapp/assets/html/b.html",
     *             "/webapp/assets/js/c.js",
     *             "/webapp/index.html" ]
     *
     *   Expected output
     *   webapp
     *      assets
     *         html
     *           a.html
     *           b.html
     *         js
     *           c.js
     *
     *      index.html
     *
     *
     *
     */
    /*
     * /webapp
     *     |
     *     +-- assets
     *     |   |
     *     |   +-- html
     *     |   |   |
     *     |   |   +-- a.html
     *     |   |   |
     *     |   |   +-- b.html
     *     |   |
     *     |   +-- js
     *     |       |
     *     |       +-- c.js
     *     |
     *     +-- index.html
     */
    static class TreeNode {
        String name;
        Map<String, TreeNode> children;

        public TreeNode(String name) {
            this.name = name;
            this.children = new HashMap<>();
        }

        public TreeNode() {
            this.name = "Root"; // Initialize with a non-empty name
            this.children = new HashMap<>();
        }

        public TreeNode addChild(String childName) {
            TreeNode child = new TreeNode(childName);
            children.put(childName, child);
            return child;
        }
    }

    public static void main(String[] args) {
        String[] files = {
                "Desktop/Documents/File2.jpg",
                "Himalayas.zip",
                "California.jpg",
                "Desktop/Documents",
                "Desktop/Documents/Education/Class1",
                "Desktop/Documents/Public",
                "Desktop/Pictures/Photography",
                "Desktop/Pictures/Photography/CoolPics/pics.zip"
        };

        String[] files2 = {
                "/webapp/assets/html/a.html",
                "/webapp/assets/html/b.html",
                "/webapp/assets/js/c.js",
                "/webapp/index.html"
        };

        TreeNode root = new TreeNode(); // Initialize the root node with a non-empty name
        for (String file : files) {
            addFileToTree(root, file);
        }

        printFileTree(root, 0);
    }

    public static void addFileToTree(TreeNode node, String filePath) {
        String[] parts = filePath.split("/");
        TreeNode currentNode = node;

        for (String part : parts) {
            if (!part.isEmpty()) {
                TreeNode childNode = currentNode.children.get(part);

                if (childNode == null) {
                    childNode = currentNode.addChild(part);
                }

                currentNode = childNode;
            }
        }
    }

    public static void printFileTree(TreeNode node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("\t");
        }
        System.out.println(node.name);

        List<TreeNode> sortedChildren = node.children.values()
                .stream()
                .sorted(Comparator.comparing(child -> child.name))
                .collect(Collectors.toList());

        for (TreeNode child : node.children.values()) {
            printFileTree(child, depth + 1);
        }
    }
}
