/**
 * Problem #198
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class HouseRobber {

    public int rob(int[] nums) {
        int currRobbed = nums.length > 0 ? nums[0] : 0;
        int currNotRobbed = 0;
        for (int i = 1; i < nums.length; i++) {
            final int prevRobbed = currRobbed;
            currRobbed = currNotRobbed + nums[i];
            currNotRobbed = Math.max(currNotRobbed, prevRobbed);
        }
        return Math.max(currRobbed, currNotRobbed);
    }

    public static void main(final String[] args) {
        System.out.println("4 == " + new HouseRobber().rob(new int[]{ 1, 2, 3, 1 }));
        System.out.println("12 == " + new HouseRobber().rob(new int[]{ 2, 7, 9, 3, 1 }));
	}
}