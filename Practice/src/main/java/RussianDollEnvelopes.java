import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Problem #354
 * Time complexity: O(N * log(N))
 * Space complexity: O(N)
 **/
public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        final int[] nums = Stream.of(envelopes)
                .sorted(Comparator.comparingInt((int[] env) -> env[0])
                        .thenComparing(Comparator.comparingInt((int[] env) -> env[1]).reversed()))
                .mapToInt(env -> env[1])
                .toArray();
        return lengthOfLIS(nums);
    }

    private static int lengthOfLIS(int[] nums) {
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
        System.out.println("3 == " + new RussianDollEnvelopes().maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
    }
}