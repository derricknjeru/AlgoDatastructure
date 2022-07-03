package heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneHeight {
    /**
     * We have a collection of stones, each stone has a positive integer weight.
     * <p>
     * Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
     * <p>
     * If x == y, both stones are totally destroyed;
     * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
     * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
     * <p>
     * <p>
     * Note:
     * <p>
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     * Hide Hint #1
     * Simulate the process. We can do it with a heap, or by sorting some list of stones every time we take a turn.
     */

    public static void main(String[] args) {
        LastStoneHeight stoneHeight = new LastStoneHeight();
        int[] arr = {2, 7, 4, 1, 8, 1};
        stoneHeight.lastStoneWeight2(arr);

    }

    //https://www.youtube.com/watch?v=fBPS7PtPtaE
    public int lastStoneWeight(int[] stones) {
        //Collections.reverseOrder() sorts the queue in desc
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int a : stones) {
            maxHeap.add(a);
        }
        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.remove();
            int stone2 = maxHeap.remove();
            if (stone1 != stone2) {
                maxHeap.add(stone1 - stone2);
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();

    }

    public int lastStoneWeight2(int[] stones) {
        //Collections.reverseOrder() sorts the queue in desc
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for (int a : stones) {
            maxHeap.add(-a); //use negative to reverse numbers
        }
        System.out.println(Arrays.toString(maxHeap.toArray()));

        while (maxHeap.size() > 1) {
            int stone1 = -maxHeap.remove();
            int stone2 = -maxHeap.remove();
            if (stone1 != stone2) {
                maxHeap.add(-(stone1 - stone2));
            }
        }

        System.out.println(Arrays.toString(maxHeap.toArray()));

        return maxHeap.isEmpty() ? 0 : -maxHeap.remove();

    }


}
