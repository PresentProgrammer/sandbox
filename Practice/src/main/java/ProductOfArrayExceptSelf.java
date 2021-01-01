import java.util.Arrays;

/**
 * Problem #238
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] in) {
        final int[] out = new int[in.length];
        out[0] = 1;
        for (int i = 1; i < in.length; i++) {
            out[i] = out[i - 1] * in[i - 1];
        }
        int right = in[in.length - 1];
        for (int i = in.length - 2; i >= 0; i--) {
            out[i] *= right;
            right *= in[i];
        }
        return out;
    }

    public static void main(final String[] args) {
        System.out.println("[24, 12, 8, 6] == " + Arrays.toString(
                new ProductOfArrayExceptSelf().productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println("[0, -18, 0] == " + Arrays.toString(
                new ProductOfArrayExceptSelf().productExceptSelf(new int[]{9, 0, -2})));
    }
}