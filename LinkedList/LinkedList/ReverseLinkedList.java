package LinkedList;

import java.util.Arrays;

import LinkedList.LinkedList.ListNode;

public class ReverseLinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast(5);
        list.addLast(10);
        list.addLast(15);
        list.addLast(20);
        list.head = reverseList(list.head);
        list.printList(list.head); // Output: 5 -> 10 -> 15 -> null
    }

    public static ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode fwd;
        while(head != null) {
            fwd = head.next;
            head.next = prev;
            prev = head;
            head = fwd;
        }
        return prev;
    }

}
