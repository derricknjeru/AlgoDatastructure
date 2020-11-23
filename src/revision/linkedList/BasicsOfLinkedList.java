package revision.linkedList;

public class BasicsOfLinkedList {
    ListNode head = null;

    public static void main(String[] args) {
        BasicsOfLinkedList basics = new BasicsOfLinkedList();
        //push
        for (int i = 1; i < 10; i++) {
            basics.push(i);
        }
        basics.printList();

        //append at end
        basics.append(0);
        basics.printList();

    }

    private void append(int d) {
        //The new node
        ListNode newListNode = new ListNode(d);
        newListNode.next = null;

        if (head == null) {
            head = newListNode;
            return;
        }
        ListNode last = head;

        while (last.next != null) {
            last = last.next;
        }
        last.next = newListNode;

    }

    //push a node.
    private void push(int d) {
        ListNode newListNode = new ListNode(d);
        newListNode.next = head;
        head = newListNode;
    }

    //Print list.
    private void printList() {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
        System.out.println();

    }
}


