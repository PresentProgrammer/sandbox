package present.programmer.algorithms.sandbox.union.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Note: To get Percolation vizualizer classes, join the course Algorithms, Part 1 at Coursera.
 * See https://www.coursera.org/learn/algorithms-part1
 */
public class Percolation {

    private static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static final int TOP_VIRTUAL_LAYER_SITE = 0;

    private final int n;
    /**
     * grid[0] and grid[n + 1] are reserved for virtual layers: top and bottom correspondingly.
     */
    private final boolean[][] grid;
    private int openCount = 0;
    private final WeightedQuickUnionUF fullUnionFind;
    private final WeightedQuickUnionUF percolationUnionFind;
    private final int bottomVirtualLayerSite;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be positive.");
        }
        this.n = n;
        this.grid = new boolean[n + 2][n];
        this.fullUnionFind = new WeightedQuickUnionUF((n + 1) * n);
        this.percolationUnionFind = new WeightedQuickUnionUF((n + 2) * n);
        for (int col = 0; col < n; col++) {
            open0(0, col);
            open0(n + 1, col);
        }
        this.openCount = 0;
        this.bottomVirtualLayerSite = (n + 2) * n - 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        assertArgumentsInRange(row, col);
        open0(row, col - 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        assertArgumentsInRange(row, col);
        return grid[row][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        assertArgumentsInRange(row, col);
        return fullUnionFind.connected(TOP_VIRTUAL_LAYER_SITE, convertToNodeId(row, col - 1));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolationUnionFind.connected(TOP_VIRTUAL_LAYER_SITE, bottomVirtualLayerSite);
    }

    private void assertArgumentsInRange(final int row, final int col) {
        if (!(1 <= row && row <= n && 1 <= col && col <= n)) {
            throw new IllegalArgumentException("row, col should be in range [1, " + grid.length + "]");
        }
    }

    private void open0(final int row, final int col) {
        if (!grid[row][col]) {
            grid[row][col] = true;
            openCount++;
            for (final int[] dir : DIRS) {
                final int adjRow = row + dir[0];
                final int adjCol = col + dir[1];
                updateFullUnionFind(row, col, adjRow, adjCol);
                updatePercolationUnionFind(row, col, adjRow, adjCol);
            }
        }
    }

    private void updateFullUnionFind(final int row, final int col, final int adjRow, final int adjCol) {
        if (row < n + 1 && 0 <= adjRow && adjRow < n + 1 && 0 <= adjCol && adjCol < n && grid[adjRow][adjCol]) {
            fullUnionFind.union(convertToNodeId(row, col), convertToNodeId(adjRow, adjCol));
        }
    }

    private void updatePercolationUnionFind(final int row, final int col, final int adjRow, final int adjCol) {
        if (row < n + 2 && 0 <= adjRow && adjRow < n + 2 && 0 <= adjCol && adjCol < n && grid[adjRow][adjCol]) {
            percolationUnionFind.union(convertToNodeId(row, col), convertToNodeId(adjRow, adjCol));
        }
    }

    private int convertToNodeId(final int row, final int col) {
        return n * row + col;
    }
}
