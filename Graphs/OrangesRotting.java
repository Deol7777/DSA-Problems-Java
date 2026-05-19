import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OrangesRotting {

    public static void main(String[] args) {
        int[][] array = { {0} };

        System.out.println(orangesRotting(array));
    }

    public static int orangesRotting(int[][] grid) {

        Queue<int[]> queue = new LinkedList<>();
        boolean noOranges = true;
        //add all rotten to the queue (multipoint bfs)
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2) {
                    queue.add(new int[]{i,j});
                    noOranges = false;
                }
            }
        }
        
        int[] iNext = {-1,0,1,0};
        int[] jNext = {0,-1,0,1};
        int minutesPassed = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cord = queue.poll();
                
                //rot the fresh neighbours and add them to the queue
                for (int k = 0; k < 4; k++) {
                    int r = cord[0] + iNext[k];
                    int c = cord[1] + jNext[k];
                    if(!isValid(r, c, grid))
                        continue;
                    grid[r][c] = 2;
                    queue.add(new int[]{r,c});
                }
            }
            minutesPassed++;
        }

        //if any fresh fruit remains return -1
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1)
                    return -1;
            }
        }

        //to handle edge case if there are no oranges
        return noOranges ? 0 : minutesPassed-1;
    }

    //any inbound index with fresh fruit is valid
    private static boolean isValid(int i, int j, int[][] grid) {
        if(i < 0 || i >= grid.length)
            return false;
        if(j < 0 || j >= grid[0].length)
            return false;
        if(grid[i][j] != 1)
            return false;
        return true;
    }
}
