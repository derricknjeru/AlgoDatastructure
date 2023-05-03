package revision.tips;

import java.util.ArrayDeque;

public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<String> people = new ArrayDeque<>();
        /**
         *
         *Insert Elements to Deque
         * 1. Add elements using add(), addFirst() and addLast()
         *
         * add() - inserts the specified element at the end of the array deque
         * addFirst() - inserts the specified element at the beginning of the array deque
         * addLast() - inserts the specified at the end of the array deque (equivalent to add())
         */

        people.add("Derrick");
        people.add("Wanja");
        people.add("Njeru");

        System.out.println("ArrayDeque people" + people);

        for (String s : people) {
            System.out.println(s);
        }
        //iterate
        for (int i = 0; i < people.size(); i++) {
            //System.out.println(people.);
        }

        /**
         * Insert
         *
         * Insert elements using offer(), offerFirst() and offerLast()
         *
         * offer() - inserts the specified element at the end of the array deque
         * offerFirst() - inserts the specified element at the beginning of the array deque
         * offerLast() - inserts the specified element at the end of the array deque
         * Note.md: offer(), offerFirst() and offerLast() returns true if the element is successfully inserted; if the array deque is full, these methods return false.
         */

        ArrayDeque<String> animals = new ArrayDeque<>();
        // Using offer()
        animals.offer("Dog");

        // Using offerFirst()
        animals.offerFirst("Cat");

        // Using offerLast()
        animals.offerLast("Horse");
        System.out.println("ArrayDeque animals: " + animals);

        /**
         *  Access elements using getFirst() and getLast()
         *
         * getFirst() - returns the first element of the array deque
         * getLast() - returns the last element of the array deque
         * Note.md: If the array deque is empty, getFirst() and getLast() throws NoSuchElementException.
         */

        ArrayDeque<String> animals1 = new ArrayDeque<>();
        animals1.add("Dog");
        animals1.add("Cat");
        animals1.add("Horse");
        System.out.println("ArrayDeque: " + animals1);

        // Get the first element
        String firstElement = animals1.getFirst();
        System.out.println("First Element: " + firstElement);

        // Get the last element
        String lastElement = animals1.getLast();
        System.out.println("Last Element: " + lastElement);

        /**
         * ArrayDeque as a Stack
         * To implement a LIFO (Last-In-First-Out) stacks in Java, it is recommended to use a deque over the Stack class. The ArrayDeque class is likely to be faster than the Stack class.
         *
         * ArrayDeque provides the following methods that can be used for implementing a stack.
         *
         * push() - adds an element to the top of the stack
         * peek() - returns an element from the top of the stack
         * pop() - returns and removes an element from the top of the stack
         */
        ArrayDeque<String> stack = new ArrayDeque<>();

        // Add elements to stack
        stack.push("Dog");
        stack.push("Cat");
        stack.push("Horse");
        System.out.println("Stack: " + stack);

        // Access element from top of stack
        String element = stack.peek();
        System.out.println("Accessed Element: " + element);

        // Remove elements from top of stack
        String remElement = stack.pop();
        System.out.println("Removed element: " + remElement);
    }
}
