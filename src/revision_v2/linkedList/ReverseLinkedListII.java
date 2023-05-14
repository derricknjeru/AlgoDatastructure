package revision_v2.linkedList;

public class ReverseLinkedListII {
    ListNode head;

    public static void main(String[] args) {

    }


    // Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //https://www.youtube.com/watch?v=LnnJTODA77I
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null){
            return head;
        }

        ListNode dummy=new ListNode(-1);
        dummy.next=head;

        ListNode prev=dummy;

        for(int i=1;i<m;i++){
            prev=prev.next;
        }

        ListNode curr=prev.next;
        //p  c  n
        //1->2->3->4->5->NULL  m = 2, n = 4

        while(m++<n){
            ListNode temp=curr.next;
            curr.next=temp.next;
            temp.next=prev.next;
            prev.next=temp;

        }

        return dummy.next;
    }

}
