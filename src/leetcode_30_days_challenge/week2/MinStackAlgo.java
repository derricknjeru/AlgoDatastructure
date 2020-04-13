package leetcode_30_days_challenge.week2;

import java.util.Stack;

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
        /**
         * initialize your data structure here.
         */
        int data;
        MinStack next;    //root===|data|data1| |data1|null|  ->null
        MinStack root;

        public MinStack() {
        }

        public MinStack(int d) {
            data = d;
            next = null;
        }

        public void push(int x) {
            MinStack newNode = new MinStack(x);
            if (root != null) {
                newNode.next = root;
            }
            root = newNode;

        }

        public void pop() {
            if (root != null) {
                root = root.next;
            }

        }


        public int top() {
            if (root != null) {
                return root.data;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        public int getMin() {
            int min = Integer.MAX_VALUE;
            // Check loop while head not equal to NULL
            MinStack head = root;
            while (head != null) {
                min = Math.min(min, head.data);
                head = head.next;
            }
            return min;
        }
    }
}
