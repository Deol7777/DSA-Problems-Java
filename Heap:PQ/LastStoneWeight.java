import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    
        public static void main(String[] args){

		System.out.println("Hello, World!");
	
	}



    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.add(stone);
        }
        while (maxHeap.size() > 1) {
            int remain = maxHeap.poll() - maxHeap.poll();
            if(remain != 0)
                maxHeap.add(remain);
        }
        return maxHeap.size() == 0  ? 0 : maxHeap.peek();
    }
}
