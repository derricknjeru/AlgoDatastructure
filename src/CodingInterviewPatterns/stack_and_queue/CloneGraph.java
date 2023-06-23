package CodingInterviewPatterns.stack_and_queue;

import java.util.*;

public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    /**
     * Please remember that to go in the Depth-wise manner, you need to go recusrively, as it will go deeper into the nodes/CodingInterviewPatterns.trees (in case of CodingInterviewPatterns.trees). For breadth-wise manner, you need to go with level-order (for CodingInterviewPatterns.trees) / side-wise (for graphs) fashion, so we have to use queue here. This is one key observation, that I made.
     *
     * Time Complexity:
     * For DFS: O(V + E), where V = No of vertices, E = No of edges, cause you are traversing each vertices and edge exactly one.
     * For Bfs: O(V^2) for the worst case, when every other node is connected to every other node.
     *
     * Please do upvote if you find this answer helpful.
     */
    //Best
    //https://www.youtube.com/watch?v=e5tNvT1iUXs
    // O(V + E)
    //space complexity is  O(V + E) since we are copying all the nodes.
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        Map<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Integer, Node> map) {
        if (map.containsKey(node.val)) return map.get(node.val);

        Node copy = new Node(node.val);
        map.put(node.val, copy);

        for (Node neighbor : node.neighbors)
            copy.neighbors.add(dfs(neighbor, map));

        return copy;
    }


    public Node cloneGraph2(Node node) {
        if(node == null) return null;

        Queue<Node> q = new LinkedList<>();
        HashMap<Integer, Node> map = new HashMap<>();

        q.add(node);
        map.put(node.val, new Node(node.val));

        while(!q.isEmpty()){
            Node newNode = q.remove();

            for(Node neighbor : newNode.neighbors){
                if(!map.containsKey(neighbor.val)){
                    map.put(neighbor.val, new Node(neighbor.val, new ArrayList<Node>()));
                    q.add(neighbor);
                }

                map.get(newNode.val).neighbors.add(map.get(neighbor.val));
            }
        }

        return map.get(node.val);
    }

}

