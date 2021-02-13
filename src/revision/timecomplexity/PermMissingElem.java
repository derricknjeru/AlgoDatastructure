package revision.timecomplexity;

import java.util.HashSet;
import java.util.Set;

public class PermMissingElem {
    public static void main(String[] args) {

    }
    //Approach 2:
    //Time complexity O(n)
    //Space complexity O(n)

    public int solution2(int[] A) {
        int max = A.length + 1;

        //load array elements into array so would be quick to check if elements exist
        Set<Integer> incompleteSet = new HashSet<>();
        for (int j : A) {
            incompleteSet.add(j);
        }

        for (int i = 1; i < max + 1; i++) {
            if (!incompleteSet.contains(i)) {
                return (i);
            }
        }
        throw new RuntimeException("shouldn't reach here");
    }

    /**
     * This problem has a mathematical solution, based on the fact that the sum of consecutive integers from 1 to n is equal to n(n+1)/2.
     * <p>
     * Using this formula we can calculate the sum from 1 to N+1. Then with O(N) time complexity we calculate the actual sum of all elements in the array.
     * <p>
     * The difference between the full and actual totals will yield the value of the missing element.
     * <p>
     * Space complexity is O(1).
     **/
    public int solution(int[] A) {
        // write your code in Java SE 8
        double sum = 0;
        for (int i : A) {
            sum += i;
        }

        int n = A.length + 1;

        //n(n+1)/2.
        double deno = (double) n * (n + 1);
        double total = deno / 2;

        double a = total - sum;

        return (int) a;


    }
}
