package CodingInterviewPatterns.trees;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private class TreeNode {
        Map<Character, TreeNode> children;
        boolean isEndWord;

        public TreeNode() {
            children = new HashMap<>();
            isEndWord = false;
        }
    }

    private final TreeNode root;

    public Trie() {
        root = new TreeNode();
    }

    //Iterative implementation
    public void insert(String word) {
        TreeNode currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TreeNode node = currentNode.children.get(c);
            if (node == null) {
                node = new TreeNode();
                currentNode.children.put(c, node);
            }
            currentNode = node;

        }
        //mark current node isEndWord true;
        currentNode.isEndWord = true;
    }

    //Recursive implementation
    public void insertRecursively(String word) {
        insertRecursive(root, word, 0);
    }

    private void insertRecursive(TreeNode current, String word, int index) {
        if (index == word.length()) {
            //if the end of the word is reached set isEndWord to true
            current.isEndWord = true;
            return;
        }
        char c = word.charAt(index);
        TreeNode node = current.children.get(c);

        if (node == null) {
            node = new TreeNode();
            current.children.put(c, node);
        }

        insertRecursive(node, word, index + 1);

    }

    //Iterative implementation of the search

    public boolean search(String word) {
        TreeNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TreeNode node = current.children.get(c);
            if (node == null) {
                return false;
            }

            current = node;
        }
        //return true of the current's end word is true else return false;
        return current.isEndWord;

    }

    public boolean searchRecursively(String word) {
        TreeNode current = root;
        return searchRecursive(current, word, 0);
    }

    private boolean searchRecursive(TreeNode current, String word, int index) {

        if (index == word.length()) {
            return current.isEndWord;
        }

        char c = word.charAt(index);
        TreeNode node = current.children.get(c);

        if (node == null) {
            return false;
        }
        return searchRecursive(node, word, index + 1);
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TreeNode current, String word, int index) {
        if (index == word.length()) {
            //when end of the word is reached only delete when isEndword is true;
            if (!current.isEndWord) {
                return false;
            }

            current.isEndWord = false;
            //if current has no other mapping return true
            return current.children.size() == 0;
        }

        char c = word.charAt(index);
        TreeNode node = current.children.get(c);
        if (node == null) return false;

        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        // if true is returned then delete the mapping of character and trie node references from map;

        if (shouldDeleteCurrentNode) {
            current.children.remove(c);
            //return true if there are no mapping left in the map
            return current.children.size()==0;
        }

        return false;
    }
}
