import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #18
 * Time complexity: O(n * log(n)) for sorting + O(n^3) for main part → O(n^3)
 * Space complexity: O(n) for sorting
 **/
public class FourSum {

    public List<List<Integer>> fourSum(final int[] nums, final int target) {
		Arrays.sort(nums);
		final List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < nums.length - 3; i++) {
			if (i == 0 || nums[i - 1] != nums[i]) {
				for (int j = i + 1; j < nums.length - 2; j++) {
					if (j == i + 1 || nums[j - 1] != nums[j]) {
						final int ijSum = nums[i] + nums[j];
						int lo = j + 1, hi = nums.length - 1;
						while (lo < hi) {
							if (ijSum + nums[lo] + nums[hi] == target) {
								result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
								while (lo < hi && nums[lo] == nums[lo + 1]) {
									lo++;
								}
								while (lo < hi && nums[hi - 1] == nums[hi]) {
									hi--;
								}
								lo++;
								hi--;
							} else if (ijSum + nums[lo] + nums[hi] > target) {
								hi--;
							} else {
								lo++;
							}
						}
					}
				}
			}
		}
		return result;
    }
    
    public static void main(final String[] args) {
        System.out.println("[-1,  0, 0, 1], [-2, -1, 1, 2], [-2,  0, 0, 2] == " + new FourSum().fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
	}
}