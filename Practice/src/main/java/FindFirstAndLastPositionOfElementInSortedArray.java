import java.util.Arrays;

/**
 * Problem #34
 * Time complexity: O(log n)
 * Space complexity: O(1)
 **/
public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
		int left = 0, right = nums == null ? -1 : nums.length - 1;
		while (left <= right) {
		    final int mid = left + (right - left) / 2;
		    if (nums[mid] < target) {
		        left = mid + 1;
            } else if (nums[mid] > target) {
		        right = mid - 1;
            } else {
		        left = mid;
		        right = mid;
		        break;
            }
        }
		if (left > right) {
		    return new int[]{ -1, -1 };
        } else {
            int start = left, end = left;
            {
                int leftStart = 0, rightStart = start - 1;
                while (leftStart <= rightStart) {
                    final int midStart = leftStart + (rightStart - leftStart) / 2;
                    if (nums[midStart] < target) {
                        leftStart = midStart + 1;
                    } else {
                        start = midStart;
                        rightStart = midStart - 1;
                    }
                }
            }
            {
                int leftEnd = end + 1, rightEnd = nums.length - 1;
                while (leftEnd <= rightEnd) {
                    final int midEnd = leftEnd + (rightEnd - leftEnd) / 2;
                    if (nums[midEnd] > target) {
                        rightEnd = midEnd - 1;
                    } else {
                        end = midEnd;
                        leftEnd = midEnd + 1;
                    }
                }
            }
            return new int[]{ start, end };
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("-1, -1 == " + Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[]{5,7,7,8,8,10}, 6)));
        System.out.println("1, 1 == " + Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[]{1,2,3}, 2)));
        System.out.println("3, 4 == " + Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[]{5,7,7,8,8,10}, 8)));
        System.out.println("-1, -1 == " + Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[]{5,7,7,8,8,10}, 6)));
        System.out.println("-1, -1 == " + Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[]{}, 6)));
        System.out.println("-1, -1 == " + Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(null, 6)));
        System.out.println("0, 0 == " + Arrays.toString(
                new FindFirstAndLastPositionOfElementInSortedArray().searchRange(new int[]{6}, 6)));
	}
}