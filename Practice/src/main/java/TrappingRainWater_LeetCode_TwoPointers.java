/**
 * Problem #42
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class TrappingRainWater_LeetCode_TwoPointers {
	
    public int trap(final int[] heights) {
    	int leftMax = 0, rightMax = 0;
    	int left = 0, right = heights.length - 1;
    	int result = 0;
    	while (left < right) {
    		if (heights[left] < heights[right]) {
    			if (heights[left] > leftMax) {
    				leftMax = heights[left];
				} else {
    				result += leftMax - heights[left];
				}
    			left++;
			} else {
    			if (heights[right] > rightMax) {
    				rightMax = heights[right];
				} else {
    				result += rightMax - heights[right];
				}
    			right--;
			}
		}
    	return result;
    }
	
    public static void main(final String[] args) {
        System.out.println("6 == " + new TrappingRainWater_LeetCode_TwoPointers().trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
        System.out.println("1 == " + new TrappingRainWater_LeetCode_TwoPointers().trap(new int[] { 5, 4, 1, 2 }));
	}
}