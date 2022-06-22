package stack_and_queue;

import java.util.*;

public class MinStackAlgo {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        //-- > Returns - 3.
        System.out.println("Min" + minStack.getMin());

        minStack.pop();

        // -- > Returns 0.
        System.out.println("Top" + minStack.top());

        //-- > Returns - 2.
        System.out.println("Min2" + minStack.getMin());

    }

    static class MinStack {

        StackNode head;

        public MinStack() {
            head = new StackNode();
        }

        public void push(int val) {
            StackNode newNode = new StackNode(val);
            newNode.next = head;

            int prevMin = Integer.MAX_VALUE;

            if (head != null) {
                prevMin = head.min;
            }

            newNode.min = Math.min(val, prevMin);

            head = newNode;


        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }

    static class StackNode {
        StackNode next;
        int min = Integer.MAX_VALUE;
        int val;

        StackNode() {

        }

        StackNode(int val) {
            this.val = val;
        }
    }

    class MinStack2 {

        /**
         * initialize your data structure here.
         */
        int min[] = null; // this array will maintain min number for each index
        int arr[] = null; // stack
        int top;
        boolean empty = true;

        public MinStack2() {
            arr = new int[30000]; // As per question, there will be 30000 calls
            min = new int[30000];
            top = -1;
        }

        public void push(int val) {
            top++;

            if (empty) { // no need to check for minimum if stack is empty
                empty = false;
                arr[top] = val; // push the value in the stack
                min[top] = val; // push the value in min array
            } else // when stack is not empty
            {
                arr[top] = val; // push the value in stack

                // if val is smaller than last element of min array, push it
                // otherwise copy the last element of min array to current index
                if (val < min[top - 1]) {
                    min[top] = val;
                } else {
                    min[top] = min[top - 1];
                }
            }
        }

        public void pop() {
            top--; // decrement the top pointer by 1

            // if stack is empty, mark it as empty,
            // because push behave differently for empty stack
            if (top == -1) empty = true;
        }

        public int top() {
            return arr[top];
        }

        public int getMin() {
            return min[top];
        }
    }

    class MinStack3 {
        private Stack<Integer> minStack;
        private Stack<Integer> stack;

        public MinStack3() {
            minStack = new Stack<Integer>();
            stack = new Stack<Integer>();
        }

        public void push(int val) {
            if (minStack.empty() || getMin() >= val) {
                // either the stack is empty, or the latest val is the min, so place inside minStack
                minStack.push(val);
            }
            stack.push(val);
        }

        public void pop() {
            if (stack.peek() == getMin()) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

}
