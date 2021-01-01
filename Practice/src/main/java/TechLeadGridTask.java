import java.util.Arrays;

/**
 * Problem: https://youtu.be/IWvbPIYQPFM?t=319
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class TechLeadGridTask {

    private static final char EMPTY = ' ';

    public int maxAdjacentSameColours(final char[][] input) {
        final char[][] grid = copy(input);
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != EMPTY) {
                    final AdjacentCounter counter = new AdjacentCounter(grid, grid[i][j]);
                    counter.countAndErase(i, j);
                    max = Math.max(max, counter.getCount());
                }
            }
        }
        return max;
    }

    private static class AdjacentCounter {
        private final char[][] grid;
        private final char color;
        private int count;

        private AdjacentCounter(final char[][] grid, final char color) {
            this.grid = grid;
            this.color = color;
            this.count = 0;
        }

        private void countAndErase(final int i, final int j) {
            if (0 <= i && i < grid.length && 0 <= j && j < grid[i].length && grid[i][j] == color) {
                count++;
                grid[i][j] = EMPTY;
                countAndErase(i - 1, j);
                countAndErase(i + 1, j);
                countAndErase(i, j - 1);
                countAndErase(i, j + 1);
            }
        }

        private int getCount() {
            return count;
        }
    }

    private static char[][] copy(final char[][] grid) {
        final char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return copy;
    }

    public static void main(final String[] args) {
        System.out.println("5 == " + new TechLeadGridTask().maxAdjacentSameColours(new char[][]{
                {'g', 'g', 'b', 'r'},
                {'g', 'b', 'r', 'b'},
                {'r', 'b', 'b', 'b'},
        }));
    }
}