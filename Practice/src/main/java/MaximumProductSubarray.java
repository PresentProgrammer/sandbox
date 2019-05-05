import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem #152
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
		return Math.max(oneWayMaxProduct(nums), oneWayMaxProduct(reverse(nums)));
    }

    private static int oneWayMaxProduct(final int[] nums) {
        int max = nums[0];
        final Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] == 0) {
                queue.offerLast(i);
            } else if (nums[i] < 0) {
                if (queue.peekLast() != null && nums[queue.peekLast()] < 0) {
                    queue.pollLast();
                } else {
                    queue.offerLast(i);
                }
            }
        }
        queue.offerLast(nums.length);

        int left = 0;
        for (final int rightExcl : queue) {
            if (left < rightExcl) {
                int product = 1;
                for (int i = left; i < rightExcl; i++) {
                    product *= nums[i];
                }
                max = Math.max(max, product);
            }
            left = rightExcl + 1;
        }
        return max;
    }

    private static int[] reverse(final int[] original) {
        final int[] reversed = Arrays.copyOf(original, original.length);
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - i - 1];
        }
        return reversed;
    }
    
    public static void main(final String[] args) {
        System.out.println("6 == " + new MaximumProductSubarray().maxProduct(new int[]{2,3,-2,4}));
        System.out.println("0 == " + new MaximumProductSubarray().maxProduct(new int[]{-2,0,-1}));
        System.out.println("24 == " + new MaximumProductSubarray().maxProduct(new int[]{2,-5,-2,-4,3}));
	}
}