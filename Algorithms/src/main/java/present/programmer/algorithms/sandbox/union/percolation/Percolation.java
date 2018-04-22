package present.programmer.algorithms.sandbox.union.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    /**
     * true — open; false — blocked.
     */
    private final boolean[][] sites;
    private int openSiteCount;

    private final PercolationWeightedQuickUnion weightedQuickUnion;

    public Percolation(final int n) {
        mustBePositive(n);
        sites = squareMatrixAllSitesBlocked(n);
        openSiteCount = 0;
        weightedQuickUnion = new PercolationWeightedQuickUnion(n);
    }

    public void open(final int row, final int col) {
        mustBeInsideBoundaries(row, col);
        if (!isOpen(row, col)) {
            sites[asIndex(row)][asIndex(col)] = true;
            openSiteCount++;
            tryUnionAboveSite(row, col);
            tryUnionBelowSite(row, col);
            tryUnionLeftSite(row, col);
            tryUnionRightSite(row, col);
        }
    }

    public boolean isOpen(final int row, final int col)  {
        mustBeInsideBoundaries(row, col);
        return sites[asIndex(row)][asIndex(col)];
    }

    public boolean isFull(int row, int col) {
        mustBeInsideBoundaries(row, col);
        return weightedQuickUnion.connectedToTop(row, col);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }

    public boolean percolates() {
        return weightedQuickUnion.isPercolate();
    }

    // Auxiliary Methods

    private static boolean[][] squareMatrixAllSitesBlocked(final int n) {
        final boolean[][] sites = new boolean[n][];
        for (int i = 0; i < n; i++) {
            sites[i] = new boolean[n];
        }
        return sites;
    }

    private static void mustBePositive(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("grid size must be positive");
        }
    }

    private void mustBeInsideBoundaries(final int row, final int col) {
        if (outsideOfBoundaries(row) || (outsideOfBoundaries(col))) {
            throw new IllegalArgumentException(
                    "passed arguments must be inside the boundaries: [1, " + sites.length + "); " +
                            "row = " + row + ", col = " + col);
        }
    }

    private boolean outsideOfBoundaries(final int index) {
        return index < 1 || index > (sites.length);
    }

    private void tryUnionAboveSite(final int row, final int col) {
        try {
            if (isOpen(row - 1, col)) {
                weightedQuickUnion.union(row, col, row - 1, col);
            }
        } catch (final IllegalArgumentException e) {
            weightedQuickUnion.unionWithTopVirtual(col);
        }
    }

    private void tryUnionBelowSite(final int row, final int col) {
        try {
            if (isOpen(row + 1, col)) {
                weightedQuickUnion.union(row, col, row + 1, col);
            }
        } catch (final IllegalArgumentException e) {
            weightedQuickUnion.unionWithBottomVirtual(col);
        }
    }

    private void tryUnionLeftSite(final int row, final int col) {
        try {
            if (isOpen(row, col - 1)) {
                weightedQuickUnion.union(row, col, row, col - 1);
            }
        } catch (final IllegalArgumentException e) {
            // Happens when left site is blocked.
        }
    }

    private void tryUnionRightSite(final int row, final int col) {
        try {
            if (isOpen(row, col + 1)) {
                weightedQuickUnion.union(row, col, row, col + 1);
            }
        } catch (final IllegalArgumentException e) {
            // Happens when right site is blocked.
        }
    }

    private int asIndex(final int rowOrCol) {
        return rowOrCol - 1;
    }

    /**
     * Wrapper of WeightedQuickUnionUF, provides convenient API for solving Percolation task.
     * Tracks not only all grid sites, but also 2 virtual sites at the top and at the bootm of the grid.
     * Objects from 0 through n - 1 are considered grid sites, n-th is the top virtual site,
     * and (n + 1)-th is the bottom virtual site.
     */
    private static class PercolationWeightedQuickUnion {

        private final WeightedQuickUnionUF target;
        private final int gridSize;

        PercolationWeightedQuickUnion(final int gridSize) {
            target = new WeightedQuickUnionUF(gridSize * gridSize + 2);
            this.gridSize = gridSize;
        }

        boolean connectedToTop(final int row, final int col) {
            return target.connected(asIndex(row, col), topVirtual());
        }

        boolean isPercolate() {
            return target.connected(topVirtual(), bottomVirtual());
        }

        void union(final int pRow, final int pCol, final int qRow, final int qCol) {
            target.union(asIndex(pRow, pCol), asIndex(qRow, qCol));
        }

        void unionWithTopVirtual(final int col) {
            target.union(asIndex(1, col), topVirtual());
        }

        void unionWithBottomVirtual(final int col) {
            target.union(asIndex(gridSize, col), bottomVirtual());
        }

        // Auxiliary Methods

        private int asIndex(final int row, final int col) {
            return (row - 1) * gridSize + (col - 1);
        }

        private int topVirtual() {
            return gridSize * gridSize;
        }

        private int bottomVirtual() {
            return gridSize * gridSize + 1;
        }
    }
}
