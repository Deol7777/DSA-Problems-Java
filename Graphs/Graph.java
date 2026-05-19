import java.util.*;

public class Graph {
    private Map<Integer, Node> nodeMap;

    public Graph() {
        nodeMap = new HashMap<>();
    }

    // Add a node with given value
    public void addNode(int val) {
        nodeMap.putIfAbsent(val, new Node(val));
    }

    // Add an undirected edge between two nodes
    public void addEdge(int val1, int val2) {
        Node node1 = nodeMap.get(val1);
        Node node2 = nodeMap.get(val2);

        if (node1 != null && node2 != null) {
            node1.neighbors.add(node2);
            node2.neighbors.add(node1); // remove this line for a directed graph
        }
    }

    // Get node by value
    public Node getNode(int val) {
        return nodeMap.get(val);
    }

    // Print adjacency list for debugging
    public void printGraph() {
        for (int key : nodeMap.keySet()) {
            System.out.print("Node " + key + " connects to: ");
            for (Node neighbor : nodeMap.get(key).neighbors) {
                System.out.print(neighbor.val + " ");
            }
            System.out.println();
        }
    }
}
