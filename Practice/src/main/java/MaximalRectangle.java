import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #85
 * Time complexity: O(N * M), where N - rows, M - columns
 * Space complexity: O(N * M)
 **/
public class MaximalRectangle {

    private static final char FILLED = '1';

    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        for (final int[] row : toVertSubSums(matrix)) {
            max = Math.max(max, maxArea(row));
        }
        return max;
    }

    private static int[][] toVertSubSums(char[][] matrix) {
        final int[][] subSums = new int[matrix.length][];
        for (int row = 0; row < matrix.length; row++) {
            subSums[row] = new int[matrix[row].length];
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == FILLED) {
                    final int prev = (0 <= row - 1 && col < subSums[row - 1].length) ? subSums[row - 1][col] : 0;
                    subSums[row][col] = 1 + prev;
                }
            }
        }
        return subSums;
    }

    /**
     * Stack implementation takes O(M) time, O(M) space, where M = row.length.
     */
    public int maxArea(int[] row) {
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
        System.out.println("6 == " + new MaximalRectangle().maximalRectangle(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));
    }
}