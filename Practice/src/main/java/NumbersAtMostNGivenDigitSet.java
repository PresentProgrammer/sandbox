import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem #902
 * Time complexity: O(log N)
 * Space complexity: O(log N)
 **/
public class NumbersAtMostNGivenDigitSet {

    public int atMostNGivenDigitSet(String[] D, int N) {
        final int[] digits = Arrays.stream(D).mapToInt(Integer::valueOf).toArray();
        final Deque<Integer> Ndigits = new ArrayDeque<>();
        int rem = N;
        while (rem > 0) {
            Ndigits.addFirst(rem % 10);
            rem /= 10;
        }

        int result = 0;
        int base = 1;
        for (int i = 0; i < Ndigits.size() - 1; i++) {
            result += base * D.length;
            base *= D.length;
        }

        while (!Ndigits.isEmpty()) {
            final int digitIndex = Arrays.binarySearch(digits, Ndigits.pollFirst());
            if (digitIndex < 0) {
                result += base * (-digitIndex - 1);
                return result;
            } else {
                result += base * digitIndex;
                base /= D.length;
            }
        }
        return result + 1;
    }
}