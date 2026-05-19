import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class IslandsAndTreasure {
    
    public static void main(String [] args) {
        int[][] grid = {
            {2147483647, -1, 0, 2147483647},
            {2147483647, 2147483647, 2147483647, -1},
            {2147483647, -1, 2147483647, -1},
            {0, -1, 2147483647, 2147483647}
        };
        islandsAndTreasure(grid);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + ", ");
            }
            System.out.println("");
        }

    }


        public static void islandsAndTreasure(int[][] grid) {
        int[] iNext = {-1,0,1,0};
        int[] jNext = {0, -1, 0, 1};
        int[] passed = {0}; 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0)
                {   
                    bfs(i, j, grid, iNext, jNext);
                }
                passed[0] = 0;

            }
        }
    }

    private static void bfs(int i, int j, int[][] grid, int[] iNext, int[] jNext) {
        int passed = 0;
        boolean first = true;
        Queue<Pair> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        Pair pair = new Pair(i, j);
        queue.add(pair);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int l = 0; l < size; l++) {
                Pair cord = queue.poll();
                if(!first && !isValid(cord.i, cord.j, grid, passed, set)) {
                    continue;
                }
                set.add(cord.getKeyforPair());
                grid[cord.i][cord.j] = passed;
                first = false;
                for (int k = 0; k < 4; k++) {
                    queue.add(new Pair(cord.i + iNext[k], cord.j + jNext[k]));
                }
            }
            passed++;
            
        }
        
        
    }

    private static boolean isValid(int i, int j, int[][] grid, int passed, HashSet<String> set) {
        if ( i < 0 || i >= grid.length)
            return false;
        if ( j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] == 0)
            return false;
        if (grid[i][j] == -1)
            return false;
        if (grid[i][j] < passed)
            return false;
        if (set.contains(new Pair(i, j).getKeyforPair()))
            return false;
        return true;
    }

    static class Pair {
        
        public int i;
        public int j;
        
        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
        public String getKeyforPair() {
            String key = new String(i + ";" + j);
            return key;
        }
        
    }

}
