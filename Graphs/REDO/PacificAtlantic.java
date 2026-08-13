package REDO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlantic {
    // Pacific touches the top and left edges, Atlantic the bottom and right.
    // Water flows to a neighbor of equal or lower height.

    public static void main(String[] args) {
        test("leetcode example", new String[] {
                "12235",
                "32344",
                "24531",
                "67145",
                "51124" },
                new int[][] { { 0, 4 }, { 1, 3 }, { 1, 4 }, { 2, 2 }, { 3, 0 }, { 3, 1 }, { 4, 0 } });

        test("single cell", new String[] { "1" },
                new int[][] { { 0, 0 } });

        // every cell drains both ways
        test("2x2 alternating", new String[] {
                "21",
                "12" },
                new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } });

        // flat plateau: equal heights still flow, so everything reaches both
        test("plateau", new String[] {
                "111",
                "111",
                "111" },
                new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 },
                        { 2, 0 }, { 2, 1 }, { 2, 2 } });

        // rises toward the bottom-right: only the low-lying Atlantic side qualifies
        test("increasing diagonal", new String[] {
                "123",
                "234",
                "345" },
                new int[][] { { 0, 2 }, { 1, 2 }, { 2, 0 }, { 2, 1 }, { 2, 2 } });

        // a single row is both the top and the bottom edge
        test("single row", new String[] { "123" },
                new int[][] { { 0, 0 }, { 0, 1 }, { 0, 2 } });

        // a single column is both the left and the right edge
        test("single column", new String[] { "1", "2", "3" },
                new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } });
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, String[] rows, int[][] expected) {
        List<List<Integer>> actual = new PacificAtlantic().pacificAtlantic(toGrid(rows));
        List<String> a = canon(actual);
        List<String> e = new ArrayList<>();
        for (int[] cell : expected)
            e.add(cell[0] + "," + cell[1]);
        Collections.sort(e);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    // heights are single digits so the test grids stay readable
    static int[][] toGrid(String[] rows) {
        if (rows.length == 0)
            return new int[0][0];
        int[][] grid = new int[rows.length][rows[0].length()];
        for (int i = 0; i < rows.length; i++)
            for (int j = 0; j < rows[i].length(); j++)
                grid[i][j] = rows[i].charAt(j) - '0';
        return grid;
    }

    // "r,c" strings, sorted. Order of the returned cells is unspecified so sorting
    // is fine, but a Set would hide duplicate emissions -- those must fail.
    static List<String> canon(List<List<Integer>> cells) {
        List<String> out = new ArrayList<>();
        for (List<Integer> cell : cells)
            out.add(cell.get(0) + "," + cell.get(1));
        Collections.sort(out);
        return out;
    }

    private final int PACIFIC = 0;
    private final int ATLANTIC = 1;
    // private final int BOTH = 2;

    private boolean[][] pacific;
    private boolean[][] atlantic;

    private int[][] directions = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

    List<List<Integer>> sol = new ArrayList<>();

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        pacific = new boolean[heights.length][heights[0].length];
        atlantic = new boolean[heights.length][heights[0].length];
        Queue<int[]> queue = new LinkedList<>();

        // get all the edge locations in the queue first; adjust the oceans
        for (int i = 0; i < heights.length; i++) {

            // left row
            queue.add(new int[] { i, 0, PACIFIC });
            pacific[i][0] = true;

            // right row
            queue.add(new int[] { i, heights[0].length - 1, ATLANTIC });
            atlantic[i][heights[0].length - 1] = true;
        }

        for (int i = 0; i < heights[0].length; i++) {

            // top row
            queue.add(new int[] { 0, i, PACIFIC });
            pacific[0][i] = true;

            // bottom row
            queue.add(new int[] { heights.length - 1, i, ATLANTIC });
            atlantic[heights.length - 1][i] = true;

        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curPos = queue.poll();
                int water = curPos[2];
                for (int[] dir : directions) {
                    int k = curPos[0] + dir[0];
                    int l = curPos[1] + dir[1];
                    if (!isValid(heights, k, l, water, curPos[0], curPos[1]))
                        continue;
                    if (water == PACIFIC)
                        pacific[k][l] = true;
                    if (water == ATLANTIC)
                        atlantic[k][l] = true;
                    queue.add(new int[] { k, l, water });
                }
            }
        }

        // gather all the ones with both the oceans true
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (pacific[i][j] && atlantic[i][j])
                    sol.add(new ArrayList<>(Arrays.asList(i, j)));
            }
        }

        return sol;
    }

    private boolean isValid(int[][] grid, int i, int j, int ocean, int curI, int curJ) {
        if (i < 0 || i >= grid.length)
            return false;
        if (j < 0 || j >= grid[0].length)
            return false;
        if (ocean == PACIFIC && pacific[i][j])
            return false;
        if (ocean == ATLANTIC && atlantic[i][j])
            return false;
        if (grid[i][j] < grid[curI][curJ])
            return false;
        return true;
    }
}