package REDO;

import java.util.Arrays;

public class HouseRobber {
    // nums[i] is the money in house i, houses in a straight line.
    // You cannot rob two ADJACENT houses (alarm goes off).
    // Return the max money you can rob.
    // 1 <= nums.length <= 100, 0 <= nums[i] <= 400

    public static void main(String[] args) {
        test("leetcode example 1", new int[] { 1, 2, 3, 1 }, 4);
        test("leetcode example 2", new int[] { 2, 7, 9, 3, 1 }, 12);

        test("single house", new int[] { 5 }, 5);
        test("two houses", new int[] { 2, 9 }, 9);
        test("three houses, middle biggest", new int[] { 2, 9, 3 }, 9);

        // taking the two ends beats the single big middle
        test("ends beat middle", new int[] { 6, 9, 6 }, 12);

        // greedy "grab biggest first" would take 8 and lose
        test("greedy trap", new int[] { 5, 8, 6, 9 }, 17);

        test("all zeros", new int[] { 0, 0, 0, 0 }, 0);

        // must skip two in a row to reach the payoff
        test("skip two", new int[] { 10, 1, 1, 10 }, 20);

        test("increasing", new int[] { 1, 2, 3, 4, 5, 6 }, 12);
        test("longer mix", new int[] { 2, 1, 1, 2 }, 4);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int[] nums, int expected) {
        int actual = new HouseRobber().rob(nums.clone());
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "] " + Arrays.toString(nums));
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    public int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        nums[1] = Math.max(nums[1], nums[0]);
        for (int i = 2; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1], nums[i - 2] + nums[i]);
        }
        return Math.max(nums[nums.length - 1], nums[nums.length - 2]);
    }
}
