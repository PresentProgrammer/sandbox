/**
 * Problem #11
 **/
public class ContainerWithMostWater {

    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int maxArea(final int[] heights) {
		int left = 0, right = heights.length - 1;
		int result = 0;
		while (left < right) {
			result = Math.max(result, Math.min(heights[left], heights[right]) * (right - left));
			if (heights[left] < heights[right]) {
				left++;
			} else {
				right--;
			}
		}
		return result;
    }

    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    public int maxArea_bruteForce(final int[] heights) {
		int maxWater = 0;
		for (int i = 0; i < heights.length - 1; i++) {
			for (int j = i + 1; j < heights.length; j++) {
				maxWater = Math.max(maxWater, Math.min(heights[i], heights[j]) * (j - i));
			}
		}
		return maxWater;
    }
    
    public static void main(final String[] args) {
        System.out.println("49 == " + new ContainerWithMostWater().maxArea(new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
		System.out.println("1 == " + new ContainerWithMostWater().maxArea(new int[] { 1, 1 }));
	}
}