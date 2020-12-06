package revision.tips;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class MinMaxHeap {
    public static void main(String[] args) {
        /**
         * We use PriorityQueue class to implement Heaps in Java.
         * By default Min Heap is implemented by this class.
         * To implement Max Heap, we use Collections.reverseOrder()
         */

       // createMinHeap();
        createMaxHeap();

    }

    private static void createMinHeap() {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Adding items to the pQueue using add()
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(20);
        minHeap.add(400);


        Iterator iterator = minHeap.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Removing the top priority element (or head) and
        // printing the modified pQueue using poll()
        System.out.println("Top item " + minHeap.poll());

        Iterator iterator2 = minHeap.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }


        // Check if an element is present using contains()
        boolean b = minHeap.contains(20);
        System.out.println("Priority queue contains 20 "
                + "or not?: " + b);

        // Getting objects from the queue using toArray()
        // in an array and print the array
        Object[] arr = minHeap.toArray();
        System.out.println("Value in array: ");
        for (int i = 0; i < arr.length; i++)
            System.out.println("Value: " + arr[i].toString());

        // Peek without removing the top priority element (or head) and
        // printing the modified pQueue using poll()
        System.out.println("Top item " + minHeap.peek());

        Iterator iterator3 = minHeap.iterator();
        while (iterator3.hasNext()) {
            System.out.println(iterator3.next());
        }
    }

    private static void createMaxHeap() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // Adding items to the pQueue using add()
        maxHeap.add(10);
        maxHeap.add(30);
        maxHeap.add(20);
        maxHeap.add(400);

        Iterator iterator = maxHeap.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Removing the top priority element (or head) and
        // printing the modified pQueue using poll()
        System.out.println("Top item " + maxHeap.poll());

        Iterator iterator2 = maxHeap.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }


        // Check if an element is present using contains()
        boolean b = maxHeap.contains(20);
        System.out.println("Priority queue contains 20 "
                + "or not?: " + b);

        // Getting objects from the queue using toArray()
        // in an array and print the array
        Object[] arr = maxHeap.toArray();
        System.out.println("Value in array: ");
        for (int i = 0; i < arr.length; i++)
            System.out.println("Value: " + arr[i].toString());

        // Peek without removing the top priority element (or head) and
        // printing the modified pQueue using poll()
        System.out.println("Top item " + maxHeap.peek());

        Iterator iterator3 = maxHeap.iterator();
        while (iterator3.hasNext()) {
            System.out.println(iterator3.next());
        }
    }
}
