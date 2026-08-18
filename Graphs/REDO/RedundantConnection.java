package REDO;

import java.util.Arrays;

public class RedundantConnection {
    // The input started as a tree on n nodes labelled 1..n, then ONE extra edge
    // was added, so edges.length == n. Return the edge that can be removed to
    // leave a tree. When several edges qualify, return the one that appears LAST
    // in the input. Edges are undirected, with no self-loops or repeated edges.

    public static void main(String[] args) {
        // whole graph is one triangle, so every edge qualifies -- the last wins
        test("triangle", new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } },
                new int[] { 2, 3 });

        test("triangle, extra edge listed last",
                new int[][] { { 1, 2 }, { 2, 3 }, { 3, 1 } },
                new int[] { 3, 1 });

        // cycle 1-2-3-4, plus a tail hanging off node 1 that is NOT removable
        test("cycle with a tail",
                new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } },
                new int[] { 1, 4 });

        // the closing edge of the cycle is NOT the last edge in the input
        test("cycle closes early",
                new int[][] { { 1, 4 }, { 3, 4 }, { 1, 3 }, { 1, 2 }, { 4, 5 } },
                new int[] { 1, 3 });

        // star from node 1, plus a chord between two of its leaves
        test("star plus chord",
                new int[][] { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 2, 4 } },
                new int[] { 2, 4 });

        // long chain joined end to end
        test("chain closed into a loop",
                new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 1, 5 } },
                new int[] { 1, 5 });

        // small cycle up front, long acyclic tail after it
        test("cycle first, tail after",
                new int[][] { { 2, 3 }, { 1, 2 }, { 1, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } },
                new int[] { 1, 3 });
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int[][] edges, int[] expected) {
        int[] actual = new RedundantConnection().findRedundantConnection(edges);
        boolean pass = Arrays.equals(actual, expected);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + Arrays.toString(expected)
                    + "  actual: " + Arrays.toString(actual));
    }

    public int[] findRedundantConnection(int[][] edges) {

        UnionFind uf = new UnionFind(edges.length);
        int[] sol = new int[2];
        for (int[] edge : edges) {
            int x = edge[0] - 1;
            int y = edge[1] - 1;
            if (uf.find(x) == uf.find(y)) {
                sol[0] = edge[0];
                sol[1] = edge[1];
            } else {
                uf.union(x, y);
            }
        }
        return sol;

    }

    class UnionFind {

        private int[] parents;
        private int[] rank;

        public UnionFind(int n) {
            parents = new int[n];
            rank = new int[n];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (parents[x] != x)
                parents[x] = find(parents[x]);
            return parents[x];
        }

        public void union(int x, int y) {
            int rootA = find(x);
            int rootB = find(y);

            if (rootA == rootB)
                return;

            else if (rank[rootA] < rank[rootB])
                parents[rootA] = rootB;

            else if (rank[rootA] > rank[rootB])
                parents[rootB] = rootA;

            else {
                parents[rootB] = rootA;
                rank[rootA]++;
            }

        }
    }
}
