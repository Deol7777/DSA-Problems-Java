package REDO;

import java.util.ArrayList;
import java.util.List;

public class GraphValidTree {
    // edges are UNDIRECTED: {a, b} connects a and b in both directions.
    // The graph is a valid tree iff it is fully connected AND has no cycle.
    // Inputs contain no self-loops and no duplicate edges.

    public static void main(String[] args) {
        test("classic tree", 5,
                new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } }, true);

        // connected, but the 1-2-3 triangle makes it a cycle
        test("connected with cycle", 5,
                new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 1, 3 }, { 1, 4 } }, false);

        test("single node", 1, new int[][] {}, true);
        test("two nodes joined", 2, new int[][] { { 0, 1 } }, true);

        // acyclic but not connected
        test("two nodes, no edge", 2, new int[][] {}, false);

        // both halves are trees on their own, the whole thing is not
        test("two disjoint trees", 4, new int[][] { { 0, 1 }, { 2, 3 } }, false);

        test("star", 4, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 } }, true);
        test("chain", 4, new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 } }, true);

        // edges listed high-to-low: adjacency must be built in both directions
        test("edges listed backwards", 3, new int[][] { { 1, 0 }, { 2, 0 } }, true);

        // triangle uses up all 3 edges, leaving node 3 stranded
        test("cycle plus isolated node", 4,
                new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 } }, false);

        // n-1 edges, the right count for a tree, but wired into a cycle + island
        test("right edge count, still not a tree", 5,
                new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 3, 4 } }, false);

        // enough edges to connect everything twice over
        test("dense cycle", 4,
                new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, false);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int n, int[][] edges, boolean expected) {
        boolean actual = new GraphValidTree().validTree(n, edges);
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    List<List<Integer>> adj = new ArrayList<>();
    boolean[] visited;
    int nodesVisited = 0;
    boolean isTree = true;

    public boolean validTree(int n, int[][] edges) {
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            adj.add(temp);
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        return (nodesVisited == n) && isTree;
    }

    private void dfs(int cur, int parent) {
        if (visited[cur] || !isTree) {
            isTree = false;
            return;
        }
        visited[cur] = true;
        nodesVisited++;
        List<Integer> curAdj = adj.get(cur);
        for (int i = 0; i < curAdj.size(); i++) {
            int neigh = curAdj.get(i);
            if (neigh == parent)
                continue;
            dfs(neigh, cur);
        }
    }
}
