/**
 * Problem #122
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            final int yesterdayPrice = prices[i - 1];
            final int todayPrice = prices[i];
            if (yesterdayPrice < todayPrice) {
                profit += todayPrice - yesterdayPrice;
            }
        }
        return profit;
    }

    public static void main(final String[] args) {
        System.out.println("7 == " + new BestTimeToBuyAndSellStockII().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println("4 == " + new BestTimeToBuyAndSellStockII().maxProfit(new int[]{1, 2, 3, 4, 5}));
        System.out.println("0 == " + new BestTimeToBuyAndSellStockII().maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}