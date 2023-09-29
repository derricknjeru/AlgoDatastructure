package CodingInterviewPatterns.Heaps;

import java.util.Arrays;

public class MinHeapImplementation {
    //https://www.youtube.com/watch?v=t0Cq6tVNRBA&ab_channel=HackerRank
    //Summary
    /**
     * All elements are smaller than the parent.
     * <p>
     * Inserting
     * -> When we insert the element we always insert it at the next empty slot at the bottom, then we heapifyUP it
     * to the right position
     * -> if the element is not in the right position we swap it with the parent, untill we find the right location.
     * <p>
     * Removing minimum element
     * Removing the minimum element, we remove the top element and we swap it with the last element, and we heapifyDown
     * <p>
     * Implementation
     * We implement using an array, And to get index we do: leftchild = parentIndex *2 + 1 and rightchild = parentIndex * 2 +1;
     */


    private int capacity = 10;
    private int size = 0;

    int[] minHeap = new int[capacity];

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private int leftChild(int index) {
        return minHeap[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return minHeap[getRightChildIndex(index)];
    }

    private int parent(int index) {
        return minHeap[getParentIndex(index)];
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = minHeap[indexOne];
        minHeap[indexOne] = minHeap[indexTwo];
        minHeap[indexTwo] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            minHeap = Arrays.copyOf(minHeap, capacity * 2);
            capacity *= 2;
        }
    }

    private int peek() {
        if (size == 0) throw new IllegalStateException();
        return minHeap[0];
    }

    private int poll() {
        if (size == 0) throw new IllegalStateException();
        //get top item and replace it with the bottom item
        int item = minHeap[0];
        minHeap[0] = minHeap[size - 1];
        size--;

        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();
        //add element to last sport
        minHeap[size] = item;
        size++;
        heapifyUP();
    }

    private void heapifyUP() {
        int index = size - 1;
        while (hasParent(index) && parent(index) > minHeap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        //we only need to check left child since if we don't have a left child, we don't have a right chlld
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (minHeap[index] < minHeap[smallerChildIndex]) {
                //current item is smaller than that of the two children
                //Everything is in order
                break;
            } else {
                swap(index, smallerChildIndex);
            }

            //move down to smaller child
            index = smallerChildIndex;
        }

    }

    public static void main(String[] args) {

        MinHeapImplementation minHeap = new MinHeapImplementation();

        // Adding elements to the min-heap
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(15);
        minHeap.add(3);
        minHeap.add(8);

        // Peeking at the minimum element (without removing)
        System.out.println("Peek: " + minHeap.peek()); // Should print "Peek: 3"

        // Removing and printing elements in ascending order (minimum to maximum)
        while (minHeap.size > 0) {
            int min = minHeap.poll();
            System.out.println("Popped: " + min);
        }
    }


}
