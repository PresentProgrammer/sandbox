import java.util.Arrays;

/**
 * Problem #16
 * Time complexity: O(n^2)
 * Space complexity: O(n) for sorting.
 **/
public class ThreeSumClosest_LeetCode {

    public int threeSumClosest(final int[] nums, final int target) {
		Arrays.sort(nums);
		int result = nums[0] + nums[1] + nums[nums.length - 1];
		for (int i = 0; i < nums.length - 2; i++) {
		    int lo = i + 1, hi = nums.length - 1;
		    while (lo < hi) {
		        final int sum = nums[i] + nums[lo] + nums[hi];
		        if (sum == target) {
		            return sum;
                } else if (sum < target) {
		            lo++;
                } else {
		            hi--;
                }
		        if (Math.abs(sum - target) < Math.abs(result - target)) {
		            result = sum;
                }
            }
        }
		return result;
    }
    
    public static void main(final String[] args) {
        System.out.println("2 == " + new ThreeSumClosest_LeetCode().threeSumClosest(new int[]{ -1, 2, 1, -4 }, 1));
	}
}