package hackerrack_30_days_challenge;

import java.io.*;
import java.util.*;

class NodeTT {
    int data;
    NodeTT next;

    NodeTT(int d) {
        data = d;
        next = null;
    }

}

class RemoveDuplicateSortedLinkedList {

    public static NodeTT removeDuplicates(NodeTT head) {
        //Write your code here
        /*Another reference to head*/
        NodeTT curr = head;

        /* Traverse list till the last node */
        while (curr != null) {
            NodeTT temp = curr;
            /*Compare current node with the next node and
            keep on deleting them until it matches the current
            node data */
            while (temp != null && temp.data == curr.data) {
                temp = temp.next;
            }
            /*Set current node next to the next different
            element denoted by temp*/
            curr.next = temp;
            curr = curr.next;
        }

        return head;

    }

    public static NodeTT insert(NodeTT head, int data) {
        NodeTT p = new NodeTT(data);
        if (head == null)
            head = p;
        else if (head.next == null)
            head.next = p;
        else {
            NodeTT start = head;
            while (start.next != null)
                start = start.next;
            start.next = p;

        }
        return head;
    }

    public static void display(NodeTT head) {
        NodeTT start = head;
        while (start != null) {
            System.out.print(start.data + " ");
            start = start.next;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        NodeTT head = null;
        int T = sc.nextInt();
        while (T-- > 0) {
            int ele = sc.nextInt();
            head = insert(head, ele);
        }
        head = removeDuplicates(head);
        display(head);

    }
}