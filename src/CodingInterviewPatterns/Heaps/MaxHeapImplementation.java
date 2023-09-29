package CodingInterviewPatterns.Heaps;

import java.util.Arrays;

public class MaxHeapImplementation {
    private int capacity = 10;
    private int size = 0;

    int[] maxHeap = new int[capacity];

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
        return maxHeap[getLeftChildIndex(index)];
    }

    private int rightChild(int index) {
        return maxHeap[getRightChildIndex(index)];
    }

    private int parent(int index) {
        return maxHeap[getParentIndex(index)];
    }

    private void swap(int indexOne, int indexTwo) {
        int temp = maxHeap[indexOne];
        maxHeap[indexOne] = maxHeap[indexTwo];
        maxHeap[indexTwo] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            maxHeap = Arrays.copyOf(maxHeap, capacity * 2);
            capacity *= 2;
        }
    }

    private int peek() {
        if (size == 0) throw new IllegalStateException();
        return maxHeap[0];
    }

    private int poll() {
        if (size == 0) throw new IllegalStateException();
        int item = maxHeap[0];
        maxHeap[0] = maxHeap[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void add(int item) {
        ensureExtraCapacity();
        maxHeap[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index) < maxHeap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int largerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) > leftChild(index)) {
                largerChildIndex = getRightChildIndex(index);
            }

            if (maxHeap[index] > maxHeap[largerChildIndex]) {
                break;
            } else {
                swap(index, largerChildIndex);
            }

            index = largerChildIndex;
        }
    }

    public static void main(String[] args) {
        MaxHeapImplementation maxHeap = new MaxHeapImplementation();

        // Adding elements to the max-heap
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(15);
        maxHeap.add(3);
        maxHeap.add(8);

        // Peeking at the maximum element (without removing)
        System.out.println("Peek: " + maxHeap.peek()); // Should print "Peek: 15"

        // Removing and printing elements in descending order (maximum to minimum)
        while (maxHeap.size > 0) {
            int max = maxHeap.poll();
            System.out.println("Popped: " + max);
        }
    }
}

