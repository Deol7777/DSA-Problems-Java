import java.util.ArrayList;
import java.util.Arrays;

public class SorroundedRegions {
    public static void main(String[] args) {
        char[][] board = {
                { 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'O', 'X' },
                { 'X', 'O', 'O', 'X' },
                { 'X', 'X', 'X', 'O' }
        };

        solve(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void solve(char[][] board) {
        int[] iNext = { -1, 0, 1, 0 };
        int[] jNext = { 0, -1, 0, 1 };

        // for top and bottom
        for (int i = 0; i < board[0].length; i++) {
            dfs(0, i, board, iNext, jNext);
            dfs(board.length - 1, i, board, iNext, jNext);
        }

        // for left and right columns
        for (int i = 0; i < board.length; i++) {
            dfs(i, 0, board, iNext, jNext);
            dfs(i, board[0].length - 1, board, iNext, jNext);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'Y')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

    }

    private static void dfs(int i, int j, char[][] grid, int[] iNext, int[] jNext) {
        if (!isValid(i, j, grid))
            return;
        grid[i][j] = 'Y';
        for (int k = 0; k < 4; k++) {
            dfs(i + iNext[k], j + jNext[k], grid, iNext, jNext);
        }

    }

    private static boolean isValid(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length)
            return false;
        if (j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] == 'O')
            return true;
        return false;
    }
}
