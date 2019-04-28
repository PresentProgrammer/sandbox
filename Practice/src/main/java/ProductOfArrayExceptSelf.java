import java.util.Arrays;

/**
 * Problem #238
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] in) {
		final int[] leftProducts = new int[in.length];
		leftProducts[0] = 1;
		for (int i = 1; i < in.length; i++) {
		    leftProducts[i] = leftProducts[i - 1] * in[i - 1];
        }

        final int[] rightProducts = new int[in.length];
		rightProducts[in.length - 1] = 1;
		for (int i = in.length - 2; i >= 0; i--) {
		    rightProducts[i] = rightProducts[i + 1] * in[i + 1];
        }

		final int[] out = new int[in.length];
		for (int i = 0; i < out.length; i++) {
		    out[i] = leftProducts[i] * rightProducts[i];
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