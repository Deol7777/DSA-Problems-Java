package LinkedList;

import LinkedList.LinkedList.ListNode;

public class MergeTwoSortedLinkedLists {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        LinkedList list2 = new LinkedList();
        // list.addLast(5);
        // list.addLast(10);
        // list.addLast(15);

        // list2.addLast(6);
        // list2.addLast(12);
        // list2.addLast(13);

        ListNode newHead = mergeTwoLists(list.head, list2.head);
        list.printList(newHead); // Output: 5 -> 10 -> 15 -> null
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        if (list1 == null && list2 == null)
            return list1;
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode current = new ListNode();
        ListNode first = list2.val < list1.val ? list2 : list1;
        while(list1 != null && list2 != null) {
        
            if(list2.val < list1.val) {
                current.next = list2;
                list2 = list2.next;
            }
            else {
                current.next = list1;
                list1 = list1.next;
            }
            current = current.next;
        }

        current.next = list1 == null ? list2 : list1;

        return first;

    }
}
