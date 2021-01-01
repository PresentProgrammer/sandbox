/**
 * Problem #123
 * Time complexity: O(N)
 * Space complexity: O(N)
 **/
public class BestTimeToBuyAndSellStockIII {

    public int maxProfit(int[] prices) {
        final int[] left = new int[prices.length];
        int lo = prices[0];
        for (int i = 1; i < prices.length; i++) {
            lo = Math.min(lo, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - lo);
        }

        int maxProfit = left[left.length - 1];
        int hi = 0;
        for (int i = prices.length - 1; i > 0; i--) {
            hi = Math.max(hi, prices[i]);
            maxProfit = Math.max(maxProfit, left[i - 1] + hi - prices[i]);
        }
        maxProfit = Math.max(maxProfit, hi - prices[0]);

        return maxProfit;
    }
    
    public static void main(final String[] args) {
        System.out.println("6 == " + new BestTimeToBuyAndSellStockIII().maxProfit(new int[]{3,3,5,0,0,3,1,4}));
        System.out.println("0 == " + new BestTimeToBuyAndSellStockIII().maxProfit(new int[]{1}));
        System.out.println("13 == " + new BestTimeToBuyAndSellStockIII().maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0}));
	}
}