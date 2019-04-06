import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

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

    private void sinkIsland(char[][] grid, int i, int j) {
        final Deque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(i, j));
        while (!stack.isEmpty()) {
            final Pair p = stack.pop();
            if (0 <= p.i && p.i < grid.length && 0 <= p.j && p.j < grid[p.i].length && grid[p.i][p.j] == LAND) {
                grid[p.i][p.j] = WATER;
                stack.push(new Pair(p.i - 1, p.j));
                stack.push(new Pair(p.i + 1, p.j));
                stack.push(new Pair(p.i, p.j - 1));
                stack.push(new Pair(p.i, p.j + 1));
            }
        }
    }

    private static char[][] copyInput(final char[][] input) {
        final char[][] copy = new char[input.length][];
        for (int i = 0; i < input.length; i++) {
            copy[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return copy;
    }

    private static class Pair {

        private int i;
        private int j;

        private Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
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