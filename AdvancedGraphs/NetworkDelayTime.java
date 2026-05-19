import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times = new int[][] {{1,2,1},{2,3,1},{1,4,4},{3,4,1}};
        System.out.println(networkDelayTime(times, 4, 1));

    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]));
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        int maxTime = Integer.MIN_VALUE;
        
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[k] = 0;
        visited[0] = true;
        
        //for a node, store all the neighbours and the dist from the node
        for (int i = 0; i < times.length; i++) {
            int origin = times[i][0];
            int dest = times[i][1];
            int disp = times[i][2];
            List<int[]> temp = map.getOrDefault(origin, new ArrayList<>());
            temp.add(new int[] { dest, disp });
            map.put(origin, temp);
        }

        //add source to the queue
        minHeap.add(new int[]{k, 0});
        
        while (!minHeap.isEmpty()) {
            int cur = minHeap.poll()[0];
            if(visited[cur])
                continue;
            visited[cur] = true;
            List<int[]> neighbours = map.get(cur);
            if(neighbours == null)
                continue;
            for (int[] neigh : neighbours) {
                if(visited[neigh[0]])
                    continue;
                dist[neigh[0]] = Math.min(dist[neigh[0]], dist[cur] + neigh[1]);
                minHeap.add(new int[]{neigh[0], dist[neigh[0]]});
            }
        }
        for (boolean reach : visited) {
            if(!reach)
                return -1;
        }
        for (int i = 1; i < dist.length; i++) {
            maxTime = Math.max(maxTime, dist[i]);
        }
        return maxTime;
    }
}