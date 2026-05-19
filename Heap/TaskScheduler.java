import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {
    

    public static void main(String[] args){

		char[] values = {'A', 'C', 'A', 'B', 'D', 'B'};
        System.out.println(leastInterval(values, 1));

	
	}

    public static int leastInterval(char[] tasks, int n) {
        
        HashMap<Character, Integer> map = new HashMap<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Queue<int[]> queue  = new LinkedList<>();
        //count everything up
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) +1);
        }
        maxHeap.addAll(map.values());
        for (int time = 0;; time++) {
            if(!maxHeap.isEmpty()) {
                int top = maxHeap.poll();
                    if(top != 1)
                        queue.add(new int[] {top-1, time + n});
            }

            //id something was just processed and not 0, it would be in the queue
            if(queue.isEmpty() && maxHeap.isEmpty())
                return time+1;

            //if frontrunner in queue is at current time add to the heap
            if(time == queue.peek()[1])
                maxHeap.add(queue.poll()[0]);
        }
    }
}
