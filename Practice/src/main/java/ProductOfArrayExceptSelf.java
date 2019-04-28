import java.util.Arrays;

/**
 * Problem #x
 * Time complexity: O(n log n)
 * Space complexity: O(log n)
 **/
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
		final int[] out = new int[nums.length];
		Arrays.fill(out, 1);
        productExceptSelf(nums, out, 0, nums.length - 1);
        return out;
    }

    private static void productExceptSelf(final int[] in, final int[] out, final int left, final int right) {
        if (right > left) {
            final int mid = (left + right) / 2;
            int subResult = out[left];
            for (int i = left; i <= mid; i++) {
                subResult *= in[i];
            }
            out[mid + 1] = subResult;

            subResult = out[left];
            for (int i = mid + 1; i <= right; i++) {
                subResult *= in[i];
            }
            out[left] = subResult;

            productExceptSelf(in, out, left, mid);
            productExceptSelf(in, out, mid + 1, right);
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("[24, 12, 8, 6] == " + Arrays.toString(
                new ProductOfArrayExceptSelf().productExceptSelf(new int[]{1,2,3,4})));
	}
}