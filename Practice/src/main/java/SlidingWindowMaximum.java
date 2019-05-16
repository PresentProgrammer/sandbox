import java.util.Arrays;
import java.util.TreeMap;

/**
 * Problem #239
 * Time complexity: O(n log k)
 * Space complexity: O(k)
 **/
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
		    return new int[]{};
        }
        int left = 0, rightExcl = left + k;
		final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		for (int i = left; i < rightExcl - 1; i++) {
		    treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);
        }
		final int[] result = new int[nums.length + 1 - k];
		while (rightExcl <= nums.length) {
            treeMap.put(nums[rightExcl - 1], treeMap.getOrDefault(nums[rightExcl - 1], 0) + 1);
            result[left] = treeMap.lastKey();
            final int leftCount = treeMap.get(nums[left]);
            if (leftCount == 1) {
                treeMap.remove(nums[left]);
            } else {
                treeMap.put(nums[left], leftCount - 1);
            }
            left++;
            rightExcl++;
        }
		return result;
    }
    
    public static void main(final String[] args) {
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(new int[]{1,-1}, 1)));
	}
}