package present.programmer.algorithms.sandbox.union.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private double[] thresholds;

    public PercolationStats(int n, int trials) {
        mustBePositive(n);
        mustBePositive(trials);
        thresholds = new double[trials];
        runExperiments(n, trials);
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(thresholds.length);
    }

    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(thresholds.length);
    }

    public static void main(String[] args) {
        final PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean \t\t = " + stats.mean());
        System.out.println("stddev \t\t = " + stats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]", stats.confidenceLo(), stats.confidenceHi());
    }

    // Auxiliary Methods

    private static void mustBePositive(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("grid size and trial number must be positive numbers");
        }
    }

    private void runExperiments(final int n, final int trials) {
        for (int trial = 0; trial < trials; trial++) {
            final Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(randomFrom1To(n), randomFrom1To(n));
            }
            thresholds[trial] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    private static int randomFrom1To(final int n) {
        return StdRandom.uniform(n) + 1;
    }
}
