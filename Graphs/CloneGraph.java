import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CloneGraph {
    public static void main(String[] args) {
        Graph graph = new Graph();

         // Step 1: Add all nodes (1 to 4)
        for (int i = 1; i <= 4; i++) {
            graph.addNode(i);
        }

        // Step 2: Add edges based on adjacency list
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Step 3: Print the graph to verify
        graph.printGraph();
        // Print graph
        //printAllNodes(graph.getNode(2));
        Node newNode = cloneGraph(graph.getNode(1));
        printAllNodes(newNode);
        printAllNodes(graph.getNode(1));

    }

    public static Node cloneGraph(Node node) {
        if(node == null)
                return null;
        Queue<Node> queue = new LinkedList<>();
        HashMap<Node, Node> map = new HashMap<>();
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curOldNode = queue.poll();
            Node curNewNode = map.get(curOldNode);
            for (Node neighbour : curOldNode.neighbors) {
                if(!map.containsKey(neighbour)) {
                    Node temp = new Node(neighbour.val);
                    map.put(neighbour, temp); 
                    queue.add(neighbour);
                }
                curNewNode.neighbors.add(map.get(neighbour));
            }
        
        }
        return newNode;
    }

    private static void printAllNodes(Node node) {
        HashSet<Integer> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node curOldNode = queue.poll();
            System.out.print(curOldNode.val + " ->");
            for (Node neighbour : curOldNode.neighbors) {
                System.out.print(" ," + neighbour.val);
                if(!set.contains(neighbour.val))
                    queue.add(neighbour);
                }
            set.add(curOldNode.val);
            System.out.println("");
        
        }
    }
}

