import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Problem #78
 * Time complexity: O(2^n)
 * Space complexity: O(n)
 **/
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        return subsets(sortedCopy(nums), new ArrayList<>(), new ArrayList<>(), 0);
    }

    private static List<List<Integer>> subsets(int[] nums, List<Integer> temp, List<List<Integer>> result, int depth) {
        result.add(new ArrayList<>(temp));
        for (int i = depth; i < nums.length; i++) {
            if (i == depth || nums[i] != nums[i - 1]) {
                temp.add(nums[i]);
                subsets(nums, temp, result, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
        return result;
    }

    private static int[] sortedCopy(int[] nums) {
        return IntStream.of(nums)
                .sorted()
                .toArray();
    }

    public static void main(final String[] args) {
        System.out.println(new SubsetsII().subsetsWithDup(new int[]{1, 2, 3, 2}));
    }
}