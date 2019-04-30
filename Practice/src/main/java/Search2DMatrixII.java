/**
 * Problem #240
 * Time complexity: O(n + m)
 * Space complexity: O(1)
 **/
public class Search2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] < target) {
                i++;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                return true;
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