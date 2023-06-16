package CodingInterviewPatterns.InterestingQuiz;

public class PossibleMaximumEdges {
    public static void main(String[] args) {
        int V = 4;
        //The nested for loop you provided will iterate over all possible combinations of distinct pairs of vertices. Let's break it down:
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                System.out.println("i ->" + i + " j ->" + j);
            }
        }

        int n = 4; // Number of vertices
        int count = 1;

        //n * (n-1)
        //Each line represents a directed edge from one vertex to another, covering all possible edges in the CodingInterviewPatterns.graph.
        for (int source = 0; source < n; source++) {
            for (int destination = 0; destination < n; destination++) {
                if (source != destination) {
                    System.out.println(count++ + "source ->" + source + " destination ->" + destination);
                }
            }
        }
    }
}
