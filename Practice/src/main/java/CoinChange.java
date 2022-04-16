/**
 * Problem #322
 * Time complexity: O(amount)
 * Space complexity: O(amount)
 **/
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        final int[] dp = new int[amount + 1];
        for (int currAmount = 1; currAmount <= amount; currAmount++) {
            int currResult = -1;
            for (final int coin : coins) {
                if (currAmount >= coin && dp[currAmount - coin] != -1) {
                    final int candidate = dp[currAmount - coin] + 1;
                    currResult = currResult == -1 ? candidate : Math.min(currResult, candidate);
                }
            }
            dp[currAmount] = currResult;
        }
        return dp[amount];
    }

    public static void main(final String[] args) {
        System.out.println("3 == " + new CoinChange().coinChange(new int[]{1, 2, 5}, 11));
        System.out.println("-1 == " + new CoinChange().coinChange(new int[]{2}, 3));
        System.out.println("20 == " + new CoinChange().coinChange(new int[]{1, 2, 5}, 100));
        System.out.println("2 == " + new CoinChange().coinChange(new int[]{1, 2147483647}, 2));
        System.out.println("20 == " + new CoinChange().coinChange(new int[]{186, 419, 83, 408}, 6249));
    }
}