package LinkedList;

import java.util.List;

public class LinkedList {

    ListNode head;
    ListNode tail;
    public int size;

    public LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public LinkedList(int value) {
        ListNode node = new ListNode(value);
        node.next = null;
        tail.next = null;
        head = node;
        tail = node;
        size = 1;
    }

    public void addLast(int val) {
        ListNode newNode = new ListNode(val);
    
        if (head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            tail = newNode;
        }
    
        size++;
    }

    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    static class ListNode {

        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }
}
