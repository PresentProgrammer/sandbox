import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Problem #78
 * Time complexity: O(2^n)
 * Space complexity: O(n)
 **/
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, new ArrayList<>(), new ArrayList<>(), 0);
    }

    private static List<List<Integer>> subsets(int[] nums, List<Integer> temp, List<List<Integer>> result, int firstToUse) {
        result.add(new ArrayList<>(temp));
        for (int i = firstToUse; i < nums.length; i++) {
            temp.add(nums[i]);
            subsets(nums, temp, result, i + 1);
            temp.remove(temp.size() - 1);
        }
        return result;
    }

    /**
     * ArrayList VS LinkedList performance:
     *
     * 20 nums:
     * ArrayList: 0.091S, 0.083S, 0.08S
     * LinkedList: 3.84S, 3.759S, 3.837S
     *
     *
     * 23 nums:
     * ArrayList: 7.472S, 5.89S, 6.632S, 6.991S, 5.582S
     * ArrayList: 34.516S, 39.398S
     */
    public static void main(final String[] args) {
        final int[] nums = IntStream.range(0, 20).toArray();
        final Instant start = Instant.now();
        new Subsets().subsets(nums);
        System.out.println("Time taken: " + Duration.between(start, Instant.now()));

        System.out.println(new Subsets().subsets(new int[]{ 1, 2, 3 }));
    }
}