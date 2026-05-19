import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule2 {
    public static void main(String[] args) {
        int[][] pre = { {1,0} };
        System.out.println(Arrays.toString(findOrder(2, pre)));
    }

    //for each prequisite [0,1] there is an edge pointing from 1 to 0. ( Reverse of CS1) The main logic is to use Kahn's
    //algorith to see if everything can be arranged in a topoligical sort. If not, that means there is a cycle,
    //meaning it is impossible to take all the courses
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        if(prerequisites.length == 0)
            return order;
            
        //store the outpointing edges for each node    
        List<List<Integer>> adj = new ArrayList<>();
        
        //store number of incoming edges for each node
        int[] incoming = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        //fill the adjaceny list with empty lists
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        //put all the neighbours in adj list and fill incoming
        for (int i = 0; i < prerequisites.length; i++) {
            adj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            incoming[prerequisites[i][0]]++;
            
        }

        //fill the queue initially
        for (int i = 0; i < incoming.length; i++) {
            if(incoming[i] == 0)
                queue.add(i);
        } 

        int orderIndex = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            order[orderIndex] = cur;
            orderIndex++;
            for (Integer neighbor : adj.get(cur)) {
                incoming[neighbor]--;
                if(incoming[neighbor] == 0)
                    queue.add(neighbor);
            }
        }

        //if all the nodes were removed from graph, means we can take the courses
        return orderIndex == numCourses ? order : new int[0];
    }
}
