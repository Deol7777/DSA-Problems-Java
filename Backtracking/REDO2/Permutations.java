package REDO2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Permutations {
    public static void main(String[] args) {
        test("three", new int[] { 1, 2, 3 },
                new int[][] { { 1, 2, 3 }, { 1, 3, 2 }, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 }, { 3, 2, 1 } });
        test("two", new int[] { 0, 1 },
                new int[][] { { 0, 1 }, { 1, 0 } });
        test("single", new int[] { 5 }, new int[][] { { 5 } });
    }

    // fresh instance each call in case solution uses instance fields
    static void test(String name, int[] nums, int[][] expected) {
        List<List<Integer>> actual = new Permutations().permute(nums);
        Set<List<Integer>> a = canon(actual);
        Set<List<Integer>> e = canonArr(expected);
        boolean pass = a.equals(e);
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass) {
            System.out.println("  expected: " + e);
            System.out.println("  actual:   " + a);
        }
    }

    // order-SENSITIVE: keep element order (permutations differ by order); Set only
    // ignores the order the permutations are returned in.
    static Set<List<Integer>> canon(List<List<Integer>> lists) {
        Set<List<Integer>> out = new HashSet<>();
        for (List<Integer> l : lists)
            out.add(new ArrayList<>(l));
        return out;
    }

    static Set<List<Integer>> canonArr(int[][] arr) {
        Set<List<Integer>> out = new HashSet<>();
        for (int[] row : arr) {
            List<Integer> c = new ArrayList<>();
            for (int v : row)
                c.add(v);
            out.add(c);
        }
        return out;
    }

    Queue<Integer> queue = new LinkedList<>();
    List<Integer> temp = new ArrayList<>();
    List<List<Integer>> sol = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        for (int i : nums) {
            queue.add(i);
        }
        permuteHelper(nums);

        return sol;
    }

    private void permuteHelper(int[] nums) {
        if (queue.size() == 0)
            sol.add(new ArrayList<>(temp));
        for (int i = 0; i < queue.size(); i++) {
            int cur = queue.poll();
            temp.add(cur);
            permuteHelper(nums);
            temp.remove(temp.size() - 1);
            queue.add(cur);
        }
    }
}
