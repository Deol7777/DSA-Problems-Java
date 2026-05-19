import java.util.PriorityQueue;

public class KthLargestInArray {

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        for (Integer num : nums) {
            if (minQueue.size() < k)
                minQueue.add(num);
            else {
                if (num > minQueue.peek()) {
                    minQueue.poll();
                    minQueue.add(num);
                }
            }
        }
        return minQueue.poll();
    }

}
