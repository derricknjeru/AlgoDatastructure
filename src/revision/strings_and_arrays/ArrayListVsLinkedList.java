package revision.strings_and_arrays;

import java.util.ArrayList;
import java.util.LinkedList;

public class ArrayListVsLinkedList {
    //https://www.youtube.com/watch?v=M_0q6rGUsNc
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            linkedList.add(i);
            arrayList.add(i);
        }

        final long startTimeAL = System.nanoTime();
        arrayList.remove(5000);
        final long endTimeAL = System.nanoTime();

        final long startTimeLL = System.nanoTime();
        linkedList.remove(5000);
        final long endTimeLL = System.nanoTime();

        long totalTimeAL = endTimeAL - startTimeAL;
        long totalTimeLL = endTimeLL - startTimeLL;

        System.out.println("The array list time" + totalTimeAL);
        System.out.println("The linked list time" + totalTimeLL);


    }
}
