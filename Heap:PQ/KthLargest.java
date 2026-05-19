import java.util.Collections;
import java.util.PriorityQueue;

class KthLargest {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    int size;

    public KthLargest(int k, int[] nums) {
        size = k;
        for (int i : nums)
            add(i);
    }
    
    public int add(int val) {
        if(minHeap.size() == size) {
            if(val < minHeap.peek())
                return minHeap.peek();
            minHeap.poll();
            minHeap.add(val);
            return minHeap.peek();
        }
        //if there are less elelemnts in heap than k, just add it
        minHeap.add(val);
        return minHeap.peek();
    }


    public static void main(String[] args){

		System.out.println("Hello, World!");
	
	}
}
