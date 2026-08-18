package REDO;

import java.util.Arrays;

public class HouseRobber2 {
    // Same as House Robber, but the houses are in a CIRCLE:
    // house 0 and house n-1 are adjacent, so you cannot rob both.
    // Return the max money you can rob.
    // 1 <= nums.length <= 100, 0 <= nums[i] <= 1000

    public static void main(String[] args) {
        test("leetcode example 1", new int[] { 2, 3, 2 }, 3);
        test("leetcode example 2", new int[] { 1, 2, 3, 1 }, 4);
        test("leetcode example 3", new int[] { 1, 2, 3 }, 3);

        test("single house", new int[] { 5 }, 5);
        test("two houses", new int[] { 2, 9 }, 9);

        // linear answer would be 12 (both ends) -- circle forbids it
        test("ends are adjacent now", new int[] { 6, 9, 6 }, 9);

        test("take 0 and 2, not adjacent in circle", new int[] { 10, 1, 10, 1 }, 20);

        // best is 1 + 3 = index 1 and 3, wrapping is what rules out 0 + 2
        test("skip first to win", new int[] { 5, 8, 6, 9 }, 17);

        test("all zeros", new int[] { 0, 0, 0, 0 }, 0);
        test("all equal", new int[] { 4, 4, 4, 4, 4 }, 8);

        // wrap-around trap: 0 and n-1 both huge but cannot both be taken
        test("big ends", new int[] { 200, 3, 140, 20, 10 }, 340);

        test("longer mix", new int[] { 2, 7, 9, 3, 1 }, 11);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int[] nums, int expected) {
        int actual = new HouseRobber2().rob(nums.clone());
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "] " + Arrays.toString(nums));
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);

        int[] dp = Arrays.copyOf(nums, nums.length);

        dp[1] = Math.max(dp[0], dp[1]);
        nums[2] = Math.max(nums[1], nums[2]);

        for (int i = 2; i < dp.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i] + dp[i - 2]);
        }
        for (int i = 3; i < dp.length; i++) {
            nums[i] = Math.max(nums[i - 1], nums[i] + nums[i - 2]);
        }
        return Math.max(nums[nums.length - 1], dp[dp.length - 2]);

    }
}
