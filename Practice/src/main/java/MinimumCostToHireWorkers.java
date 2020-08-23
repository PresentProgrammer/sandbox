import present.programmer.util.InputReader;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Problem #857
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class MinimumCostToHireWorkers {

    public double mincostToHireWorkers(int[] qualities, int[] wages, int K) {
        final int N = qualities.length;
        final Worker[] workers = IntStream.range(0, N)
                .mapToObj(i -> new Worker(qualities[i], wages[i]))
                .sorted(Comparator.comparingDouble((Worker worker) -> worker.ratio))
                .toArray(Worker[]::new);
        final PriorityQueue<Worker> pq = new PriorityQueue<>(
                Comparator.comparingDouble((Worker worker) -> worker.quality).reversed());

        double minCost = Double.POSITIVE_INFINITY;
        int qualitySum = 0;
        for (final Worker worker : workers) {
            pq.offer(worker);
            qualitySum += worker.quality;
            if (pq.size() == K) {
                minCost = Math.min(minCost, worker.ratio * qualitySum);
                qualitySum -= pq.poll().quality;
            }
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
        final int[] wages = new InputReader().readIntArray("/input/538.wages.txt");
        Instant start = Instant.now();
        System.out.println("? == " + new MinimumCostToHireWorkers().mincostToHireWorkers(qualities, wages, 2));
        System.out.println("Execution took " + Duration.between(start, Instant.now()));
	}
}