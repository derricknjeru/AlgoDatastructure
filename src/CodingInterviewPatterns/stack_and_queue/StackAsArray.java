package CodingInterviewPatterns.stack_and_queue;

public class StackAsArray {

    static class Stack {


        static final int max = 1000;
        int top;
        int[] stackArray = new int[max];

        Stack() {
            top = -1;
        }

        boolean isEmpty() {
            return top < 0;
        }

        boolean push(int x) {
            if (top >= max - 1) {
                System.out.println("Stack Overflow");
                return false;
            } else {
                stackArray[++top] = x;
                System.out.println(x + " pushed into stack");
                return true;
            }
        }

        int pop() {
            if (top < 0) {
                System.out.println("Stack Underflow");
                return 0;
            } else {
                return stackArray[top--];
            }
        }

        int peek() {
            if (top < 0) {
                System.out.println("Stack Underflow");
                return 0;
            } else {
                return stackArray[top];
            }
        }
    }


    public static void main(String[] args) {

        Stack s = new Stack();
        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println(s.pop() + " Popped from stack");
        System.out.println(s.pop() + " Popped from stack2");
        System.out.println(s.pop() + " Popped from stack3");
        System.out.println(s.pop() + " Popped from stack4");
    }


}
