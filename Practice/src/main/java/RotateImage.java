/**
 * Problem #48
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class RotateImage {

    private int[][] matrix;

    public void rotate(int[][] matrix) {
        this.matrix = matrix;
        rotate();
    }

    private void rotate() {
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                rotate(i, j);
            }
        }
        if (matrix.length % 2 == 1) {
            for (int j = 0; j < matrix.length / 2; j++) {
                rotate(matrix.length / 2, j);
            }
        }
    }

    private void rotate(int i1, int j1) {
        final int i2 = j1, j2 = matrix.length - 1 - i1;
        final int i3 = matrix.length - 1 - i1, j3 = matrix.length - 1 - j1;
        final int i4 = matrix.length - 1 - j1, j4 = i1;
        swap(i4, j4, i1, j1);
        swap(i3, j3, i4, j4);
        swap(i2, j2, i3, j3);
    }

    private void swap(int i1, int j1, int i2, int j2) {
        final int temp = matrix[i2][j2];
        matrix[i2][j2] = matrix[i1][j1];
        matrix[i1][j1] = temp;
    }

    public static void main(final String[] args) {
	}
}