/**
 * Problem #329
 * Time complexity: O(m * n), where m and n determine matrix size.
 * Space complexity: O(m * n)
 **/
public class LongestIncreasingPathinMatrix {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int[][] matrix;
    private int[][] cellLongestPath;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        this.matrix = matrix;
        this.cellLongestPath = new int[matrix.length][matrix[0].length];
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result = Math.max(result, longestPath(i, j));
            }
        }
        return result;
    }

    private int longestPath(final int i, final int j) {
        if (cellLongestPath[i][j] > 0) {
            return cellLongestPath[i][j];
        } else {
            final int currVal = matrix[i][j];
            int currLongestPath = 1;
            for (final int[] direction : DIRECTIONS) {
                final int iNext = i + direction[0], jNext = j + direction[1];
                if (isIncreasingCell(iNext, jNext, currVal)) {
                    currLongestPath = Math.max(currLongestPath, longestPath(iNext, jNext) + 1);
                }
            }
            cellLongestPath[i][j] = currLongestPath;
            return currLongestPath;
        }
    }

    private boolean isIncreasingCell(final int i, final int j, final int prevVal) {
        return 0 <= i && i < matrix.length && 0 <= j && j < matrix[i].length && prevVal < matrix[i][j];
    }

    public static void main(final String[] args) {
    }
}