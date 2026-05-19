import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlantic {
    public static void main(String[] args) {
        int[][] heights = { {1} };

        System.out.println(pacificAtlantic(heights));
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> sol = new ArrayList<>();
        boolean[][] pacific = new boolean[heights.length][heights[0].length];
        boolean[][] atlantic = new boolean[heights.length][heights[0].length];

        //top and bottom true for arrays ( can skip this part)
        for (int i = 0; i < heights[0].length; i++) {
            pacific[0][i] = true;
            atlantic[heights.length-1][i] = true;
        }

        //left and right for arrays ( can skip this part)
        for (int i = 0; i < heights.length; i++) {
            pacific[i][0] = true;
            atlantic[i][heights[0].length-1] = true;
        }
        int[] iNext = {-1,0,1,0};
        int[] jNext = {0,-1,0,1};

        //run dfs top and bottom true for arrays
        for (int i = 0; i < heights[0].length; i++) {
            dfs(0,i,heights, pacific,iNext, jNext);
            dfs(heights.length-1, i, heights, atlantic,iNext, jNext);
        }

        //run dfs left and right for arrays
        for (int i = 0; i < heights.length; i++) {
            dfs(i,0,heights, pacific,iNext, jNext);
            dfs(i, heights[0].length-1, heights, atlantic,iNext, jNext);
        }

        //reachable through both arrays is in the sol
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                if(pacific[i][j] && atlantic[i][j]) 
                    sol.add(new ArrayList<>(Arrays.asList(i,j)));
            }
        }
        return sol;

    }

    private static void dfs(int i, int j, int[][]grid, boolean[][] reachable, int[] iNext, int[]jNext) {
        reachable[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int r = i + iNext[k];
            int c = j + jNext[k];
            if(isValid(r,c , grid, reachable, grid[i][j]))
                dfs(r, c, grid, reachable, iNext, jNext);
            
        }
    }

    //any block lower than the current block is unreachable
    private static boolean isValid(int i, int j, int[][] grid, boolean[][] reachable, int cur) {
        if(i < 0 || i >= grid.length)
            return false;
        if(j < 0 || j >= grid[0].length)
            return false;
        if(reachable[i][j] == true)
            return false;
        if(grid[i][j] < cur)
            return false;
        return true;
    }

}
