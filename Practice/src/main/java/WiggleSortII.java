import java.util.Arrays;

/**
 * Problem #324
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 **/
public class WiggleSortII {

    public void wiggleSort(int[] nums) {
        if (nums != null && nums.length > 0) {
            final int[] auxiliary = Arrays.copyOf(nums, nums.length);
            Arrays.sort(auxiliary);
            int lo = (auxiliary.length - 1) / 2, hi = auxiliary.length - 1, i = 0;
            while (i < nums.length) {
                nums[i++] = auxiliary[lo--];
                if (i < nums.length) {
                    nums[i++] = auxiliary[hi--];
                }
            }
        }
    }

    public static void main(final String[] args) {
    }
}