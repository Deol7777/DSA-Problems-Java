import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCostToConnectPoints {

    public static void main(String[] args) {
        int[][] points = {
    {0, 0},
    {2, 2},
    {3, 3},
    {2, 4},
    {4, 2}
};
        System.out.println(minCostConnectPoints(points));

    }

    public static int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> minQueue = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[2]));

        //queue has [a,b,c] a & b are point index while c is the dist between them, queue based on min c
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                minQueue.add(new int[] { i, j, dist });
            }
        }

        int path = 0;
        int found = 0;
        UnionFind uf = new UnionFind(points.length);

        //keep taking the min dist out which are in different sets until (points - 1) paths are found
        //uses kruskal algorithm to find min points
        while( found < points.length-1) {
            int[] cur = minQueue.poll();
            if(uf.merge(cur[0], cur[1])) {
                path += cur[2];
                found++;
            }

        }

        return path;

    }

    static class UnionFind {

        int[] parents;
        public UnionFind(int n) {
            parents = new int[n];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        public int findRoot (int i) {
                        if (parents[i] != i) {
                parents[i] = findRoot(parents[i]); // Path compression
            }
            return parents[i];
        }

        public boolean merge(int i, int j) {
            int iRoot = findRoot(i);
            int jRoot = findRoot(j);

            if(iRoot == jRoot)
                return false;
            parents[jRoot] = iRoot;
            return true;
        }
    }
}
