package CodingInterviewPatterns.stack_and_queue.quickreview;

import java.util.Stack;

public class MinStackAlgo {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        //-- > Returns - 3.
        System.out.println("Min " + minStack.getMin());

        minStack.pop();

        // -- > Returns 0.
        System.out.println("Top " + minStack.top());

        //-- > Returns - 2.
        System.out.println("Min2 " + minStack.getMin());

    }

    static class MinStack {

        ListNode head;

        public MinStack() {

        }

        public void push(int val) {
            ListNode node = new ListNode(val);
            if(head==null){
                head = node;
                return;
            }

            node.next = head;
            node.min = Math.min(node.min, head.min);
            head = node;
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

        class ListNode {
            int val;
            ListNode next;
            int min;

            ListNode(int val){
                this.val = val;
                this.min = val;
            }
        }
    }


    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(val);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */



    class MinStack2 {
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack2() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public void pop() {
            if (stack.pop().equals(minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    class MinStack3 {

        /**
         * initialize your data structure here.
         */
        int min[] = null; // this array will maintain min number for each index
        int arr[] = null; // stack
        int top;
        boolean empty = true;

        public MinStack3() {
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

}
