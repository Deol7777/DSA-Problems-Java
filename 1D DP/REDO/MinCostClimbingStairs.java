package REDO;

import java.util.Arrays;

public class MinCostClimbingStairs {
    // cost[i] is what you pay to STEP OFF index i.
    // You may start at index 0 or index 1.
    // After paying at i you climb 1 or 2 steps.
    // Return the min total cost to reach the top (index cost.length).
    // 2 <= cost.length <= 1000

    public static void main(String[] args) {
        test("leetcode example 1", new int[] { 10, 15, 20 }, 15);
        test("leetcode example 2",
                new int[] { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 }, 6);

        // length 2: pay the cheaper one and step straight to the top
        test("two steps, second cheaper", new int[] { 5, 1 }, 1);
        test("two steps, first cheaper", new int[] { 1, 5 }, 1);
        test("two equal steps", new int[] { 7, 7 }, 7);

        // zeros are free: start at 1, then hop the evens
        test("zeros let you skip", new int[] { 0, 0, 1, 1 }, 1);

        // last element is never forced -- you can land on the top from len-2
        test("expensive last step", new int[] { 1, 1, 1, 100 }, 2);

        // greedy "always take the cheaper next step" fails here
        test("greedy trap", new int[] { 1, 2, 4, 6, 2, 4, 6, 1 }, 12);

        test("all same cost", new int[] { 3, 3, 3, 3, 3 }, 6);
        test("all zeros", new int[] { 0, 0, 0, 0 }, 0);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int[] cost, int expected) {
        int actual = new MinCostClimbingStairs().minCostClimbingStairs(cost.clone());
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "] " + Arrays.toString(cost));
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    public int minCostClimbingStairs(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[cost.length - 1], cost[cost.length - 2]);
    }
}
