package REDO;

import java.util.LinkedList;
import java.util.Queue;

public class OrangesRotting {
    // 0 = empty, 1 = fresh, 2 = rotten

    public static void main(String[] args) {
        test("leetcode example 1", new String[] {
                "211",
                "110",
                "011" }, 4);

        // bottom-left fresh orange is cut off by the empty cells
        test("leetcode example 2 (unreachable)", new String[] {
                "211",
                "011",
                "101" }, -1);

        test("no fresh oranges at all", new String[] { "02" }, 0);
        test("single empty", new String[] { "0" }, 0);
        test("single rotten", new String[] { "2" }, 0);
        test("single fresh, no rotten", new String[] { "1" }, -1);

        // rotten exists but can never reach the fresh one
        test("blocked by empty", new String[] { "201" }, -1);

        // no rotten anywhere -> fresh can never rot
        test("no rotten source", new String[] {
                "11",
                "11" }, -1);

        // rot spreads from both ends at once, so 3 not 6
        test("two sources meet", new String[] { "21111112" }, 3);

        test("straight chain", new String[] { "21111" }, 4);

        // diagonals do NOT spread rot
        test("diagonal does not spread", new String[] {
                "20",
                "01" }, -1);

        test("empty grid", new String[] {}, 0);
    }

    // fresh instance + fresh grid each call: solution may use instance fields and
    // may rot oranges in place
    static void test(String name, String[] rows, int expected) {
        int actual = new OrangesRotting().orangesRotting(toGrid(rows));
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    static int[][] toGrid(String[] rows) {
        if (rows.length == 0)
            return new int[0][0];
        int[][] grid = new int[rows.length][rows[0].length()];
        for (int i = 0; i < rows.length; i++)
            for (int j = 0; j < rows[i].length(); j++)
                grid[i][j] = rows[i].charAt(j) - '0';
        return grid;
    }

    private int[][] directions = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

    public int orangesRotting(int[][] grid) {

        Queue<int[]> queue = new LinkedList<>();
        // count the no. of fresh oranges
        int newFreshCount = 0;
        int ogFreshCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1)
                    ogFreshCount++;
                if (grid[i][j] == 2)
                    queue.add(new int[] { i, j });
            }
        }
        if (ogFreshCount == 0)
            return 0;
        int timePassed = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            timePassed++;
            for (int i = 0; i < size; i++) {
                int[] curPos = queue.poll();
                for (int[] dir : directions) {
                    int newI = curPos[0] + dir[0];
                    int newJ = curPos[1] + dir[1];
                    if (!isValid(grid, newI, newJ))
                        continue;
                    grid[newI][newJ] = 2;
                    queue.add(new int[] { newI, newJ });
                    newFreshCount++;
                }
                if (ogFreshCount == newFreshCount)
                    return timePassed + 1;
            }
        }
        return -1;
    }

    private boolean isValid(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length)
            return false;
        if (j < 0 || j >= grid[0].length)
            return false;
        if (grid[i][j] != 1)
            return false;
        return true;
    }

}
