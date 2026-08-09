package REDO;

public class MaxAreaOfIsland {
    public static void main(String[] args) {
        // LeetCode example: largest island is the 6-cell one in the lower right
        test("leetcode example", new String[] {
                "0010000100000",
                "0000000111000",
                "0110100000000",
                "0100110010100",
                "0100110011100",
                "0000000000100",
                "0000000111000",
                "0000000110000" }, 6);

        test("all water", new String[] {
                "000",
                "000" }, 0);

        test("all land", new String[] {
                "111",
                "111" }, 6);

        test("single cell land", new String[] { "1" }, 1);
        test("single cell water", new String[] { "0" }, 0);

        // two islands: 4 cells and 3 cells -> answer is the bigger one
        test("pick the larger", new String[] {
                "1100",
                "1100",
                "0010",
                "0011" }, 4);

        // diagonals do NOT connect -> every island is size 1
        test("checkerboard", new String[] {
                "101",
                "010",
                "101" }, 1);

        test("single row", new String[] { "1101011" }, 2);
        test("single column", new String[] { "1", "0", "1", "1" }, 2);
    }

    // fresh instance + fresh grid each call: solution may use instance fields and
    // may sink visited land in place
    static void test(String name, String[] rows, int expected) {
        int actual = new MaxAreaOfIsland().maxAreaOfIsland(toGrid(rows));
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    // grid is int[][] of 0/1 for this problem, not char[][]
    static int[][] toGrid(String[] rows) {
        int[][] grid = new int[rows.length][rows[0].length()];
        for (int i = 0; i < rows.length; i++)
            for (int j = 0; j < rows[i].length(); j++)
                grid[i][j] = rows[i].charAt(j) - '0';
        return grid;
    }

    int[][] directions = {
            { 0, -1 },
            { 0, 1 },
            { -1, 0 },
            { 1, 0 }
    };

    int maxAreaOverall = 0;
    int maxAreaLocal = 0;

    public int maxAreaOfIsland(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                    maxAreaOverall = Math.max(maxAreaLocal, maxAreaOverall);
                    maxAreaLocal = 0;
                }
            }
        }
        return maxAreaOverall;
    }

    private void dfs(int[][] grid, int i, int j) {
        if (!island(i, j, grid))
            return;
        grid[i][j] = 0;
        maxAreaLocal++;
        for (int[] dir : directions) {
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }

    private boolean island(int i, int j, int[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] == 0)
            return false;
        return true;
    }
}
