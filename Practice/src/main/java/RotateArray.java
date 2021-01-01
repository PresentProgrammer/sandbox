import java.util.Arrays;

/**
 * Problem #189
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class RotateArray {

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k == 0) {
            return;
        }
        for (int i = 0; i < greatestCommonDivisor(k, nums.length); i++) {
            int j = i, prevTemp = nums[j], temp;
            do {
                final int next = j + k;
                j = next >= nums.length ? next % nums.length : next;
                temp = nums[j];
                nums[j] = prevTemp;
                prevTemp = temp;
            } while (j != i);
        }
    }

    private static int greatestCommonDivisor(final int a, final int b) {
        return a > b ? gcd(a, b) : gcd(b, a);
    }

    private static int gcd(final int a, final int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(final String[] args) {
        final int[] nums = {1, 2, 3, 4, 5, 6};
        new RotateArray().rotate(nums, 4);
        System.out.println(Arrays.toString(nums));
    }
}