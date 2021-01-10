import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Problem #694
 * Time complexity: O(N), where N - grid size (rows * cols)
 * Space complexity: O(N)
 *
 * Note: Using Strings instead of List<Integer> did not make solution faster for tests on LeetCode.
 **/
public class NumberOfDistinctIslands {

    private static final int LAND = 1;
    private static final int[][] DIRS = {
            {0, -1}, {-1, 0}, {0, 1}, {1, 0}
    };


    public int numDistinctIslands(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        final boolean[][] visited = new boolean[grid.length][grid[0].length];
        final Set<List<Integer>> seen = new HashSet<>();
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == LAND && !visited[i][j]) {
                    visited[i][j] = true;
                    final List<Integer> island = dfs(i, j, i, j, new ArrayList<>(), visited, grid);
                    if (seen.add(island)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static List<Integer> dfs(int startRow, int startCol, int row, int col,
            List<Integer> island, boolean[][] visited, int[][] grid) {
        island.add(row - startRow);
        island.add(col - startCol);
        for (final int[] dir : DIRS) {
            final int nextRow = row + dir[0];
            final int nextCol = col + dir[1];
            if (0 <= nextRow && nextRow < grid.length && 0 <= nextCol && nextCol < grid[nextRow].length
                    && grid[nextRow][nextCol] == LAND && !visited[nextRow][nextCol]) {
                visited[nextRow][nextCol] = true;
                dfs(startRow, startCol, nextRow, nextCol, island, visited, grid);
            }
        }
        return island;
    }
}