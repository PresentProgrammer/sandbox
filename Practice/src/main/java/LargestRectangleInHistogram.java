import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #84
 * Time complexity: O(N), as we push and pop max N elements.
 * Space complexity: O(N)
 **/
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] row) {
        final Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{-1, -1});
        int maxArea = 0;
        for (int i = 0; i < row.length; i++) {
            maxArea = Math.max(maxArea, areaFromStack(stack, row[i]));
            stack.push(new int[]{i, row[i]});
        }
        maxArea = Math.max(maxArea, areaFromStack(stack, 0));
        return maxArea;
    }

    private static int areaFromStack(Deque<int[]> stack, int limit) {
        int maxArea = 0;
        final int right = stack.peek()[0];
        while (stack.peek()[1] >= limit) {
            final int height = stack.pop()[1];
            final int[] nextPoint = stack.peek();
            maxArea = Math.max(maxArea, height * (right - nextPoint[0]));
        }
        return maxArea;
    }

    public static void main(final String[] args) {
        System.out.println("10 == " + new LargestRectangleInHistogram().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println("0 == " + new LargestRectangleInHistogram().largestRectangleArea(new int[]{}));
        System.out.println(Integer.MAX_VALUE + " == " + new LargestRectangleInHistogram().largestRectangleArea(new int[]{Integer.MAX_VALUE}));
    }
}