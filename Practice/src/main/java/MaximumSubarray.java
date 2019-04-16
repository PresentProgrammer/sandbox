/**
 * Problem #53
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    public static void main(final String[] args) {
        System.out.println("-1 == " + new MaximumSubarray().maxSubArray(new int[]{ -1, -2 }));
        System.out.println("6 == " + new MaximumSubarray().maxSubArray(new int[]{ -2,1,-3,4,-1,2,1,-5,4 }));
        System.out.println("33 == " + new MaximumSubarray().maxSubArray(new int[]{ -9,-2,1,8,7,-6,4,9,-9,-5,0,5,-2,5,9,7 }));
        System.out.println("16 == " + new MaximumSubarray().maxSubArray(new int[]{ -5,8,-5,1,1,-3,5,5,-3,-3,6,4,-7,-4,-8,0,-1,-6 }));
        System.out.println("3 == " + new MaximumSubarray().maxSubArray(new int[]{ -1,1,-3,-2,2,-1,-2,1,2,-3 }));
	}
}