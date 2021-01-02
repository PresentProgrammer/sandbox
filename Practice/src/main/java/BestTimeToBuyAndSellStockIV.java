import java.util.Arrays;

/**
 * Problem #188
 * Time complexity: O(N * (N + K))
 * Space complexity: O(N * (N + K))
 **/
public class BestTimeToBuyAndSellStockIV {

    private static final int EMPTY = -1;

    private int[] prices;
    private int[][] oneTransactionMemo;
    private int[][] profitMemo;

    public int maxProfit(int k, int[] prices) {
        init(k, prices);
        return memoedProfit(0, k);
    }

    private void init(int k, int[] prices) {
        this.prices = prices;
        this.oneTransactionMemo = initOneTransactionMemo(prices);
        this.profitMemo = initProfitMemo(prices, k);
    }

    private static int[][] initOneTransactionMemo(int[] prices) {
        final int[][] memo = new int[prices.length][prices.length];
        for (int first = 0; first < prices.length; first++) {
            int lo = prices[first];
            int profit = 0;
            for (int last = first; last < prices.length; last++) {
                lo = Math.min(lo, prices[last]);
                profit = Math.max(profit, prices[last] - lo);
                memo[first][last] = profit;
            }
        }
        return memo;
    }

    private static int[][] initProfitMemo(int[] prices, int k) {
        final int[][] memo = new int[prices.length + 1][k + 1];
        for (final int[] row : memo) {
            Arrays.fill(row, EMPTY);
        }
        return memo;
    }

    private int memoedProfit(int first, int transRem) {
        if (profitMemo[first][transRem] == EMPTY) {
            profitMemo[first][transRem] = profit(first, transRem);
        }
        return profitMemo[first][transRem];
    }

    private int profit(int first, int transRem) {
        if (transRem == 0 || first == prices.length) {
            return 0;
        } else {
            int maxProfit = 0;
            for (int last = first + 1; last < prices.length; last++) {
                maxProfit = Math.max(maxProfit,
                        oneTransactionMemo[first][last] + memoedProfit(last + 1, transRem - 1));
            }
            return maxProfit;
        }
    }

    public static void main(final String[] args) {
        System.out.println("2 == " + new BestTimeToBuyAndSellStockIV().maxProfit(2, new int[]{2, 4, 1}));
        System.out.println("7 == " + new BestTimeToBuyAndSellStockIV().maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }
}