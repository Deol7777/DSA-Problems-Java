package SlidingWindow;

import java.util.Arrays;

public class BestTimeTOBuyAndSell {
        public static void main(String[] args) {
        int[] temp = new int[]{10,8,7,5,2};
        System.out.println(maxProfit(temp));
    }

    // public static int maxProfit(int[] prices) {
    //     int maxProfit = 0;
    //     int currSmallest = Integer.MAX_VALUE;
    //     for (int i : prices) {
    //         currSmallest = Math.min(i, currSmallest);
    //         maxProfit = Math.max(maxProfit, i - currSmallest);
    //     }
    //     return maxProfit;
    // }

    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0, j = 1; j < prices.length; j++) {
            maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            if(prices[j] < prices[i])
                i = j;
        }
        return maxProfit;
    }
}
