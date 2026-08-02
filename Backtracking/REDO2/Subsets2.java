package REDO2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Subsets2 {
    public static void main(String[] args) {
        test("dups", new int[] { 1, 2, 2 },
                new int[][] { {}, { 1 }, { 1, 2 }, { 1, 2, 2 }, { 2 }, { 2, 2 } });
        test("zero", new int[] { 0 }, new int[][] { {}, { 0 } });
        test("all-same", new int[] { 4, 4, 4 },
                new int[][] { {}, { 4 }, { 4, 4 }, { 4, 4, 4 } });
        test("unsorted-dups", new int[] { 4, 4, 1, 4 },
                new int[][] { {}, { 1 }, { 4 }, { 1, 4 }, { 4, 4 }, { 1, 4, 4 }, { 4, 4, 4 }, { 1, 4, 4, 4 } });
        test("no-dups", new int[] { 1, 2, 3 },
                new int[][] { {}, { 1 }, { 2 }, { 3 }, { 1, 2 }, { 1, 3 }, { 2, 3 }, { 1, 2, 3 } });
    }

    // fresh instance each call: sol/temp are instance fields
    static void test(String name, int[] nums, int[][] expected) {
        List<List<Integer>> actual = new Subsets2().subsetsWithDup(nums);
        List<List<Integer>> a = canon(actual);
        List<List<Integer>> e = canonArr(expected);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    // NOT a Set: Subsets II requires the output contain NO duplicate subsets, so a
    // Set would silently hide a missing-dedupe bug. Sort each subset, then sort the
    // outer list -> emission order ignored, duplicate emissions still fail.
    static List<List<Integer>> canon(List<List<Integer>> lists) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> l : lists) {
            List<Integer> c = new ArrayList<>(l);
            Collections.sort(c);
            out.add(c);
        }
        out.sort(LEX);
        return out;
    }

    static List<List<Integer>> canonArr(int[][] arr) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] row : arr) {
            List<Integer> c = new ArrayList<>();
            for (int v : row)
                c.add(v);
            Collections.sort(c);
            out.add(c);
        }
        out.sort(LEX);
        return out;
    }

    // shortest-first, then element-wise
    static final Comparator<List<Integer>> LEX = (x, y) -> {
        if (x.size() != y.size())
            return Integer.compare(x.size(), y.size());
        for (int i = 0; i < x.size(); i++) {
            int cmp = Integer.compare(x.get(i), y.get(i));
            if (cmp != 0)
                return cmp;
        }
        return 0;
    };

    List<List<Integer>> sol = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        subsetsHelper(nums, 0);
        return sol;
    }

    private void subsetsHelper(int[] nums, int level) {
        if (level > nums.length - 1) {
            sol.add(new ArrayList<>(temp));
            return;
        }
        // if next element is the same, for the time case where the current is not
        // included,
        // make sure the index is passed to the next call is for the next distinct
        // element
        int toSkip = level;
        while (toSkip + 1 < nums.length && nums[toSkip] == nums[toSkip + 1])
            toSkip++;
        subsetsHelper(nums, toSkip + 1);
        temp.add(nums[level]);
        subsetsHelper(nums, level + 1);
        temp.remove(temp.size() - 1);

    }

}
