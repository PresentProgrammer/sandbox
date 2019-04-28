import java.util.Arrays;

/**
 * Problem #x
 * Time complexity: O(n log n)
 * Space complexity: O(1)
 **/
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] in) {
		final int[] out = new int[in.length];
		Arrays.fill(out, 1);
        int left = 0, right = in.length - 1;
        while (left < in.length) {
            final int mid = (left + right + 1) / 2;

            int subResult = out[left];
            for (int i = left; i < mid; i++) {
                subResult *= in[i];
            }
            out[mid] = subResult;

            subResult = out[left];
            for (int i = mid; i <= right; i++) {
                subResult *= in[i];
            }
            out[left] = subResult;

            if (mid - left > 1) {
                out[right] = Integer.MAX_VALUE;
                right = mid - 1;
            } else if (right > mid) {
                left = mid;
            } else {
                left = right + 1;
                if (left < in.length) {
                    right = left + 1;
                    while (out[right] != Integer.MAX_VALUE) {
                        right++;
                    }
                }
            }
        }
        return out;
    }

    public static void main(final String[] args) {
        System.out.println("[24, 12, 8, 6] == " + Arrays.toString(
                new ProductOfArrayExceptSelf().productExceptSelf(new int[]{ 1, 2, 3, 4 })));
        System.out.println("[0, -18, 0] == " + Arrays.toString(
                new ProductOfArrayExceptSelf().productExceptSelf(new int[]{ 9, 0, -2 })));
	}
}