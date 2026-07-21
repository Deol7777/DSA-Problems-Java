package REDO2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum2 {
    public static void main(String[] args) {
        test("dups", new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8,
                new int[][] { { 1, 1, 6 }, { 1, 2, 5 }, { 1, 7 }, { 2, 6 } });
        test("all-twos", new int[] { 2, 5, 2, 1, 2 }, 5,
                new int[][] { { 1, 2, 2 }, { 5 } });
        test("no-solution", new int[] { 3, 4, 5 }, 2, new int[][] {});
        test("single-use", new int[] { 1, 1 }, 2, new int[][] { { 1, 1 } });
    }

    // fresh instance each call in case solution uses instance fields
    static void test(String name, int[] candidates, int target, int[][] expected) {
        List<List<Integer>> actual = new CombinationSum2().combinationSum2(candidates, target);
        Set<List<Integer>> a = canon(actual);
        Set<List<Integer>> e = canonArr(expected);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    // order-insensitive: sort each combo, collect to a Set (dedupes duplicate
    // combos too)
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

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSumHelper(candidates, target, 0, 0);
        return sol;
    }

    private void combinationSumHelper(int[] candidates, int target, int sum, int idx) {
        if (sum > target)
            return;
        if (sum == target)
            sol.add(new ArrayList<>(temp));
        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == i - 1)
                continue;
            temp.add(candidates[i]);
            combinationSumHelper(candidates, target, sum + candidates[i], i + 1);
            temp.remove(temp.size() - 1);
        }
    }

}
