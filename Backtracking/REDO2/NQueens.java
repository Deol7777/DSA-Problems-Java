package REDO2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NQueens {
    public static void main(String[] args) {
        test("n=1", 1, new String[][] {
                { "Q" } });
        test("n=2 (no solution)", 2, new String[][] {});
        test("n=3 (no solution)", 3, new String[][] {});
        test("n=4", 4, new String[][] {
                { ".Q..", "...Q", "Q...", "..Q." },
                { "..Q.", "Q...", "...Q", ".Q.." } });
        // known counts; listing all boards is not useful
        testCount("n=5 count", 5, 10);
        testCount("n=6 count", 6, 4);
        testCount("n=8 count", 8, 92);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int n, String[][] expected) {
        List<List<String>> actual = new NQueens().solveNQueens(n);
        List<String> a = canon(actual);
        List<String> e = new ArrayList<>();
        for (String[] board : expected)
            e.add(String.join("|", board));
        Collections.sort(e);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    static void testCount(String name, int n, int expected) {
        List<List<String>> actual = new NQueens().solveNQueens(n);
        List<String> a = canon(actual);
        int distinct = a.stream().distinct().toArray().length;
        boolean pass = a.size() == expected && distinct == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected " + expected + " boards, got " + a.size()
                    + " (" + distinct + " distinct)");
    }

    // flatten each board to ".Q..|...Q|Q...|..Q." then sort: row order within a
    // board
    // matters, order of the boards does not. Sorted List, NOT a Set -- a Set would
    // hide duplicate emissions, those must fail.
    static List<String> canon(List<List<String>> boards) {
        List<String> out = new ArrayList<>();
        for (List<String> board : boards)
            out.add(String.join("|", board));
        Collections.sort(out);
        return out;
    }

    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> sol = new ArrayList<>();
    List<List<String>> sol2 = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        // fills sol with [[2,3,4,5], [1,2,4,3]] etc
        for (int i = 0; i < n; i++) {
            solveNQueensHelper(n, temp, 0, i);
        }

        // fil the sol2 array with actual '..Q..' type things
        // for each List on sol
        for (int i = 0; i < sol.size(); i++) {
            List<String> temp = new ArrayList<>();

            // for each index in this list ( each index is a '..Q.') line
            for (int j = 0; j < n; j++) {
                StringBuilder sb = new StringBuilder();
                int qPosition = sol.get(i).get(j);
                // create the String '..Q.'
                for (int k = 0; k < n; k++) {
                    if (k == qPosition)
                        sb.append('Q');
                    else
                        sb.append('.');
                }
                temp.add(sb.toString());
            }
            sol2.add(temp);
        }
        return sol2;
    }

    private void solveNQueensHelper(int n, List<Integer> temp, int i, int j) {

        if (!checkPositionValid(i, j, temp, n))
            return;
        temp.add(j);
        if (temp.size() == n) {
            sol.add(new ArrayList<>(temp));
        }
        for (int k = 0; k < n && i < n; k++) {
            solveNQueensHelper(n, temp, i + 1, k);
        }
        temp.remove(temp.size() - 1);
    }

    private boolean checkPositionValid(int i, int j, List<Integer> temp, int n) {
        if (i >= n)
            return false;

        // checl that temp doesn't have the column
        for (int k : temp) {
            if (k == j)
                return false;
        }
        // check left lower diagonal
        for (int k = temp.size() - 1, l = j - 1; k >= 0 && l >= 0; k--, l--) {
            if (temp.get(k) == l)
                return false;
        }

        // check right diagonal
        for (int k = i - 1, l = j + 1; k >= 0 && l < n; k--, l++) {
            if (temp.get(k) == l)
                return false;
        }

        return true;

    }

}
