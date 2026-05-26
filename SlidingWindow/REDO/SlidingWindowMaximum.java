package SlidingWindow.REDO;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.priority - a.priority);
        int[] a = new int[] { 1, 3, -1, -3, 5, 3, 6, 7 };
        System.out.println(Arrays.toString(maxSlidingWindow(a, 3)));

        // while (!pq.isEmpty()) {
        // System.out.println(pq.poll()); // Sorted by priority only
        // }
    }

    static class Node {
        int index;
        int priority;

        Node(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> b.priority - a.priority);
        int[] sol = new int[nums.length - k + 1];
        // for lopp to put ll eelements PQ uptil the window si
        for (int r = 0, l = 0; r < nums.length; r++) {
            queue.add(new Node(r, nums[r]));
            if (r < k - 1)
                continue;

            if (r - l + 1 > k)
                l++;

            while (queue.peek().index < l)
                queue.poll();
            sol[l] = queue.peek().priority;
        }
        return sol;

    }

}
