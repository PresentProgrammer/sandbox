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
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
		return left;
    }
    
    public static void main(final String[] args) {
        System.out.println("2 == " + new FindPeakElement().findPeakElement(new int[]{ 1, 2, 3, 1 }));
        System.out.println("1 or 5 == " + new FindPeakElement().findPeakElement(new int[]{ 1, 2, 1, 3, 5, 6, 4 }));
	}
}