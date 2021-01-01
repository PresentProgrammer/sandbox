/**
 * Problem #121
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int min = prices[0], best = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                best = Math.max(best, prices[i] - min);
            }
        }
        return best;
    }

    public static void main(final String[] args) {
    }
}