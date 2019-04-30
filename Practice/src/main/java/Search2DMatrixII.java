import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Problem #240
 * Time complexity: O(?)
 * Space complexity: O(?)
 **/
public class Search2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        final Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
        q.offer(new Pair<>(0, matrix.length - 1));
        q.offer(new Pair<>(0, matrix[0].length - 1));
        while (!q.isEmpty()) {
            final Pair<Integer, Integer> vertRange = q.poll();
            final int top = vertRange.getKey();
            final int bottom = vertRange.getValue();
            final Pair<Integer, Integer> horizRange = q.poll();
            final int left = horizRange.getKey();
            final int right = horizRange.getValue();
            if (top == bottom) {
                if (horizBinarySearch(matrix, target, top, left, right)) {
                    return true;
                }
            } else if (left == right) {
                if (vertBinarySearch(matrix, target, left, top, bottom)) {
                    return true;
                }
            } else {
                int topNew = top, bottomNew = bottom;
                int leftNew = left, rightNew = right;
                while (topNew <= bottomNew && leftNew <= rightNew) {
                    final int vertMid = (topNew + bottomNew) / 2;
                    final int horizMid = (leftNew + rightNew) / 2;
                    if (matrix[vertMid][horizMid] > target) {
                        bottomNew = vertMid - 1;
                        rightNew = horizMid - 1;
                    } else if (matrix[vertMid][horizMid] < target) {
                        topNew = vertMid + 1;
                        leftNew = horizMid + 1;
                    } else {
                        return true;
                    }
                }
                if (bottomNew >= top && leftNew <= right) {
                    q.offer(new Pair<>(top, bottomNew));
                    q.offer(new Pair<>(leftNew, right));
                }
                if (topNew <= bottom && rightNew >= left) {
                    q.offer(new Pair<>(topNew, bottom));
                    q.offer(new Pair<>(left, rightNew));
                }
            }
        }
        return false;
    }

    private static boolean horizBinarySearch(int[][] matrix, int target, int row, int left, int right) {
        return Arrays.binarySearch(matrix[row], left, right + 1, target) >= 0;
    }

    private static boolean vertBinarySearch(int[][] matrix, int target, int col, int _top, int _bottom) {
        int top = _top, bottom = _bottom;
        while (top <= bottom) {
            final int mid = (top + bottom) / 2;
            if (matrix[mid][col] == target) {
                return true;
            } else if (matrix[mid][col] < target) {
                top = mid + 1;
            } else {
                bottom = mid - 1;
            }
        }
        return false;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new Search2DMatrixII().searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 5));
        System.out.println("false == " + new Search2DMatrixII().searchMatrix(new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        }, 20));
        System.out.println("true == " + new Search2DMatrixII().searchMatrix(new int[][]{
                {1,2,3,7,8},
                {5,10,14,16,19},
                {8,10,18,19,23},
                {9,12,22,24,29}
        }, 14));
    }
}