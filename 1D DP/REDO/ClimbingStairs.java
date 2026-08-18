package REDO;

public class ClimbingStairs {
    // You are climbing a staircase with n steps.
    // Each move you may climb either 1 or 2 steps.
    // Return how many DISTINCT ways you can reach the top.
    // 1 <= n <= 45, so the answer fits in an int.

    public static void main(String[] args) {
        test("n = 1", 1, 1);
        test("n = 2", 2, 2);
        test("n = 3", 3, 3);

        // ways follow Fibonacci: f(n) = f(n-1) + f(n-2)
        test("n = 4", 4, 5);
        test("n = 5", 5, 8);
        test("n = 6", 6, 13);

        test("n = 10", 10, 89);

        // naive recursion without memo blows up around here
        test("n = 30", 30, 1346269);

        // upper bound: must not overflow int
        test("n = 45", 45, 1836311903);
    }

    // fresh instance each call: solution may use instance fields
    static void test(String name, int n, int expected) {
        int actual = new ClimbingStairs().climbStairs(n);
        boolean pass = actual == expected;
        System.out.println((pass ? "PASS" : "FAIL") + " [" + name + "]");
        if (!pass)
            System.out.println("  expected: " + expected + "  actual: " + actual);
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[dp.length - 1];
    }
}
