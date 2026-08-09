package REDO;

import java.nio.charset.CoderResult;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class IslandsAndTreasure {
    // -1 = water/wall, 0 = treasure chest/gate, INF = empty land
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        test("leetcode example", new int[][] {
                { INF, -1, 0, INF },
                { INF, INF, INF, -1 },
                { INF, -1, INF, -1 },
                { 0, -1, INF, INF } },
                new int[][] {
                        { 3, -1, 0, 1 },
                        { 2, 2, 1, -1 },
                        { 1, -1, 2, -1 },
                        { 0, -1, 3, 4 } });

        test("single gate", new int[][] { { 0 } }, new int[][] { { 0 } });
        test("single wall", new int[][] { { -1 } }, new int[][] { { -1 } });
        test("single unreachable land", new int[][] { { INF } }, new int[][] { { INF } });

        // no gates at all -> nothing changes
        test("no gates", new int[][] {
                { INF, INF },
                { INF, INF } },
                new int[][] {
                        { INF, INF },
                        { INF, INF } });

        // two gates: each cell takes the nearer one, not the first one found
        test("nearest of two gates", new int[][] {
                { 0, INF, INF, 0 } },
                new int[][] {
                        { 0, 1, 1, 0 } });

        // gate is sealed in by walls -> the rest stays INF
        test("gate walled off", new int[][] {
                { 0, -1, INF },
                { -1, -1, INF },
                { INF, INF, INF } },
                new int[][] {
                        { 0, -1, INF },
                        { -1, -1, INF },
                        { INF, INF, INF } });

        // BFS must go around the wall, not through it
        test("must go around", new int[][] {
                { 0, -1, INF },
                { INF, -1, INF },
                { INF, INF, INF } },
                new int[][] {
                        { 0, -1, 6 },
                        { 1, -1, 5 },
                        { 2, 3, 4 } });

        test("empty grid", new int[][] {}, new int[][] {});
    }

    // solution writes into the grid in place, so it gets its own copy
    static void test(String name, int[][] grid, int[][] expected) {
        int[][] actual = copy(grid);
        new IslandsAndTreasure().islandsAndTreasure(actual);
        boolean pass = equal(actual, expected);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected:");
            print(expected);
            System.out.println("  actual:");
            print(actual);
        }
    }

    static int[][] copy(int[][] grid) {
        int[][] out = new int[grid.length][];
        for (int i = 0; i < grid.length; i++)
            out[i] = grid[i].clone();
        return out;
    }

    static boolean equal(int[][] a, int[][] b) {
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length)
                return false;
            for (int j = 0; j < a[i].length; j++)
                if (a[i][j] != b[i][j])
                    return false;
        }
        return true;
    }

    // INF prints as "INF" so a failing grid stays readable
    static void print(int[][] grid) {
        for (int[] row : grid) {
            StringBuilder sb = new StringBuilder("    ");
            for (int v : row)
                sb.append(String.format("%4s", v == INF ? "INF" : String.valueOf(v)));
            System.out.println(sb);
        }
    }

    // int[][] directions = {
    // { 0, -1 },
    // { 0, 1 },
    // { -1, 0 },
    // { 1, 0 }
    // };

    // HashSet<String> set = new HashSet<>();

    // public void islandsAndTreasure(int[][] grid) {
    // for (int i = 0; i < grid.length; i++) {
    // for (int j = 0; j < grid[0].length; j++) {
    // // if treasure found
    // if (grid[i][j] == 0) {
    // dfs(grid, i, j, 0);
    // set.clear();
    // }
    // }
    // }
    // }

    // void dfs(int[][] grid, int i, int j, int distance) {
    // if (!isValid(grid, i, j, distance))
    // return;
    // grid[i][j] = Math.min(distance, grid[i][j]);
    // set.add(i + ";" + j);
    // for (int[] dir : directions) {
    // dfs(grid, i + dir[0], j + dir[1], distance + 1);
    // }

    // }

    // private boolean isValid(int[][] grid, int i, int j, int distance) {
    // if (i < 0 || i >= grid.length)
    // return false;
    // if (j < 0 || j >= grid[0].length)
    // return false;
    // if (grid[i][j] == -1)
    // return false;
    // if (set.contains(i + ";" + j))
    // return false;
    // if (distance != 0 && grid[i][j] == 0)
    // return false;
    // return true;
    // }

    int[][] directions = {
            { 0, -1 },
            { 0, 1 },
            { -1, 0 },
            { 1, 0 }
    };

    HashSet<String> set = new HashSet<>();
    Queue<Coord> queue = new LinkedList<>();

    public void islandsAndTreasure(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // if treasure found
                if (grid[i][j] == 0) {
                    queue.add(new Coord(i, j));
                    // set.clear();
                }
            }
        }
        bfs(grid);
    }

    void bfs(int[][] grid) {
        int distance = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int k = 0; k < size; k++) {
                Coord extracted = queue.poll();
                int i = extracted.i;
                int j = extracted.j;

                grid[i][j] = Math.min(distance, grid[i][j]);
                for (int[] dir : directions) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (!isValid(grid, newI, newJ))
                        continue;
                    set.add(i + ";" + j);
                    queue.add(new Coord(newI, newJ));
                }
            }
        }

    }

    private boolean isValid(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length)
            return false;
        if (j < 0 || j >= grid[0].length)
            return false;
        if (set.contains(i + ";" + j))
            return false;
        if (grid[i][j] != Integer.MAX_VALUE)
            return false;
        return true;
    }

    class Coord {
        public int i;
        public int j;

        public Coord(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
