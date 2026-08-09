package REDO;

public class NumberOfIslands {
    public static void main(String[] args) {
        test("one big island", new String[] {
                "11110",
                "11010",
                "11000",
                "00000" }, 1);

        test("three islands", new String[] {
                "11000",
                "11000",
                "00100",
                "00011" }, 3);

        test("all water", new String[] {
                "000",
                "000" }, 0);

        test("all land", new String[] {
                "111",
                "111" }, 1);

        test("single cell land", new String[] { "1" }, 1);
        test("single cell water", new String[] { "0" }, 0);

        // diagonals do NOT connect
        test("checkerboard", new String[] {
                "101",
                "010",
                "101" }, 5);

        // one row / one column
        test("single row", new String[] { "1011011" }, 3);
        test("single column", new String[] { "1", "0", "1", "1" }, 2);
    }

    // fresh instance + fresh grid each call: solution may use instance fields and
    // may sink visited land in place
    static void test(String name, String[] rows, int expected) {
        int actual = new NumberOfIslands().numIslands(toGrid(rows));
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    static char[][] toGrid(String[] rows) {
        char[][] grid = new char[rows.length][];
        for (int i = 0; i < rows.length; i++)
            grid[i] = rows[i].toCharArray();
        return grid;
    }

    int[][] directions = {
            { 0, -1 },
            { 0, 1 },
            { -1, 0 },
            { 1, 0 }
    };

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (!island(i, j, grid))
            return;
        grid[i][j] = '0';
        for (int[] dir : directions) {
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }

    private boolean island(int i, int j, char[][] grid) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] == '0')
            return false;
        return true;
    }
}
