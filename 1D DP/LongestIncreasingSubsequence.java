public class LongestIncreasingSubsequence {
    
    public static void main(String args[]) {

        int[] a = new int[]{0,3,1,3,2,3 };
        System.out.println(lengthOfLIS(a));
    }


    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int overallMax = 1;
        for (int i = 0; i < dp.length; i++) {
            int j = i-1;
            dp[i] = 1;
            while(j >= 0) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                j--;
            }
            overallMax = Math.max(overallMax, dp[i]);
        }
        return overallMax;
    }
}
