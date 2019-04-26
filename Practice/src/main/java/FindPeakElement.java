/**
 * Problem #162
 * Time complexity: O(log n)
 * Space complexity: O(1)
 **/
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
		int left = 0, right = nums.length - 1;
		while (left < right) {
		    final int mid = (left + right) / 2;
            if (mid - 1 < 0 || nums[mid - 1] < nums[mid]) {
                if (mid + 1 >= nums.length || nums[mid + 1] < nums[mid]) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
		return left;
    }
    
    public static void main(final String[] args) {
        System.out.println("2 == " + new FindPeakElement().findPeakElement(new int[]{ 1, 2, 3, 1 }));
        System.out.println("1 or 5 == " + new FindPeakElement().findPeakElement(new int[]{ 1, 2, 1, 3, 5, 6, 4 }));
	}
}