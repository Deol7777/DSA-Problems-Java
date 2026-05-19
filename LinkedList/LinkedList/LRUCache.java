package LinkedList;

import java.util.HashMap;

//import LinkedList.LinkedList.ListNode;
//import LinkedList.DoublyLinkedList.ListNode;

public class LRUCache {

    int size;
    int capacity;
    HashMap<Integer, ListNode> map;
    DoublyLinkedList list;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>();
        list = new DoublyLinkedList();
    }

    public int get(int key) {
        
        ListNode node = map.get(key);
        if (node == null)
            return -1;
        if(node != list.tail) {
            if(node != list.head) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            else{
                list.head = node.next;
                node.next.prev = null;
            }
            list.addLast(node.val, node.key);
            map.put(key, list.tail);
        }
        return node.val;
    }

    public void put(int key, int value) {
        ListNode node = map.get(key);
        if (node == null) {
            list.addLast(value, key);
            map.put(key, list.tail);
            this.size++;
            if (this.size > this.capacity) {
                int toRemoveKey = list.head.key;
                map.remove(toRemoveKey);
                list.head = list.head.next;
                this.size--;
            }
            
        }
        else{
            node.key = key;
            node.val = value;
            this.get(key);
        }
    }

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

      
    public static void main(String[] args) {
        
        LRUCache cache = new LRUCache(2);

        // Perform operations
        cache.put(1, 10);                 // cache: {1=10}
        System.out.println(cache.get(1)); // returns 10
        cache.put(2, 20);  
              cache.get(1);                // cache: {1=10, 2=20}
        cache.put(3, 30);
        //cache.get(1);                   // evicts key 1, cache: {2=20, 3=30}
        System.out.println(cache.get(2)); // returns 20
        System.out.println(cache.get(3)); // returns -1 (not found)

    }
}
