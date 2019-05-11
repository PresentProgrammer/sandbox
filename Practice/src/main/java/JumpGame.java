/**
 * Problem #55
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class JumpGame {

    public boolean canJump(int[] nums) {
        int distanceToReachable = 1;
        for (int i = nums.length - 2; i >= 1; i--) {
            if (nums[i] >= distanceToReachable) {
                distanceToReachable = 1;
            } else {
                distanceToReachable++;
            }
        }
        return nums.length == 1 || nums[0] >= distanceToReachable;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new JumpGame().canJump(new int[]{ 2,3,1,1,4 }));
        System.out.println("false == " + new JumpGame().canJump(new int[]{ 3,2,1,0,4 }));
        System.out.println("true == " + new JumpGame().canJump(new int[]{ 0 }));
	}
}