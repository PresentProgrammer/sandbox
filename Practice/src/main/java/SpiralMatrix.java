import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem #54
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class SpiralMatrix {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Collections.emptyList();
        }
        final boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        final List<Integer> result = new ArrayList<>();
        final int expectedSize = matrix.length * matrix[0].length;
        int dirInd = 0;
        int i = 0;
        int j = -1;
        while (result.size() < expectedSize) {
            final int nextI = i + DIRECTIONS[dirInd][0];
            final int nextJ = j + DIRECTIONS[dirInd][1];
            if (0 <= nextI && nextI < matrix.length && 0 <= nextJ && nextJ < matrix[nextI].length && !visited[nextI][nextJ]) {
                i = nextI;
                j = nextJ;
                result.add(matrix[i][j]);
                visited[i][j] = true;
            } else {
                dirInd = (dirInd + 1) % 4;
            }
        }
        return result;
    }

    public static void main(final String[] args) {
    }
}