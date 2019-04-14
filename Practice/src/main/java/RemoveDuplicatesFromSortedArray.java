/**
 * Problem #26
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
		if (nums.length == 0) {
		    return 0;
        }
		int left = 0;
		while (left + 1 < nums.length && nums[left] != nums[left + 1]) {
		    left++;
        }
		left++;
		int right = left + 1;
		while (right < nums.length) {
		    if (nums[right] != nums[right - 1]) {
		        nums[left] = nums[right];
		        left++;
            }
		    right++;
        }
		return left;
    }
    
    public static void main(final String[] args) {
	}
}