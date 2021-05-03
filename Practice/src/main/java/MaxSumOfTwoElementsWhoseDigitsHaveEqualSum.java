import java.util.HashMap;
import java.util.Map;

/**
 * https://stackoverflow.com/a/59873031/8217032
 * Time complexity: O(N)
 * Space complexity: O(lg(N))
 **/
public class MaxSumOfTwoElementsWhoseDigitsHaveEqualSum {

    public int solve(int[] arr) {
        final Map<Integer, Integer> map = new HashMap<>();
        int max = -1;
        for (final int el : arr) {
            final int key = toDigitSum(el);
            final int value = map.getOrDefault(key, Integer.MIN_VALUE);
            max = Math.max(max, value + el);
            map.put(key, Math.max(value, el));
        }
        return max;
    }

    private static int toDigitSum(final int el) {
        int result = 0;
        int rem = el;
        while (rem > 0) {
            result += rem % 10;
            rem /= 10;
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println("93 == " + new MaxSumOfTwoElementsWhoseDigitsHaveEqualSum().solve(new int[]{51, 71, 17, 42}));
        System.out.println("102 == " + new MaxSumOfTwoElementsWhoseDigitsHaveEqualSum().solve(new int[]{42, 33, 60}));
        System.out.println("-1 == " + new MaxSumOfTwoElementsWhoseDigitsHaveEqualSum().solve(new int[]{51, 32, 43}));
        System.out.println("121 == " + new MaxSumOfTwoElementsWhoseDigitsHaveEqualSum().solve(new int[]{101, 20, 40}));
    }
}