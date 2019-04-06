import java.util.Arrays;

/**
 * Problem #200
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class NumberOfIslands {

    private static final char LAND = '1';
    private static final char WATER = '0';

    public int numIslands(final char[][] input) {
        final char[][] grid = copyInput(input);
        int islandCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == LAND) {
                    islandCount++;
                    sinkIsland(grid, i, j);
                }
            }
        }
        return islandCount;
    }

    private static void sinkIsland(final char[][] grid, final int i, final int j) {
        if (0 <= i && i < grid.length && 0 <= j && j < grid[i].length && grid[i][j] == LAND) {
            grid[i][j] = WATER;
            sinkIsland(grid, i, j - 1);
            sinkIsland(grid, i, j + 1);
            sinkIsland(grid, i - 1, j);
            sinkIsland(grid, i + 1, j);
        }
    }

    private static char[][] copyInput(final char[][] input) {
        final char[][] copy = new char[input.length][];
        for (int i = 0; i < input.length; i++) {
            copy[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return copy;
    }

    public static void main(final String[] args) {
        System.out.println("1 == " + new NumberOfIslands().numIslands(new char[][]{
                new char[] { '1', '1', '1', '1' , '0' },
                new char[] { '1', '1', '0', '1' , '0' },
                new char[] { '1', '1', '0', '0' , '0' },
                new char[] { '0', '0', '0', '0' , '0' },
        }));
        System.out.println("3 == " + new NumberOfIslands().numIslands(new char[][]{
                new char[] { '1', '1', '0', '0' , '0' },
                new char[] { '1', '1', '0', '0' , '0' },
                new char[] { '0', '0', '1', '0' , '0' },
                new char[] { '0', '0', '0', '1' , '1' },
        }));
	}
}