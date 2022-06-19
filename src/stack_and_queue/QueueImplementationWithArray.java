package stack_and_queue;

public class QueueImplementationWithArray {
    //https://www.youtube.com/watch?v=YqrFeU90Coo
    private static class MyQueue {
        private int size = 5;
        int queue[];
        int front = -1;
        int rear = -1;

        MyQueue() {
            queue = new int[size];
        }

        public void enQueue(int val) {
            if (rear == size - 1) {
                System.out.println("Overflow");
            } else if (rear == -1 && front == -1) {
                rear = front = 0;
                queue[rear] = val;
            } else {
                rear++;
                queue[rear] = val;
            }
        }

        public void deQueue() {
            if (rear == -1 && front == -1) {
                System.out.println("The queue is empty");
            } else if (rear == front) {
                rear = front = -1;
            } else {
                front++;
            }
        }

        public void display() {
            if (rear == -1 && front == -1) {
                System.out.println("Queue is empty");
            } else {
                int i = front;
                while (i <= rear) {
                    System.out.println(queue[i]);
                    i++;
                }
            }
        }

        public int peek() {
            if (rear == -1 && front == -1) return -1;
            return queue[front];
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.enQueue(2);
        queue.enQueue(5);
        queue.enQueue(-1);
        queue.display();
        System.out.println("peak"+queue.peek());
        queue.deQueue();
        System.out.println("peak"+queue.peek());
        queue.display();

    }
}
