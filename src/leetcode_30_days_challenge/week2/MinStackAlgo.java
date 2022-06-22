package leetcode_30_days_challenge.week2;

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
}
