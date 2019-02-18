import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #15
 * Time complexity: O(n * log(n)) for sorting + O(n^2) for main part → O(n^2).
 * Space complexity: O(n) for sorting.
 **/
public class ThreeSum_LeetCode {

    public List<List<Integer>> threeSum(final int[] nums) {
        final List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                int lo = i + 1, hi = nums.length - 1, twoSum = -nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == twoSum) {
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (hi > lo && nums[hi - 1] == nums[hi]) {
                            hi--;
                        }
                        while (lo < hi && nums[lo + 1] == nums[lo]) {
                            lo++;
                        }
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] > twoSum) {
                        hi--;
                    } else {
                        lo++;
                    }
                }
            }
        }
        return result;
    }
	
	public static void main(final String[] args) {
        System.out.println("[[-1, 0, 1], [-1, -1, 2]] == " + new ThreeSum_LeetCode().threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
        System.out.println("[[1, 1, -2]] == " + new ThreeSum_LeetCode().threeSum(new int[] { 1, 1, -2 }));
	}
}