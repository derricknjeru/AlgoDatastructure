package stack_and_queue;

public class MyCircularQueue {
    private int size=0;
    private int[] myQueue;
    int _front=-1;
    int _rear=-1;
    //https://leetcode.com/submissions/detail/725912128/?from=explore&item_id=1337
    //https://www.youtube.com/watch?v=dn01XST9-bI
    public MyCircularQueue(int k) {
        myQueue=new int[k];
        size=k;
    }

    public boolean enQueue(int value) {
        if(isEmpty()){
            _front=_rear=0;
            myQueue[_rear]=value;
            return true;

        }else if(isFull()){
            return false;
        }else{
            _rear = (_rear+1)%size;
            myQueue[_rear]=value;
            return true;
        }
    }

    public boolean deQueue() {
        if(isEmpty()){
            return false;
        }else if(_front ==_rear){
            _front=_rear=-1;
            return true;
        }else{
            _front = (_front+1)%size;
            return true;
        }
    }

    public int Front() {
        if(isEmpty()) return -1;
        return myQueue[_front];
    }

    public int Rear() {
        if(isEmpty()) return -1;
        return myQueue[_rear];
    }

    public boolean isEmpty() {
        return (_front==-1 && _rear==-1);
    }

    public boolean isFull() {
        return ((_rear+1)%size ==_front);
    }
}
