import present.programmer.util.InputReader;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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

        final List<Integer> leftQualities = IntStream.of(qualities)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        double minCost = Double.POSITIVE_INFINITY;
        for (int i = 0; i <= N - K; i++) {
            final Worker iWorker = workers[i];
            leftQualities.remove(Collections.binarySearch(leftQualities, iWorker.quality));

            double iMinCost = iWorker.wage;
            for (int j = 0; j < K - 1; j++) {
                iMinCost += iWorker.ratio * leftQualities.get(j);
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
        System.out.println("? == " + new MinimumCostToHireWorkers().mincostToHireWorkers(qualities, wages, 7933));
        System.out.println("Execution took " + Duration.between(start, Instant.now()));
	}
}