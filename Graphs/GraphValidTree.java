import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GraphValidTree {
    

    public static void main(String[] args) {
        int[][] pre = { {0, 1}, {1, 3}, {3, 2}, {1, 4} };
        System.out.println(validTree(5, pre));
    }

    //id there is a cycle detected (back to a visited node) OR you can't visited all the nodes then it is
    //not a valid tree
    public static boolean validTree(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        //since undirected need to add both sides to adj list
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return dfs(0, -1, adj, visited) && visited.size() == n;


    }

    private static boolean dfs(int cur, int parent, List<List<Integer>> adj, HashSet<Integer> visited) {
        if(visited.contains(cur))
            return false;
        visited.add(cur);
        for (Integer neigbour : adj.get(cur)) {
            //don't go back to parent
            if(neigbour == parent)
                continue;
            if(!dfs(neigbour, cur, adj, visited))
                return false;
        }
        return true;
    }
}
