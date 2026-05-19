import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConnectedComponents {
    public static void main(String[] args) {
        int[][] pre = { {0, 1}, {1, 2}, {2, 3}, {4, 5} };
        System.out.println(countComponents(6, pre));
    }

    public static int countComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int components = 0;
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        //since undirected need to add both sides to adj list
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < visited.length; i++) {
            if(!visited[i]) {
                dfs(i, adj, visited);
                components++;
            }
        }
        return components;


    }

    private static void dfs(int cur, List<List<Integer>> adj, boolean[] visited) {
        visited[cur] = true;
        for (Integer neigbour : adj.get(cur)) {
            //don't go back to a visited node
            if(visited[neigbour])
                continue;
            dfs(neigbour, adj, visited);
        }
    }
}
