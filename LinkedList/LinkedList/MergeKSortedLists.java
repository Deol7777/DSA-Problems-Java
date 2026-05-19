package LinkedList;

import java.util.ArrayList;
import java.util.List;

import LinkedList.LinkedList.ListNode;

public class MergeKSortedLists {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        LinkedList list2 = new LinkedList();
        LinkedList list3 = new LinkedList();
        LinkedList list4 = new LinkedList();
        


        list.addLast(5);
        list.addLast(10);
        list.addLast(15);

        list2.addLast(6);
        list2.addLast(12);
        list2.addLast(13);

        list3.addLast(8);
        list3.addLast(10);
        list3.addLast(19);

        list4.addLast(3);
        list4.addLast(7);
        list4.addLast(11);
        ListNode[] arr = new ListNode[]{list.head, list2.head, list3.head, list4.head};
        ListNode newHead = mergeKLists(arr);
        list.printList(newHead); // Output: 5 -> 10 -> 15 -> null
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        if(lists.length == 0) {
            return null;
        }

        else if(lists.length == 1) {
            return lists[0];
        }

        List<ListNode> arrList = new ArrayList<>();
        List<ListNode> shorterList;
        for (ListNode node : lists) {
            arrList.add(node);
        }
        while(arrList.size() > 1) {
            shorterList = new ArrayList<>();
            for (int i = 0; i < arrList.size(); i+=2) {
                ListNode l1 = arrList.get(i);
                ListNode l2 = null;
                if(i+1 < arrList.size()){
                    l2 = arrList.get(i+1);
                }
                shorterList.add(merge2Lists(l1, l2));
            }
            arrList = shorterList;
        }

        return arrList.get(0);

        // while

        // ListNode merged = merge2Lists(lists[0], lists[1]);

        // for (int i = 2; i < lists.length; i++) {
        //     merged = merge2Lists(merged, lists[i]);
        // }

        // return merged;

    }

    public static ListNode merge2Lists(ListNode l1, ListNode l2) {
        
        if (l1 == null && l2 == null)
            return l1;
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode current = new ListNode();
        ListNode first = l1.val <= l2.val ? l1 : l2;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            }
            else{
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = l1 == null ? l2 : l1;
        return first;
    }

}
