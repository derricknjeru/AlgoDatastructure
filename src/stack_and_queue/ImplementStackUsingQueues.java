package stack_and_queue;

import java.util.*;

public class ImplementStackUsingQueues {
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    private int top;


    public ImplementStackUsingQueues() {

    }

    public void push(int x) {
        q1.add(x);
        top = x;
    }

    public int pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        int pop = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return pop;
    }


    public int top() {
        return top;
    }

    public boolean empty() {
        return q1.isEmpty();
    }
}
