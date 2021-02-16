import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #995
 * Time complexity: O(N)
 * Space complexity: O(K)
 **/
public class MinimumNumberOfKConsecutiveBitFlips {

    public int minKBitFlips(int[] a, int k) {
        int flips = 0;
        final Deque<Integer> deque = new ArrayDeque<>(k);
        int i;
        for (i = 0; i < a.length - k + 1; i++) {
            maintain(deque, i, k);
            if (isZero(a, i, deque)) {
                deque.offerLast(i);
                flips++;
            }
        }

        while (i < a.length) {
            maintain(deque, i, k);
            if (isZero(a, i, deque)) {
                return -1;
            } else {
                i++;
            }
        }
        return flips;
    }

    private static boolean isZero(int[] a, int i, Deque<Integer> deque) {
        return (a[i] + deque.size()) % 2 == 0;
    }

    private static void maintain(Deque<Integer> deque, int i, int k) {
        if (deque.size() > 0 && deque.peekFirst() == i - k) {
            deque.pollFirst();
        }
    }

    public static void main(final String[] args) {
        System.out.println("3 == " + new MinimumNumberOfKConsecutiveBitFlips().minKBitFlips(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3));
    }
}