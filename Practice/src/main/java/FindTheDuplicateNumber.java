/**
 * Problem #x
 * Time complexity: O(n log n)
 * Space complexity: O(1)
 **/
public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while (left < right) {
            final int mid = (left + right) / 2;
            int leftCount = 0;
            for (final int num : nums) {
                if (left <= num && num <= mid) {
                    leftCount++;
                }
            }
            if (leftCount > mid - left + 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(final String[] args) {
        System.out.println("2 == " + new FindTheDuplicateNumber().findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println("3 == " + new FindTheDuplicateNumber().findDuplicate(new int[]{3, 1, 3, 4, 2}));
    }
}