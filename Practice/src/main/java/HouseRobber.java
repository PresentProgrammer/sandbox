/**
 * Problem #198
 * Time complexity: O(n ^ 2)
 * Space complexity: O(n)
 **/
public class HouseRobber {

    private int[] nums;

    // TODO: See https://leetcode.com/problems/house-robber/discuss/55681/Java-O(n)-solution-space-O(1)
    // TODO: See https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.
    public int rob(int[] nums) {
        this.nums = nums;
		return rob(0);
    }

    private int rob(final int curr) {
        if (curr + 1 < nums.length) {
            return Math.max(nums[curr] + rob(curr + 2), nums[curr + 1] + rob(curr + 3));
        } else if (curr < nums.length) {
            return nums[curr];
        } else {
            return 0;
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("4 == " + new HouseRobber().rob(new int[]{ 1, 2, 3, 1 }));
        System.out.println("12 == " + new HouseRobber().rob(new int[]{ 2, 7, 9, 3, 1 }));
	}
}