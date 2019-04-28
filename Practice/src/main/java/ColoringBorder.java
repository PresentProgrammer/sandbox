/**
 * Problem: Weekly Contest 134: #1034
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ColoringBorder {

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        if (grid.length > 0 && grid[0].length > 0 && color != grid[r0][c0]) {
            colorBorder(grid, r0, c0, color, grid[r0][c0], new boolean[grid.length][grid[0].length]);
        }
        return grid;
    }

    private static void colorBorder(int[][] grid, int r0, int c0, int color, int initColor, boolean[][] visited) {
        if (0 <= r0 && r0 < grid.length && 0 <= c0 && c0 < grid[r0].length && grid[r0][c0] == initColor && !visited[r0][c0]) {
            if (onBorder(grid, r0, c0, initColor, visited)) {
                grid[r0][c0] = color;
            }
            visited[r0][c0] = true;
            colorBorder(grid, r0 - 1, c0, color, initColor, visited);
            colorBorder(grid, r0 + 1, c0, color, initColor, visited);
            colorBorder(grid, r0, c0 - 1, color, initColor, visited);
            colorBorder(grid, r0, c0 + 1, color, initColor, visited);
        }
    }

    private static boolean onBorder(int[][] grid, int r0, int c0, int initColor, boolean[][] visited) {
        return r0 == 0 || r0 == grid.length - 1 || c0 == 0 || c0 == grid[r0].length - 1
                || grid[r0 - 1][c0] != initColor && !visited[r0 - 1][c0]
                || grid[r0 + 1][c0] != initColor && !visited[r0 + 1][c0]
                || grid[r0][c0 - 1] != initColor && !visited[r0][c0 - 1]
                || grid[r0][c0 + 1] != initColor && !visited[r0][c0 + 1];
    }

    public static void main(final String[] args) {
        new ColoringBorder().colorBorder(new int[][] {
                {1,1,1},
                {1,1,1},
                {1,1,1}
        }, 1, 1, 2);
	}
}