package LinkedList;

import java.util.List;

import LinkedList.LinkedList.ListNode;

public class ReorderLinkedList {
    

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast(5);
        list.addLast(10);
        list.addLast(15);
        //ListNode node = list.tail;
        list.addLast(52);
        list.addLast(112);
        list.addLast(55);
        list.addLast(200);
        list.addLast(205);
        //list.addLast(100);
        //list.tail.next = node;
        //list.printList(list.head); // Output: 5 -> 10 -> 15 -> null
        reorderList(list.head);
        //ListNode reverseHEad = reverseList(list.head);
        printList(list.head);
    }

    public static void reorderList(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head;

        //find the center of the list
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode reversHead = slow.next;
        slow.next = null;

        //reverse the list
        reversHead = reverseList(reversHead);

        ListNode toChange = head;
        boolean first = true;
        while(reversHead!=null) {
            if(first) {
                head = toChange.next;
                toChange.next = reversHead;
                toChange = reversHead;
                first = false;
            }
            else {
                reversHead = toChange.next;
                toChange.next = head;
                toChange = head;
                first = true;

            }
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode fwd = head.next;
            head.next = prev;
            prev = head;
            head = fwd;
        }
        return prev;
    }


    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }


}
