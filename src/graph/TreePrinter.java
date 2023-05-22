package graph;

import java.util.*;

public class TreePrinter {


    //https://www.youtube.com/watch?v=V0xjK_6ZoEY&t=33s
    //https://www.youtube.com/watch?v=dQdM86yRDCs
    //https://algorithms.tutorialhorizon.com/implement-graph-using-map-java/

    /**
     * Write a java function, printTree(), which prints a given tree to stdout;
     * Details:
     * - The argument of printTree is a stream of Relations: Pair of "parents -> child" relationships.
     * - Each string found anywhere in the inputs represent a unique node.
     * - Each parent can have many children
     * - The input list may contain Relationship in any order, although:
     * - The order in which the pair appear in the input list determines the node' order with respect to the siblings.
     * <p>
     * Example input:
     * List<Relation> input = new ArrayList<>();
     * input.add(new Relation("animal","mammal"));
     * input.add(new Relation("animal","bird"));
     * input.add(new Relation("lifeform","animal"));
     * input.add(new Relation("cat","lion"));
     * input.add(new Relation("mammal","cat"));
     * input.add(new Relation("animal","fish"));
     * <p>
     * TreePrinter.printTree(input);
     * <p>
     * Expected output:
     * 1: lifeform
     * 2.   animal
     * 3.     mammal
     * 4.       cat
     * 5.         lion
     * 6.     bird
     * 7.     fish
     *
     * @param input
     */
    public static void printTree(List<Relation> input) {

        HashMap<String, ArrayList<String>> treeMap = new HashMap<>();
        HashSet<String> children = new HashSet<>();

        for (Relation rs : input) {
            if (!treeMap.containsKey(rs.parent)) {
                treeMap.put(rs.parent, new ArrayList<String>());
            }
            treeMap.get(rs.parent).add(rs.child);
            children.add(rs.child);
        }

        String root = getRoot(treeMap, children);
        int level = 0;
        //Because we know the root
        printNodeDfs(treeMap, root, level);

        System.out.println("----------------------------------------------");

        //Testing
        /**
         * HashSet<String> seen = new HashSet<>();
         Iterator iterator = treeMap.keySet().iterator();

         while (iterator.hasNext()){
         if(!seen.contains(iterator.next())){
         dfs2(treeMap,root,seen,level);
         }
         }*/


    }

    private static void printNodeDfs(Map<String, ArrayList<String>> tree, String node, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }

        System.out.println((level) + ": " + node);

        if (tree.containsKey(node)) {
            List<String> children = tree.get(node);
            for (String child : children) {
                printNodeDfs(tree, child, level + 1);
            }
        }
    }

    private static void dfs(HashMap<String, ArrayList<String>> treeMap, int level, String root) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(root);

        if (!treeMap.containsKey(root)) return;

        List<String> children = treeMap.get(root);

        for (String child : children) {
            dfs(treeMap, level + 1, child);
        }
    }

    private static String getRoot(HashMap<String, ArrayList<String>> treeMap, HashSet<String> children) {
        String root = "";
        for (String parent : treeMap.keySet()) {
            if (!children.contains(parent)) return parent;
        }
        return root;
    }

    public static class Relation {
        String parent;
        String child;

        public Relation(String parent, String child) {
            this.child = child;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        List<Relation> input = new ArrayList<>();
        input.add(new Relation("animal", "mammal"));
        input.add(new Relation("animal", "bird"));
        input.add(new Relation("lifeform", "animal"));
        input.add(new Relation("cat", "lion"));
        input.add(new Relation("mammal", "cat"));
        input.add(new Relation("animal", "fish"));

        printTree(input);
    }
}
