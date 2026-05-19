public class NumberOfIslands {
    public static void main(String [] args) {
        char[][] grid = {
            {'1', '1', '0', '0', '1'},
            {'1', '1', '0', '0', '1'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid));
    }

    public static int numIslands(char[][] grid) {
        int islands = 0;
        int[] iNext = {-1,0,1,0};
        int[] jNext = {0, -1, 0, 1}; 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1')
                {   
                    dfs(i, j, grid, iNext, jNext);
                    islands++;
                }

            }
        }
        return islands;
    }

    private static void dfs(int i, int j, char[][] grid, int[] iNext, int[] jNext) {
        if(!isValid(i, j, grid))
            return;
        grid[i][j] = '0';
        for (int k = 0; k < 4; k++) {
            dfs(i + iNext[k], j + jNext[k], grid, iNext, jNext);
        }
    }

    private static boolean isValid(int i, int j, char[][] grid) {
        if ( i < 0 || i >= grid.length)
            return false;
        if ( j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] == '0')
            return false;
        return true;
    }

}