import present.programmer.util.InputReader;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * Problem #857
 * Time complexity: O(N^2 * log N)
 * Space complexity: O(N)
 **/
public class MinimumCostToHireWorkers {

    public double mincostToHireWorkers(int[] qualities, int[] wages, int K) {
        final int N = qualities.length;
        final Worker[] workers = IntStream.range(0, N)
                .mapToObj(i -> new Worker(qualities[i], wages[i]))
                .sorted(Comparator.comparingDouble((Worker worker) -> worker.ratio)
                        .reversed())
                .toArray(Worker[]::new);

        final TreeMap<Integer, Integer> leftQualities = new TreeMap<>();
        for (final int quality : qualities) {
            leftQualities.put(quality, leftQualities.getOrDefault(quality, 0) + 1);
        }

        double minCost = Double.POSITIVE_INFINITY;
        for (int i = 0; i <= N - K; i++) {
            final Worker iWorker = workers[i];
            if (leftQualities.get(iWorker.quality) == 1) {
                leftQualities.remove(iWorker.quality);
            } else {
                leftQualities.put(iWorker.quality, leftQualities.get(iWorker.quality) - 1);
            }

            double iMinCost = iWorker.wage;
            int leftToHire = K - 1;
            int minPossibleQuality = 0;
            while (leftToHire > 0) {
                minPossibleQuality = leftQualities.higherKey(minPossibleQuality);
                final int minPossibleQualityCount = leftQualities.get(minPossibleQuality);
                iMinCost += iWorker.ratio * minPossibleQuality * Math.min(minPossibleQualityCount, leftToHire);
                leftToHire -= minPossibleQualityCount;
            }

            minCost = Math.min(minCost, iMinCost);
        }
        return minCost;
    }

    private static class Worker {
        final int quality;
        final int wage;
        final double ratio;

        Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            this.ratio = ((double) wage) / quality;
        }
    }
    
    public static void main(final String[] args) {
        final int[] qualities = new InputReader().readIntArray("/input/538.qualities.txt");
        final int[] wages = new InputReader().readIntArray("/input/538.qualities.txt");
        Instant start = Instant.now();
        System.out.println("? == " + new MinimumCostToHireWorkers().mincostToHireWorkers(qualities, wages, 3));
        System.out.println("Execution took " + Duration.between(start, Instant.now()));
	}
}