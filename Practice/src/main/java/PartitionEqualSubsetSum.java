import java.util.Arrays;

/**
 * Problem #416
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class PartitionEqualSubsetSum {

    private static final int MAX_ELEMENT = 100;

    public boolean canPartition(int[] nums) {
        final boolean[] first = new boolean[MAX_ELEMENT + 1];
        first[0] = true;
        final boolean[] second = Arrays.copyOf(first, first.length);
        int delta = 0;
        for (final int num : nums) {
            if (delta <= 0) {
                delta += num;
                updateSums(first, num);
            } else {
                delta -= num;
                updateSums(second, num);
            }
        }
        if (delta % 2 == 0) {
            int firstIndex = delta > 0 ? delta / 2 : 0;
            int secondIndex = delta < 0 ? Math.abs(delta) / 2 : 0;
            while (firstIndex < first.length && secondIndex < second.length) {
                if (first[firstIndex] && second[secondIndex]) {
                    return true;
                } else {
                    firstIndex++;
                    secondIndex++;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private static void updateSums(final boolean[] sums, final int num) {
        for (int i = sums.length - 1; i - num >= 0; i--) {
            if (sums[i - num]) {
                sums[i] = true;
            }
        }
    }
}