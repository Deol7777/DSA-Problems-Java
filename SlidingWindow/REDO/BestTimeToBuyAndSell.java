package SlidingWindow.REDO;
import java.util.Arrays;

public class BestTimeToBuyAndSell {
        public static void main(String[] args) {
        int[] temp = new int[]{7,1,5,3,6,4};
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
       int lMin = prices[0];
       int profit = 0;
       for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - lMin);
            lMin = Math.min(lMin, prices[i]);
       }
       return profit;
    }
}
