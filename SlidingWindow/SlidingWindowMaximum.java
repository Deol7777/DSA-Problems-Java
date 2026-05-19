package SlidingWindow;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.priority - a.priority);
        int[] a = new int[]{1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow(a, 3)));


        // while (!pq.isEmpty()) {
        //     System.out.println(pq.poll());  // Sorted by priority only
        // }
    }

    static class Node {
        int priority; // used for ordering
        int index; // stored, but not used for comparison

        public Node(int priority, int index) {
            this.priority = priority;
            this.index = index;
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.priority - a.priority);
        int[] sol = new int[nums.length - k + 1];
        for (int i = 0, j = i + k - 1; j < nums.length; i++, j++) {
            if(i == 0) {
                for (int l = 0; l <= j; l++) {
                    pq.add(new Node(nums[l], l));
                }
            }
            else {
                pq.add(new Node(nums[j], j));
            }

            while(pq.peek().index < i)
                pq.poll();
            sol[i] = pq.peek().priority;
        }
        return sol;

    }

}
