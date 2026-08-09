package REDO;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class CloneGraph {
    public static void main(String[] args) {
        // LeetCode example: 1-2, 1-4, 2-3, 3-4 (vals are 1..n, adj is 1-indexed)
        test("square", new int[][] {
                { 2, 4 },
                { 1, 3 },
                { 2, 4 },
                { 1, 3 } });

        test("empty graph (null in)", new int[][] {});
        test("single node, no neighbors", new int[][] { {} });
        test("two nodes", new int[][] {
                { 2 },
                { 1 } });

        // chain 1-2-3-4-5: catches clones that only copy one level deep
        test("chain", new int[][] {
                { 2 },
                { 1, 3 },
                { 2, 4 },
                { 3, 5 },
                { 4 } });

        // every node adjacent to every other: heavy revisiting
        test("complete K4", new int[][] {
                { 2, 3, 4 },
                { 1, 3, 4 },
                { 1, 2, 4 },
                { 1, 2, 3 } });

        // node 4 hangs off 3 only -- catches BFS that stops at depth 2
        test("tail", new int[][] {
                { 2, 3 },
                { 1 },
                { 1, 4 },
                { 3 } });
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int[][] adj) {
        Node original = build(adj);
        Map<Integer, List<Integer>> before = shape(original);

        Node clone = new CloneGraph().cloneGraph(original);

        List<String> problems = new ArrayList<>();

        if (original == null) {
            if (clone != null)
                problems.add("null input must return null, got node " + clone.val);
        } else {
            if (clone == null)
                problems.add("returned null for a non-empty graph");
            else {
                if (clone == original)
                    problems.add("returned the original node, not a copy");
                if (clone.val != original.val)
                    problems.add("entry node val " + clone.val + ", expected " + original.val);

                // no node object may be shared between the two graphs
                Set<Node> originals = Collections.newSetFromMap(new IdentityHashMap<>());
                originals.addAll(nodes(original));
                for (Node n : nodes(clone))
                    if (originals.contains(n)) {
                        problems.add("clone shares node object with val " + n.val);
                        break;
                    }

                // each val must map to exactly one object inside the clone
                Map<Integer, Node> byVal = new HashMap<>();
                for (Node n : nodes(clone)) {
                    Node prev = byVal.put(n.val, n);
                    if (prev != null && prev != n)
                        problems.add("val " + n.val + " duplicated as two objects in the clone");
                }

                Map<Integer, List<Integer>> cloneShape = shape(clone);
                if (!cloneShape.equals(before))
                    problems.add("shape " + cloneShape + ", expected " + before);
            }
        }

        // mutating the original (e.g. marking visited) is not allowed
        Map<Integer, List<Integer>> after = shape(original);
        if (!after.equals(before))
            problems.add("original graph was mutated: " + after + " was " + before);

        boolean pass = problems.isEmpty();
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        for (String p : problems)
            System.out.println("  " + p);
    }

    // adj is 1-indexed: adj[i] holds the neighbor vals of the node with val i+1
    static Node build(int[][] adj) {
        if (adj.length == 0)
            return null;
        Map<Integer, Node> byVal = new HashMap<>();
        for (int i = 1; i <= adj.length; i++)
            byVal.put(i, new Node(i));
        for (int i = 1; i <= adj.length; i++)
            for (int nb : adj[i - 1])
                byVal.get(i).neighbors.add(byVal.get(nb));
        return byVal.get(1);
    }

    // val -> sorted neighbor vals, for every reachable node. TreeMap so the
    // printed diff is readable and comparison ignores traversal order.
    static Map<Integer, List<Integer>> shape(Node entry) {
        Map<Integer, List<Integer>> out = new TreeMap<>();
        for (Node n : nodes(entry)) {
            List<Integer> vals = new ArrayList<>();
            for (Node nb : n.neighbors)
                vals.add(nb.val);
            Collections.sort(vals);
            out.put(n.val, vals);
        }
        return out;
    }

    // every node reachable from entry, by object identity
    static List<Node> nodes(Node entry) {
        List<Node> out = new ArrayList<>();
        if (entry == null)
            return out;
        Set<Node> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(entry);
        seen.add(entry);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            out.add(cur);
            for (Node nb : cur.neighbors)
                if (nb != null && seen.add(nb))
                    queue.add(nb);
        }
        return out;
    }

    HashMap<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        return dfs(node);
    }

    private Node dfs(Node ogNode) {
        if (map.containsKey(ogNode.val))
            return map.get(ogNode.val);
        Node n = new Node(ogNode.val);
        map.put(ogNode.val, n);
        for (Node neighbour : ogNode.neighbors) {
            n.neighbors.add(dfs(neighbour));
        }
        return n;
    }
}
