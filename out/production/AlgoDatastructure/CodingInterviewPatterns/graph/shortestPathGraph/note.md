So when to use BFS over A*, when to use Dijkstra over A* to find the shortest paths ?
We can summarise this as below-
1) One source and One Destination-
   → Use A* Search Algorithm (For Unweighted as well as Weighted Graphs)
2) One Source, All Destination –
   → Use BFS (For Unweighted Graphs)
   → Use Dijkstra (For Weighted Graphs without negative weights)
   → Use Bellman Ford (For Weighted Graphs with negative weights)
3) Between every pair of nodes-
   → Floyd-Warshall
   → Johnson’s Algorithm

Source links:
- [BFS shortest path & explanation of other](https://www.youtube.com/watch?v=T_m27bhVQQQ)
- [playlist](https://www.geeksforgeeks.org/shortest-path-unweighted-graph/)
- [BFS example](https://www.youtube.com/watch?v=UvxV6y0k6Vk)

### Shortest problems in unweighted graphs
They are 3 types of problems
- single source problem 
   - I want to find the shortest paths from a given node to all the other nodes.
- Point to point problem
   - I want to find the shortest distance from vertex A to vertex B
   - Typically done by stopping the single problem when we locate the target.
- All shortest paths problem.
   - I want to find the shortest path connecting each pair of points in the CodingInterviewPatterns.graph. 
  
#### weighted and unweighted
- Unweighted path cost is the number of edges in the path
- Weighted path cost is the sum of the costs of the edges in the path.

#### Dijkstra's Shortest Path Algorithm
- BFS works great for unweighted edges, but It does not guarantee a shortest path for unweighted edges.
- Dijkstra's guarantee finding the shortest weighted path (if no negative weights)
- It is a greedy algorithm, we can a decision with what we have. And we don't go back to change it

#### Bellman Ford Algorithm
- Dijkstra doesn’t work for Graphs with negative weights, Bellman-Ford works for such graphs.
- Bellman-Ford is also simpler than Dijkstra and suites well for distributed systems. But time complexity of Bellman-Ford is O(V * E), which is more than Dijkstra.

#### Floyd-Warshall - All Pairs Shortest Path Dynamic Programming

- The Floyd Warshall Algorithm is for solving all pairs of shortest-path problems.
- The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed Graph. 
- It is an algorithm for finding the shortest path between all the pairs of vertices in a weighted CodingInterviewPatterns.graph. This algorithm follows the dynamic programming approach to find the shortest path.
