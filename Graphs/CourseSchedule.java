import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {
    public static void main(String[] args) {
        int[][] pre = { {0,1}, {0,2}, {1,3}, {1,4}, {3,4}, {3, 0} };
        System.out.println(canFinish(5, pre));
    }

    //for each prequisite [0,1] there is an edge pointing from 0 to 1. The main logic is to use Kahn's
    //algorith to see if everything can be arranged in a topoligical sort. IF not, that means there is a cycle,
    //meanign it is impossible to take all the courses
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0)
            return true;
            
        //store the outpointing edges for each node    
        List<List<Integer>> adj = new ArrayList<>();
        
        //store number of incoming edges for each node
        int[] incoming = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        //fill the adjaceny list with empty lists
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        //put all the in adj list and fill incoming
        for (int i = 0; i < prerequisites.length; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
            incoming[prerequisites[i][1]]++;
            
        }

        //fill the queue initially
        for (int i = 0; i < incoming.length; i++) {
            if(incoming[i] == 0)
                queue.add(i);
        } 

        int taken = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            taken++;
            for (Integer neighbor : adj.get(cur)) {
                incoming[neighbor]--;
                if(incoming[neighbor] == 0)
                    queue.add(neighbor);
            }
        }

        //if all the nodes were removed from graph, means we can take the courses
        return taken == numCourses;
    }

    private static boolean dfs(int key, HashMap<Integer, List<Integer>> map, HashSet<Integer> visited, int[] taken, int numCourses) {
        if(visited.contains(key))
            return false;
        //boolean canTake = false;
        visited.add(key);
        List<Integer> list = map.get(key);
        if(list == null || list.isEmpty()) {
            //taken[0]++;
            return true;
        }
        for (Integer i : list) {
            if(!dfs(i, map, visited, taken, numCourses))
                return false;
            list.remove(i);
            // if(taken[0] >= numCourses)
            //     return true;
        }
        // if (canTake) {
        //     taken[0]++;
        // }
        return true;
    }
}