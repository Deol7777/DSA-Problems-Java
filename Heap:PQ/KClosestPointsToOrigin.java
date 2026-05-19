import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public static void main(String[] args) {

        int[][] cord = {
                { 0, 2 },
                { 2, 0 },
                { 2, 2 }
        };
        
        //System.out.println(Arrays.toString(kClosest(cord, 2)));

    }

    public static int[][] kClosest(int[][] points, int k) {
        int[][] sol = new int[k][2];
        int idx = 0;
        PriorityQueue<Double> maxHeap = new PriorityQueue<>();
        for (int[] pair : points) {
            double dist = Math.sqrt(pair[0] ^ 2 + pair[1] ^ 2);
            if (maxHeap.size() >= k) {
                if (dist >= maxHeap.peek())
                    continue;
                maxHeap.poll();
            }
            maxHeap.add(dist);
            sol[idx % k][0] = pair[0];
            sol[idx % k][1] = pair[1];
            idx++;
        }
        return sol;
    }

}
