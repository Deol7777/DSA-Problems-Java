public class MaxAreaOfIslands {
    public static void main(String [] args) {
        int[][] grid = {
            {0, 1, 1, 0, 1},
            {1, 0, 1, 0, 1},
            {0, 1, 1, 0, 1},
            {0, 1, 0, 0, 1}
        };
        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int[] sol = {0};
        int maxArea = 0;
        int[] iNext = {-1,0,1,0};
        int[] jNext = {0, -1, 0, 1}; 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                {   
                    dfs(i, j, grid, iNext, jNext, sol);
                    maxArea = Math.max(maxArea, sol[0]);
                    sol[0] = 0;
                }

            }
        }
        return maxArea;
    }

    private static void dfs(int i, int j, int[][] grid, int[] iNext, int[] jNext, int[] sol) {
        if(!isValid(i, j, grid))
            return;
        sol[0]++;
        grid[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            dfs(i + iNext[k], j + jNext[k], grid, iNext, jNext, sol);
        }
    }

    private static boolean isValid(int i, int j, int[][] grid) {
        if ( i < 0 || i >= grid.length)
            return false;
        if ( j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] == 0)
            return false;
        return true;
    }

}
