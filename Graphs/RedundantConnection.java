import java.util.Arrays;
import java.util.HashSet;

public class RedundantConnection {

    public static void main(String[] args) {
        int[][] pre = { {1, 2}, {1, 3}, {2, 3} };
        System.out.println(Arrays.toString(findRedundantConnection(pre)));
    }

    public static int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length+1);
        for (int[] edge : edges) {
            if(!uf.union(edge[0], edge[1]))
                return edge;
        }
        return new int[0];
    }

    static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        public int findRoot(int i) {
            if (parent[i] != i) {
                parent[i] = findRoot(parent[i]); // Path compression
            }
            return parent[i];
        }

        public boolean union(int x, int y) {

            int rootX = findRoot(x);
            int rootY = findRoot(y);

            if (rootX == rootY)
                return false;

            parent[rootY] = rootX;
            return true;
        }
    }
}
