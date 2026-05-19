public class CoinChange {
        public static void main(String args[]) {

        int[] a = new int[]{1,5,10 b b }
        System.out.println(countSubstrings(str));
    }

    public static int coinChange(int[] coins, int amount) {
        
        int[] dp = new int[amount+1];
        for(int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if(i - coin == 0) 
                    dp[i] = 1;
                if(i- coin < 0 || dp[i - coin] == 0)
                    continue;
                min = Math.min(dp[i-coin], min);
            }
            dp[i] = min + 1;
        }
        return dp[amount];
    }
}
