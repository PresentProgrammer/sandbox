import java.util.ArrayList;
import java.util.List;

/**
 * Problem #78
 * Time complexity: O(2^n)
 * Space complexity: O(n)
 **/
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        final List<List<Integer>> result = new ArrayList<>();
        subsets(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void subsets(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {
        result.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            subsets(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(final String[] args) {
    }
}