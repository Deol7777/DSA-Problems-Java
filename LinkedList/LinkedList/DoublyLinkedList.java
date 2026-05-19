package LinkedList;

import java.util.List;

public class DoublyLinkedList {
    ListNode head;
    ListNode tail;
    public int size;

    public DoublyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public DoublyLinkedList(int value, int key) {
        ListNode node = new ListNode(value, key);
        node.next = null;
        head = node;
        tail = node;
        size = 1;
    }

    public void addLast(int val, int key) {
        ListNode newNode = new ListNode(val, key);
    
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
            tail = newNode;
        }
    
        size++;
    }

    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    static class ListNode {

        public int val;
        public int key;
        public ListNode next;
        public ListNode prev;

        public ListNode() {
        }

        public ListNode(int val, int key) {
            this.val = val;
            this.key = key;
            this.next = null;
            this.prev = null;
        }

        public ListNode(int val, int key, ListNode next, ListNode prev) {
            this.val = val;
            this.key = key;
            this.next = next;
            this.prev = prev;
        }

    }
}
