/**
 * Problem #73
 * Time complexity: O(m * n), where m and n determine size of matrix
 * Space complexity: O(1)
 **/
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
		boolean firstRowShouldBeZeroes = false;
		boolean firstColumnShouldBeZeroes = false;
		for (int i = 0; i < matrix.length; i++) {
		    if (matrix[i][0] == 0) {
		        firstColumnShouldBeZeroes = true;
		        break;
            }
        }
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                firstRowShouldBeZeroes = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstColumnShouldBeZeroes) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRowShouldBeZeroes) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
    }
    
    public static void main(final String[] args) {
	}
}