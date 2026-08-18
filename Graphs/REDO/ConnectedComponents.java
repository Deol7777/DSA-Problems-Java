package REDO;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponents {
    // edges are UNDIRECTED: {a, b} connects a and b in both directions.
    // Return how many connected components the graph has.
    // Inputs contain no self-loops and no duplicate edges.

    public static void main(String[] args) {
        test("two components", 5,
                new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } }, 2);

        test("one component", 5,
                new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } }, 1);

        // no edges at all: every node is its own component
        test("no edges", 4, new int[][] {}, 4);
        test("single node", 1, new int[][] {}, 1);
        test("two nodes joined", 2, new int[][] { { 0, 1 } }, 1);

        // edges listed high-to-low: adjacency must be built in both directions
        test("edges listed backwards", 3, new int[][] { { 1, 0 }, { 2, 0 } }, 1);

        // a cycle is still ONE component -- counting n - edges would say 1 here
        test("triangle plus isolated node", 4,
                new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 } }, 2);

        test("three pairs", 6,
                new int[][] { { 0, 1 }, { 2, 3 }, { 4, 5 } }, 3);

        // chain {0,1,2}, pair {3,4}, plus loners {5} and {6}
        test("mixed sizes", 7,
                new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } }, 4);

        // fully connected square, extra edges do not add components
        test("dense cycle", 4,
                new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, 1);

        // the search must start from every node, not just node 0
        test("node 0 is isolated", 5,
                new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 } }, 2);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int n, int[][] edges, int expected) {
        int actual = new ConnectedComponents().countComponents(n, edges);
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    List<List<Integer>> adj = new ArrayList<>();
    boolean[] visited;
    int components = 0;

    public int countComponents(int n, int[][] edges) {
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            adj.add(temp);
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                dfs(i);
                components++;
            }
        }
        return components;
    }

    private void dfs(int cur) {
        if (visited[cur])
            return;
        visited[cur] = true;
        List<Integer> neighList = adj.get(cur);
        for (int i = 0; i < neighList.size(); i++) {
            dfs(neighList.get(i));
        }
    }
}
