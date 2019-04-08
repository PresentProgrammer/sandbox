import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #322
 * Time complexity: O(amount)
 * Space complexity: O(amount)
 **/
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        final boolean[] considered = new boolean[amount + 1];
		final Deque<State> stateQueue = new ArrayDeque<>();
		stateQueue.offer(new State(0, 0));
		while (!stateQueue.isEmpty()) {
		    final State curr = stateQueue.poll();
		    if (curr.totalAmount == amount) {
		        return curr.depth;
            } else {
                for (final int coin : coins) {
                    final int nextTotalAmount = curr.totalAmount + coin;
                    if (nextTotalAmount > 0 && nextTotalAmount <= amount && !considered[nextTotalAmount]) {
                        considered[nextTotalAmount] = true;
                        stateQueue.offer(new State(curr.depth + 1, nextTotalAmount));
                    }
                }
            }
        }
		return -1;
    }

    private static class State {
        private final int depth;
        private final int totalAmount;

        private State(int depth, int totalAmount) {
            this.depth = depth;
            this.totalAmount = totalAmount;
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("3 == " + new CoinChange().coinChange(new int[]{ 1, 2, 5 }, 11));
        System.out.println("-1 == " + new CoinChange().coinChange(new int[]{ 2 }, 3));
        System.out.println("20 == " + new CoinChange().coinChange(new int[]{ 1, 2, 5 }, 100));
        System.out.println("2 == " + new CoinChange().coinChange(new int[]{ 1, 2147483647 }, 2));
        System.out.println("20 == " + new CoinChange().coinChange(new int[]{ 186,419,83,408 }, 6249));
	}
}