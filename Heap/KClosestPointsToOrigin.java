import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Map;

public class KClosestPointsToOrigin {

    public static void main(String[] args) {

        int[][] cord = {
                { 0, 1 },
                { 1, 0 }
        };
        int k = 2;
        int[][] sol = kClosest(cord, k);
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println("");
        }
        // System.out.println(Arrays.toString(kClosest(cord, 2)));

    }

    // doesn't work coz dist not unique
    public static int[][] kClosest2(int[][] points, int k) {

        int[][] sol = new int[k][2];

        // map would be used to store the indeces of the current things in the heap(to
        // recreate sol later)
        HashMap<Double, Integer> map = new HashMap<>();
        PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < points.length; i++) {
            double dist = Math.sqrt(points[i][0] * points[i][0] + points[i][1] * points[i][1]);

            // only bother if heap already full
            if (maxHeap.size() >= k) {
                if (dist >= maxHeap.peek())
                    continue;

                // if cur is smaller than the top of heap then we remove top of heap and from
                // the map
                double toRemove = maxHeap.poll();
                map.remove(toRemove);
            }
            maxHeap.add(dist);
            map.put(dist, i);
        }
        int i = 0;
        for (Integer idx : map.values()) {
            sol[i][0] = points[idx][0];
            sol[i][1] = points[idx][1];
            i++;
        }
        return sol;
    }

    public static int[][] kClosest(int[][] points, int k) {

        int[][] sol = new int[k][2];

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[0]).reversed());
        
        for (int i = 0; i < points.length; i++) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];

            // only bother if heap already full
            if (maxHeap.size() >= k) {
                if (dist >= maxHeap.peek()[0])
                    continue;

                // if cur is smaller than the top of heap then we remove top of heap and from
                // the map
                maxHeap.poll();
            }
            //add an array where the first entry is the dist and rest two are the points themselves
            maxHeap.add(new int[]{dist, points[i][0], points[i][1] });
        }
        int i = 0;
        while(!maxHeap.isEmpty()) {
            int[] temp = maxHeap.poll();
            sol[i][0] = temp[1];
            sol[i][1] = temp[2];
            i++;
        }
        return sol;
    }

}
