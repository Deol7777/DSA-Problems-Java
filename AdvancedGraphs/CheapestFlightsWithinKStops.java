import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {
                { 0, 1, 200 },
                { 1, 2, 100 },
                { 1, 3, 300 },
                { 2, 3, 100 }
        };
        int src = 0;
        int dst = 3;
        int k = 1;
        System.out.println(findCheapestPrice(n, flights, src, dst, k));
    }

    // use the Bellman Ford algorithm - especially the property: after k iterations,
    // the shortest path with at most k edges is definitely found
    public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        int rounds = 0;
        boolean change = true;
        while (rounds <= k && change) {
            // we need this coz at each iter, we only want things i iterations away to be
            // updated
            int[] tempDist = Arrays.copyOf(dist, n);
            change = false;
            for (int[] arr : flights) {
                int u = arr[0];
                int v = arr[1];
                int edge = arr[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + edge < tempDist[v]) {
                    tempDist[v] = dist[u] + edge;
                    change = true;
                }
            }
            dist = tempDist;
            rounds++;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];

    }

    // using the BFS Search - unfortunately doesn;t work when u try to write stuff
    // to the same array :(, so you definitely need a temp array
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        // make a map of neighbours
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int[] arr : flights) {
            List<int[]> list = map.getOrDefault(arr[0], new ArrayList<>());
            list.add(new int[] { arr[1], arr[2] });
            map.put(arr[0], list);

        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        int round = 0;
        while (!queue.isEmpty() && round <= k) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int u = queue.poll();
                // get all the neighbours
                List<int[]> neigh = map.get(u);
                if (neigh == null)
                    continue;
                for (int[] nb : neigh) {
                    int v = nb[0];
                    int edge = nb[1];
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + edge < dist[v])
                        dist[v] = dist[u] + edge;
                    queue.add(v);
                }
            }

            round++;
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
