package REDO;

import java.util.Arrays;

public class SurroundedRegions {
    // An 'O' region is captured (flipped to 'X') only if no cell in it touches
    // the border. Diagonals do NOT connect.

    public static void main(String[] args) {
        test("leetcode example", new String[] {
                "XXXX",
                "XOOX",
                "XXOX",
                "XOXX" },
                new String[] {
                        "XXXX",
                        "XXXX",
                        "XXXX",
                        "XOXX" });

        // fully enclosed block gets captured, border corners survive
        test("inner block captured", new String[] {
                "OXXO",
                "XOOX",
                "XOOX",
                "OXXO" },
                new String[] {
                        "OXXO",
                        "XXXX",
                        "XXXX",
                        "OXXO" });

        // touching a border cell only diagonally is not touching it
        test("diagonal does not rescue", new String[] {
                "OXX",
                "XOX",
                "XXX" },
                new String[] {
                        "OXX",
                        "XXX",
                        "XXX" });

        // every cell is connected to the border, nothing changes
        test("all O", new String[] {
                "OOO",
                "OOO",
                "OOO" },
                new String[] {
                        "OOO",
                        "OOO",
                        "OOO" });

        test("all X", new String[] {
                "XXX",
                "XXX" },
                new String[] {
                        "XXX",
                        "XXX" });

        // a long region reaching the border drags the whole region to safety
        test("snake reaches border", new String[] {
                "XXXXX",
                "XOOOX",
                "XOXOX",
                "XOXOO",
                "XXXXX" },
                new String[] {
                        "XXXXX",
                        "XOOOX",
                        "XOXOX",
                        "XOXOO",
                        "XXXXX" });

        // every cell of a 1xN / Nx1 grid is on the border
        test("single row", new String[] { "XOXOO" },
                new String[] { "XOXOO" });
        test("single column", new String[] { "O", "X", "O" },
                new String[] { "O", "X", "O" });

        test("single cell O", new String[] { "O" }, new String[] { "O" });
        test("single cell X", new String[] { "X" }, new String[] { "X" });
    }

    // fresh instance + fresh grid each call: solution may use instance fields and
    // mutates the board in place
    static void test(String name, String[] rows, String[] expected) {
        char[][] board = toGrid(rows);
        new SurroundedRegions().solve(board);
        String[] actual = toRows(board);
        boolean pass = Arrays.equals(actual, expected);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + String.join(" | ", expected));
            System.out.println("  actual:   " + String.join(" | ", actual));
        }
    }

    static char[][] toGrid(String[] rows) {
        char[][] grid = new char[rows.length][];
        for (int i = 0; i < rows.length; i++)
            grid[i] = rows[i].toCharArray();
        return grid;
    }

    static String[] toRows(char[][] grid) {
        String[] rows = new String[grid.length];
        for (int i = 0; i < grid.length; i++)
            rows[i] = new String(grid[i]);
        return rows;
    }

    private int[][] directions = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            // left column
            if (board[i][0] == 'O')
                dfs(board, i, 0);

            // right column
            if (board[i][board[0].length - 1] == 'O')
                dfs(board, i, board[0].length - 1);

        }

        for (int j = 0; j < board[0].length; j++) {
            // top row
            if (board[0][j] == 'O')
                dfs(board, 0, j);

            // bottom row
            if (board[board.length - 1][j] == 'O')
                dfs(board, board.length - 1, j);

        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'C')
                    board[i][j] = 'O';
                else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        board[i][j] = 'C';
        for (int[] dir : directions) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (isValid(board, row, col))
                dfs(board, row, col);
        }
    }

    private boolean isValid(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length)
            return false;
        if (j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] != 'O')
            return false;
        return true;
    }
}
