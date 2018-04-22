package present.programmer.algorithms.sandbox.union.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;

    private double[] thresholds;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n, int trials) {
        validateInputs(n, trials);
        thresholds = new double[trials];
        runExperiments(n, trials);
        produceStats();
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        final PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]", stats.confidenceLo(), stats.confidenceHi());
    }

    // Auxiliary Methods

    private static void validateInputs(final int gridSize, final int trials) {
        if (gridSize < 1 || trials < 1) {
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

    private void produceStats() {
        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        final double halfDifference = CONFIDENCE_95 * stddev() / Math.sqrt(thresholds.length);
        confidenceLo = mean - halfDifference;
        confidenceHi = mean + halfDifference;
    }
}
