/**
 * Problem #463
 * Time complexity: O(n * m), where n, m are dimensions of grid.
 * Space complexity: O(1)
 **/
public class IslandPerimeter {

    private static final int[][] DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int islandPerimeter(int[][] grid) {
		int perimeter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    for (final int[] dir : DIRS) {
                        final int iAdj = i + dir[0];
                        final int jAdj = j + dir[1];
                        if (iAdj < 0 || iAdj >= grid.length || jAdj < 0 || jAdj >= grid[i].length || grid[iAdj][jAdj] == 0) {
                            perimeter++;
                        }
                    }
                }
            }
        }
        return perimeter;
    }
    
    public static void main(final String[] args) {
	}
}