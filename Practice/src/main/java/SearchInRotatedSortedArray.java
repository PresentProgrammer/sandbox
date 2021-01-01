/**
 * Problem #33
 * Time complexity: O(log n)
 * Space complexity: O(1)
 **/
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length;
        while (left < right) {
            final int mid = (left + right) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (nums[left] <= target && target < nums[mid]) {
                right = mid;
            } else if (nums[mid] < target && target <= nums[right - 1]) {
                left = mid + 1;
            } else if (nums[left] > nums[mid]) {
                right = mid;
            } else if (nums[mid] > nums[right - 1]) {
                left = mid + 1;
            } else {
                return -1;
            }
        }
        return -1;
    }


    public static void main(final String[] args) {
        System.out.println("4 == " + new SearchInRotatedSortedArray().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println("-1 == " + new SearchInRotatedSortedArray().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println("-1 == " + new SearchInRotatedSortedArray().search(new int[]{1, 3}, 2));
        System.out.println("2 == " + new SearchInRotatedSortedArray().search(new int[]{5, 1, 3}, 3));
        System.out.println("0 == " + new SearchInRotatedSortedArray().search(new int[]{2}, 2));
        System.out.println("-1 == " + new SearchInRotatedSortedArray().search(new int[]{1}, 2));
        System.out.println("-1 == " + new SearchInRotatedSortedArray().search(new int[]{3}, 2));
    }
}