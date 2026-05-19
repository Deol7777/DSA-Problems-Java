public class DecodeWays {
        public static void main(String args[]) {


        System.out.println(numDecodings("1123"));
    }

    public static int numDecodings(String s) {
        int[] dp = new int[s.length()+1];
        if(s.length() == 0 || 0 == Character.getNumericValue(s.charAt(0)))
            return 0;
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            
            int num = Character.getNumericValue(s.charAt(i-1));
            int prev = Character.getNumericValue(s.charAt(i-2));
            
            if(num == 0) {
                
                if(prev == 1 || prev == 2) {
                    if(i-2 >= 0 ) {
                        dp[i] = dp[i-2];
                    }
                    else{
                        dp[i] = 1;
                    }
                }
                else
                    return 0;
            }
            else {
                if( prev == 1 || prev == 2) {
                    if ( prev == 2 && num > 6)
                        dp[i] = dp[i-1];
                    else 
                        dp[i] = dp[i-1] + dp[i-2];
                }
                else
                    dp[i] = dp[i-1];
            }
        }
        return dp[dp.length-1];
    }
}
