package present.programmer.algorithms.sandbox.union.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Note: To get Percolation vizualizer classes, join the course Algorithms, Part 1 at Coursera.
 * See https://www.coursera.org/learn/algorithms-part1
 */
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
            final boolean belowSiteRootReachedBottom = tryUnionBelowSite(row, col);
            final boolean aboveSiteRootReachedBottom = tryUnionAboveSite(row, col);
            final boolean leftSiteRootReachedBottom = tryUnionLeftSite(row, col);
            final boolean rightSiteRootReachedBottom = tryUnionRightSite(row, col);
            if (belowSiteRootReachedBottom || aboveSiteRootReachedBottom ||
                    leftSiteRootReachedBottom || rightSiteRootReachedBottom) {
                weightedQuickUnion.makeRootReachBottom(row, col);
            }
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

    private boolean tryUnionBelowSite(final int row, final int col) {
        try {
            return unionIfSecondIsOpen(row, col, row + 1, col);
        } catch (final IllegalArgumentException e) {
            weightedQuickUnion.unionWithBottom(col);
            return true;
        }
    }

    private boolean tryUnionAboveSite(final int row, final int col) {
        try {
            return unionIfSecondIsOpen(row, col, row - 1, col);
        } catch (final IllegalArgumentException e) {
            final boolean previousRootReachedBottom = weightedQuickUnion.topVirtualRootReachesBottom();
            weightedQuickUnion.unionWithTopVirtual(col);
            return previousRootReachedBottom;
        }
    }

    private boolean tryUnionLeftSite(final int row, final int col) {
        try {
            return unionIfSecondIsOpen(row, col, row, col - 1);
        } catch (final IllegalArgumentException e) {
            return false;
        }
    }

    private boolean tryUnionRightSite(final int row, final int col) {
        try {
            return unionIfSecondIsOpen(row, col, row, col + 1);
        } catch (final IllegalArgumentException e) {
            return false;
        }
    }

    private boolean unionIfSecondIsOpen(final int pRow, final int pCol, final int qRow, final int qCol) {
        if (isOpen(qRow, qCol)) {
            final boolean previousRootReachedBottom = weightedQuickUnion.rootReachesBottom(qRow, qCol);
            weightedQuickUnion.union(pRow, pCol, qRow, qCol);
            return previousRootReachedBottom;
        } else {
            return false;
        }
    }

    private int asIndex(final int rowOrCol) {
        return rowOrCol - 1;
    }

    /**
     * Wrapper of WeightedQuickUnionUF, provides convenient API for solving Percolation task.
     * Tracks not only all grid sites, but also 1 virtual site at the top of the grid.
     * Objects from 0 through n - 1 are considered grid sites, n-th is the top virtual site.
     * Field {@link PercolationWeightedQuickUnion#rootReachesBottom} is added to keep track whether root
     * belongs to the tree which reaches bottom.
     */
    private static class PercolationWeightedQuickUnion {

        private final WeightedQuickUnionUF target;
        private final boolean[] rootReachesBottom;
        private final int gridSize;

        PercolationWeightedQuickUnion(final int size) {
            target = new WeightedQuickUnionUF(gridPlusVirtual(size));
            this.gridSize = size;
            this.rootReachesBottom = new boolean[gridPlusVirtual(size)];
        }

        boolean connectedToTop(final int row, final int col) {
            return target.connected(asIndex(row, col), topVirtual());
        }

        boolean isPercolate() {
            return topVirtualRootReachesBottom();
        }

        boolean topVirtualRootReachesBottom() {
            return rootReachesBottom[rootOf(topVirtual())];
        }

        boolean rootReachesBottom(final int row, final int col) {
            return rootReachesBottom[rootOf(row, col)];
        }

        void makeRootReachBottom(final int row, final int col) {
            rootReachesBottom[rootOf(row, col)] = true;
        }

        void union(final int pRow, final int pCol, final int qRow, final int qCol) {
            target.union(asIndex(pRow, pCol), asIndex(qRow, qCol));
        }

        void unionWithTopVirtual(final int col) {
            target.union(asIndex(1, col), topVirtual());
        }

        void unionWithBottom(final int col) {
            rootReachesBottom[rootOf(gridSize, col)] = true;
        }

        // Auxiliary Methods

        private static int gridPlusVirtual(final int gridSize) {
            return gridSize * gridSize + 1;
        }

        private int rootOf(final int row, final int col) {
            return target.find(asIndex(row, col));
        }

        private int rootOf(final int index) {
            return target.find(index);
        }

        private int asIndex(final int row, final int col) {
            return (row - 1) * gridSize + (col - 1);
        }

        private int topVirtual() {
            return gridSize * gridSize;
        }
    }
}
