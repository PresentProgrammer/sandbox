import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://stackoverflow.com/a/59873031/8217032
 * Time complexity: O(N)
 * Space complexity: O(lg(N))
 **/
public class MaxSumOfTwoElementsWhoseDigitsHaveEqualSum {

    public int solve(int[] arr) {
        final Map<Integer, LargestPair> map = new HashMap<>();
        int max = -1;
        for (final int el : arr) {
            max = Math.max(max, map.computeIfAbsent(toDigitSum(el), unused -> new LargestPair()).addAndSum(el));
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

    private static class LargestPair {

        private final List<Integer> list = new ArrayList<>(3);

        int addAndSum(int el) {
            list.add(el);
            list.sort(Comparator.reverseOrder());
            if (list.size() > 2) {
                list.remove(2);
            }
            return list.size() == 2 ? list.get(0) + list.get(1) : -1;
        }
    }

    public static void main(final String[] args) {
        System.out.println("93 == " + new MaxSumOfTwoElementsWhoseDigitsHaveEqualSum().solve(new int[]{51, 71, 17, 42}));
        System.out.println("102 == " + new MaxSumOfTwoElementsWhoseDigitsHaveEqualSum().solve(new int[]{42, 33, 60}));
        System.out.println("-1 == " + new MaxSumOfTwoElementsWhoseDigitsHaveEqualSum().solve(new int[]{51, 32, 43}));
        System.out.println("121 == " + new MaxSumOfTwoElementsWhoseDigitsHaveEqualSum().solve(new int[]{101, 20, 40}));
    }
}