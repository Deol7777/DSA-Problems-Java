package LinkedList;

import LinkedList.LinkedList.ListNode;

public class LinkedListCycle {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addLast(5);
        list.addLast(10);
        list.addLast(15);
        ListNode node = list.tail;
        list.addLast(52);
        list.addLast(112);
        list.addLast(55);
        //list.tail.next = node;
        //list.printList(list.head); // Output: 5 -> 10 -> 15 -> null
        System.out.println(hasCycle(list.head));
    }

    //this is preferred
    public static boolean hasCycle2(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast == null)
                break;
            fast = fast.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;

    }

    public static boolean hasCycle(ListNode head) {

        ListNode fast = head;
        while (fast != null) {
            if(fast.val == -10000)
                return true;
            fast.val = -10000;
            fast = fast.next;
        }
        return false;

    }
}
