/**
 * Problem #55
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class JumpGame {

    private Boolean[] memo;
    private int[] nums;

    public boolean canJump(int[] nums) {
        this.memo = new Boolean[nums.length];
        this.memo[nums.length - 1]  = true;
        this.nums = nums;
        return cachedCanJump(0);
    }

    private boolean cachedCanJump(int pos) {
        if (memo[pos] == null) {
            memo[pos] = canJump(pos);
        }
        return memo[pos];
    }

    private boolean canJump(int pos) {
        for (int jumpLength = nums[pos]; jumpLength > 0; jumpLength--) {
            final int nextPos = pos + jumpLength;
            final boolean canReachEnd = nextPos >= nums.length || cachedCanJump(nextPos);
            if (canReachEnd) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(final String[] args) {
        System.out.println("true == " + new JumpGame().canJump(new int[]{ 2,3,1,1,4 }));
        System.out.println("false == " + new JumpGame().canJump(new int[]{ 3,2,1,0,4 }));
	}
}