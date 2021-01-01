/**
 * Problem #75
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class SortColors {

    public void sortColors(int[] nums) {
        if (nums != null) {
            int left = 0, right = nums.length - 1, i = 0;
            while (i <= right) {
                if (nums[i] == 0) {
                    swap(nums, left, i);
                    left++;
                    if (left > i) {
                        i = left;
                    }
                } else if (nums[i] == 2) {
                    swap(nums, i, right);
                    right--;
                } else {
                    i++;
                }
            }
        }
    }

    private static void swap(final int[] nums, final int i, final int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(final String[] args) {
    }
}