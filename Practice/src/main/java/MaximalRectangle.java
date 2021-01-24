/**
 * Problem #85
 * Time complexity: O(N * M ^ 2), where N - rows, M - columns
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

    private static int maxArea(int[] row) {
        int result = 0;
        for (int mid = 0; mid < row.length; mid++) {
            if (row[mid] > 0) {
                final int midElem = row[mid];
                int accum = midElem;
                for (int left = mid - 1; 0 <= left && row[left] >= midElem; left--) {
                    accum += midElem;
                }
                for (int right = mid + 1; right < row.length && row[right] >= midElem; right++) {
                    accum += midElem;
                }
                result = Math.max(result, accum);
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println("6 == " + new MaximalRectangle().maximalRectangle(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
    }
}