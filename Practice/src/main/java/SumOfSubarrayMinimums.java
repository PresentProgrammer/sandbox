import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem #907
 * Time complexity: O(N)
 * Space complexity: O(N)
 **/
public class SumOfSubarrayMinimums {

    private static final int MODULO = 1_000_000_007;

    /**
     * Alternative Left / right array solution, based on https://leetcode.com/problems/sum-of-subarray-minimums/discuss/170750/C++JavaPython-Stack-Solution
     * <p>
     * See interesting formula about number of sub-arrays containing A[i] in above reference, and https://lh3.googleusercontent.com/-GyygvrTJ3GY/XRlvmDTxEHI/AAAAAAAAO4E/yDc-Xvo3isgM8QFOSiVp6yUK_j9E8cwGACK8BGAs/s0/2019-06-30.jpg
     */
    public int sumSubarrayMinsLeftRight(int[] a) {
        final int[] left = buildLeft(a);
        final int[] right = buildRight(a);
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += (long) (left[i] + 1) * (right[i] + 1) * a[i];
        }
        return (int) (sum % MODULO);
    }

    private static int[] buildLeft(int[] a) {
        final int[] left = new int[a.length];
        final Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            while (!stack.isEmpty() && a[i] <= a[stack.peek()]) {
                stack.poll();
            }
            left[i] = stack.isEmpty() ? i : i - stack.peek() - 1;
            stack.push(i);
        }
        return left;
    }

    private static int[] buildRight(int[] a) {
        final int[] right = new int[a.length];
        final Deque<Integer> stack = new ArrayDeque<>();
        for (int i = a.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && a[i] < a[stack.peek()]) {
                stack.poll();
            }
            right[i] = stack.isEmpty() ? a.length - i - 1 : stack.peek() - i - 1;
            stack.push(i);
        }
        return right;
    }

    /**
     * My solution.
     */
    public int sumSubarrayMins(int[] a) {
        final int[] lower = buildLower(a);
        final int[] unused = new int[a.length + 1];
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += (long) a[i] * (lower[i] - i) * (unused[i] + 1);
            unused[lower[i]] += unused[i] + 1;
        }
        return (int) (sum % MODULO);
    }

    private static int[] buildLower(int[] a) {
        final int[] lower = new int[a.length];
        final Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            while (!stack.isEmpty() && a[i] <= a[stack.peek()]) {
                lower[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            lower[stack.pop()] = a.length;
        }
        return lower;
    }

    public static void main(final String[] args) {
//        System.out.println("17 == " + new SumOfSubarrayMinimums().sumSubarrayMins(new int[] { 3, 1, 2, 4 }));
//        System.out.println("17 == " + new SumOfSubarrayMinimums().sumSubarrayMins(new int[] { 3, 2, 1, 4 }));

        System.out.println("17 == " + new SumOfSubarrayMinimums().sumSubarrayMinsLeftRight(new int[]{3, 1, 2, 4}));
        System.out.println("17 == " + new SumOfSubarrayMinimums().sumSubarrayMinsLeftRight(new int[]{3, 2, 1, 4}));
        System.out.println("593 == " + new SumOfSubarrayMinimums().sumSubarrayMinsLeftRight(new int[]{71, 55, 82, 55}));
    }
}