package CodingInterviewPatterns.Heaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class VotingSystem {

    /**
     * Given candidates standing for an election, you have to create an interface which contains the following function:
     * <p>
     * 1. voteCandidate ( candidateID );
     * 2. getTopK ( k );
     * The first function is used to cast a vote to the candidate represented by the candidateID.
     * The second function is used to find the top K candidates at any moment during the course of an election.
     * Solution: Use maps to store the count of votes of each candidate and max heap to retrieve top k elements.
     * Time complexity O(n + klogn).
     *
     * @param args
     */
    public static void main(String[] args) {
        Voting v = new Voting();
        v.voteCandidate(2);
        v.voteCandidate(2);
        v.voteCandidate(3);
        v.voteCandidate(3);
        v.voteCandidate(3);
        v.voteCandidate(1);

        System.out.println(v.getK(2));

    }

    static class Voting {
        HashMap<Integer, Integer> map;
        PriorityQueue<Integer> minHeap;

        Voting() {
            minHeap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
            map = new HashMap<>();
        }

        public void voteCandidate(int candidateID) {
            //O(1)
            map.put(candidateID, map.getOrDefault(candidateID, 0) + 1);
            //map.put(candidateID, new int[]{s,e});
        }

        public List<Integer> getK(int k) {
            List<Integer> res = new ArrayList<>();
            //4,5,
            for (int key : map.keySet()) {
                minHeap.add(key);
                if (minHeap.size() > k) {
                    minHeap.poll(); //
                }
            }

            while (!minHeap.isEmpty()) {
                res.add(minHeap.poll());
            }
            return res;
        }
    }
}
