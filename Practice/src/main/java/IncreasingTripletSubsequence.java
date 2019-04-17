/**
 * Problem #334
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
		if (nums == null || nums.length < 3) {
		    return false;
        }
		int min = nums[0];
		Integer mid = null;
		for (int i = 1; i < nums.length; i++) {
		    if (nums[i] <= min) {
		        min = nums[i];
            } else if (mid == null || nums[i] <= mid) {
		        mid = nums[i];
            } else {
		        return true;
            }
        }
		return false;
    }
    
    public static void main(final String[] args) {
        System.out.println("true == " + new IncreasingTripletSubsequence().increasingTriplet(new int[]{ 1,2,3,4,5 }));
        System.out.println("false == " + new IncreasingTripletSubsequence().increasingTriplet(new int[]{ 5,4,3,2,1 }));
	}
}