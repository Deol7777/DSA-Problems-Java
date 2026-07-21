package REDO2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {
    public static void main(String[] args) {
        test("basic", new int[] { 2, 3, 6, 7 }, 7,
                new int[][] { { 2, 2, 3 }, { 7 } });
        test("multi", new int[] { 2, 3, 5 }, 8,
                new int[][] { { 2, 2, 2, 2 }, { 2, 3, 3 }, { 3, 5 } });
        test("no-solution", new int[] { 2 }, 1, new int[][] {});
        test("single-hit", new int[] { 1 }, 2, new int[][] { { 1, 1 } });
    }

    // fresh instance each call in case solution uses instance fields
    static void test(String name, int[] candidates, int target, int[][] expected) {
        List<List<Integer>> actual = new CombinationSum().combinationSum(candidates, target);
        Set<List<Integer>> a = canon(actual);
        Set<List<Integer>> e = canonArr(expected);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    // order-insensitive: sort each combo, collect to a Set
    static Set<List<Integer>> canon(List<List<Integer>> lists) {
        Set<List<Integer>> out = new HashSet<>();
        for (List<Integer> l : lists) {
            List<Integer> c = new ArrayList<>(l);
            Collections.sort(c);
            out.add(c);
        }
        return out;
    }

    static Set<List<Integer>> canonArr(int[][] arr) {
        Set<List<Integer>> out = new HashSet<>();
        for (int[] row : arr) {
            List<Integer> c = new ArrayList<>();
            for (int v : row)
                c.add(v);
            Collections.sort(c);
            out.add(c);
        }
        return out;
    }

    List<List<Integer>> sol = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumHelper(candidates, target, 0, 0);
        return sol;
    }

    private void combinationSumHelper(int[] candidates, int target, int sum, int idx) {
        if (sum > target)
            return;
        if (sum == target)
            sol.add(new ArrayList<>(temp));
        for (int i = idx; i < candidates.length; i++) {
            temp.add(candidates[i]);
            combinationSumHelper(candidates, target, sum + candidates[i], i);
            temp.remove(temp.size() - 1);
        }
    }

}
