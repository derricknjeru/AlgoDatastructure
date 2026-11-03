package leetcode._11graph

fun main() {

    val graph = Graph(6)
    graph.addEdge(0, 1)
    graph.addEdge(0, 2)
    graph.addEdge(1, 3)
    graph.addEdge(2, 4)
    graph.addEdge(3, 5)
    graph.addEdge(4, 5)

    //BFS: [0, 1, 2, 3, 4, 5]
    // DFS: [0, 1, 3, 5, 4, 2]

    println("BFS: ${graph.bfs(0)}")
    println("DFS: ${graph.dfs(0)}")

}

class Graph(val vertexCount: Int, val directed: Boolean = false) {
    val adjacencyList: Array<MutableList<Int>> = Array(vertexCount) { mutableListOf() }

    fun addEdge(source: Int, destination: Int) {
        require(vertexCount != 0)
        adjacencyList[source].add(destination)

        if (!directed) {
            adjacencyList[destination].add(source)
        }
    }

    fun dfs(start: Int): List<Int> {
        val visited = BooleanArray(vertexCount)
        val result = mutableListOf<Int>()

        fun dfsVisit(vertex: Int) {
            result.add(vertex)
            visited[vertex] = true
            for (neighbor in adjacencyList[vertex]) {
                if (!visited[neighbor]) {
                    dfsVisit(neighbor)
                }
            }
        }
        dfsVisit(start)
        return result
    }

    fun bfs(start: Int): List<Int> {
        val queue = ArrayDeque<Int>()
        val visited = BooleanArray(vertexCount)
        val result = mutableListOf<Int>()

        queue.add(start)
        visited[start] = true

        while (queue.isNotEmpty()) {
            val vertex = queue.removeFirst()
            result.add(vertex)

            for (neighbor in adjacencyList[vertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true
                    queue.add(neighbor)
                }
            }

        }

        return result
    }
}