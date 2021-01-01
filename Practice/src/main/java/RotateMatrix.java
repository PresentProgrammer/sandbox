/**
 * Problem #1.7
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 **/
public class RotateMatrix {

    public void rotate90(final int[][] m) {
        transpose(m);
        verticalSymmetry(m);
    }

    private static void transpose(final int[][] m) {
        for (int i = 0; i < m.length - 1; i++) {
            for (int j = i + 1; j < m[i].length; j++) {
                swap(m, i, j, j, i);
            }
        }
    }

    private static void verticalSymmetry(final int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length / 2; j++) {
                swap(m, i, j, i, m[i].length - j - 1);
            }
        }
    }

    private static void swap(int[][] m, int i1, int j1, int i2, int j2) {
        final int temp = m[i1][j1];
        m[i1][j1] = m[i2][j2];
        m[i2][j2] = temp;
    }

    public static void main(final String[] args) {
        final int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20,},
                {21, 22, 23, 24, 25,},
        };
        new RotateMatrix().rotate90(matrix);
        for (final int[] row : matrix) {
            for (final int elem : row) {
                System.out.printf("%3d", elem);
            }
            System.out.println();
        }
    }
}