package CodingInterviewPatterns.InterestingQuiz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListAsLinkedList {

    public static void main(String[] args) {
        List<String> result = getResult();
        System.out.println(result);

        List<String> result2 = getResult();
        System.out.println(result2);
    }

    public static List<String> getResult() {
        LinkedList<String> linkedList = new LinkedList();
        linkedList.add("Hello");
        linkedList.add("World");
        return linkedList; // Returning LinkedList as List
    }

    public static List<String> getResult2() {
        List<String> linkedList = new ArrayList<>();
        linkedList.add("Hello");
        linkedList.add("World");
        return linkedList; // Returning LinkedList as List
    }

}
