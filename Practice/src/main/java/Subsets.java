import java.util.ArrayList;
import java.util.List;

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

    public static void main(final String[] args) {
    }
}