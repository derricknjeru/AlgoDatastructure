package Grind75.week1;

import java.util.*;

public class ImplementQueueUsingStacks {
    //https://leetcode.com/problems/implement-queue-using-stacks/description/
    //https://leetcode.com/problems/implement-queue-using-stacks/editorial/
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        for (int i = 1; i <= 100; i++) {
            myQueue.push(i);
        }
        System.out.println(myQueue.peek()); // Output: 1
        System.out.println(myQueue.pop());  // Output: 1
        myQueue.push(101);
        System.out.println(myQueue.peek()); // Output: 2
        System.out.println(myQueue.pop());  // Output: 2
        System.out.println(myQueue.empty());// Output: false
    }

    static class MyQueue {
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        int front;

        public MyQueue() {
            stack1 = new Stack<Integer>();
            stack2 = new Stack<Integer>();
        }

        //time - O(1)
        //space - O(n);
        public void push(int x) {
            if (stack1.empty()) front = x;
            stack1.push(x);
        }

        //time - Amortized O(1), in worst-case = O(n);
        //space - O(1);
        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        //time - O(1)
        //space - O(1);
        public int peek() {
            if (!stack2.isEmpty()) return stack2.peek();
            return front;
        }

        //time - O(1)
        //space - O(1);
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    /**
     * Your MyQueue object will be instantiated and called as such:
     * MyQueue obj = new MyQueue();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.peek();
     * boolean param_4 = obj.empty();
     */

    class MyQueue2 {
        Stack<Integer> stack1;
        Stack<Integer> stack2;
        int front;

        public MyQueue2() {
            stack1 = new Stack<Integer>();
            stack2 = new Stack<Integer>();
        }

        //time - O(1)
        //space - O(1);
        public void push(int x) {
            if (stack1.empty()) front = x;

            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack2.push(x);
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }

        public int pop() {
            int res = stack1.pop();
            if (!stack1.isEmpty()) front = stack1.peek();
            return res;
        }

        public int peek() {
            return front;
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
}
