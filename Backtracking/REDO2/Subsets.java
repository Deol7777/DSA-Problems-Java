package REDO2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subsets {
    public static void main(String[] args) {
        test("empty", new int[] {}, new int[][] { {} });
        test("single", new int[] { 1 }, new int[][] { {}, { 1 } });
        test("two", new int[] { 1, 2 }, new int[][] { {}, { 1 }, { 2 }, { 1, 2 } });
        test("three", new int[] { 1, 2, 3 },
                new int[][] { {}, { 1 }, { 2 }, { 3 }, { 1, 2 }, { 1, 3 }, { 2, 3 }, { 1, 2, 3 } });
    }

    // fresh instance each call: sol/temp are instance fields
    static void test(String name, int[] nums, int[][] expected) {
        List<List<Integer>> actual = new Subsets().subsets(nums);
        Set<List<Integer>> a = canon(actual);
        Set<List<Integer>> e = canonArr(expected);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    // order-insensitive canonical form: sort each subset, collect to a Set
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

    public List<List<Integer>> subsets(int[] nums) {
        subsetsHelper(nums, 0);
        return sol;
    }

    private void subsetsHelper(int[] nums, int level) {
        if (level == nums.length) {
            sol.add(new ArrayList<>(temp));
            return;
        }
        subsetsHelper(nums, level + 1);
        temp.add(nums[level]);
        subsetsHelper(nums, level + 1);
        temp.remove(temp.size() - 1);
    }

}
