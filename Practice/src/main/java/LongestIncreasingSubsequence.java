import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem #300
 * Time complexity: O(N * log(N))
 * Space complexity: O(N)
 **/
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        final List<Integer> list = new ArrayList<>();
        for (final int num : nums) {
            final int binIndex = Collections.binarySearch(list, num);
            final int index = binIndex >= 0 ? binIndex : -binIndex - 1;
            if (index == list.size()) {
                list.add(num);
            } else {
                list.set(index, num);
            }
        }
        return list.size();
    }

    public static void main(final String[] args) {
        System.out.println("4 == " + new LongestIncreasingSubsequence().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("2 == " + new LongestIncreasingSubsequence().lengthOfLIS(new int[]{18, 18, 20}));
        System.out.println("3 == " + new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1, 2, 1, 3}));
        System.out.println("6 == " + new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }
}