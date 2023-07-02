package CodingInterviewPatterns.stack_and_queue.quickreview;

import java.util.ArrayDeque;
import java.util.Deque;

public class Stonewall {
    public static void main(String[] args) {

    }

    //https://gist.github.com/leonbobster/aa0a15cee35e2b62243366a1682e8522
    //https://app.codility.com/demo/results/trainingF8APGK-YZU/
    //https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
    public int solution(int[] H) {
        //block count
        int count = 0;
        // stack is used to hold height used to building and remove all the blocks from it,
        // if any of the block of stack is greater than current block(is to be added for building)
        Deque<Integer> stack = new ArrayDeque<>();

        for (int a : H) {
            // Remove all blocks that are bigger than current height, stack should not be empty
            while (!stack.isEmpty() && a < stack.peek()) {
                stack.pop();
            }
            //new block is required, push it's size to the stack
            if (stack.isEmpty() || a > stack.peek()) {
                count++;
                stack.push(a);
            }

        }
        return count;
    }
}
