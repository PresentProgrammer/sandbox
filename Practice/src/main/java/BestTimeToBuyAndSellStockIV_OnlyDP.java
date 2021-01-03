import java.util.stream.IntStream;

/**
 * Problem #188
 * Time complexity: O(N * K)
 * Space complexity: O(N * K)
 **/
public class BestTimeToBuyAndSellStockIV_OnlyDP {

    private static final int FREE = 0;
    private static final int HOLD = 1;

    public int maxProfit(int k, int[] prices) {
        final int[][][] dp = new int[2][k][prices.length + 1];
        for (int i = 0; i < k; i++) {
            dp[HOLD][i][0] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                dp[FREE][j][i + 1] = Math.max(dp[FREE][j][i], dp[HOLD][j][i] + prices[i]);
                dp[HOLD][j][i + 1] = Math.max(dp[HOLD][j][i],
                        (j == k - 1 ? 0 : dp[FREE][j + 1][i]) - prices[i]);
            }
        }

        return IntStream.range(0, k)
                .map(j -> dp[FREE][j][prices.length])
                .max()
                .orElse(0);
    }

    public static void main(final String[] args) {
        System.out.println("2 == " + new BestTimeToBuyAndSellStockIV_OnlyDP().maxProfit(2, new int[]{2, 4, 1}));
        System.out.println("7 == " + new BestTimeToBuyAndSellStockIV_OnlyDP().maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
    }
}