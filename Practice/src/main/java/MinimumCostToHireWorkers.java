import present.programmer.util.InputReader;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
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
                .sorted(Comparator.comparingDouble((Worker worker) -> worker.ratio)
                        .reversed())
                .toArray(Worker[]::new);
        final SalaryCalculator salaryCalculator = new SalaryCalculator(qualities, K);

        double minCost = Double.POSITIVE_INFINITY;
        for (int i = 0; i <= N - K; i++) {
            final Worker worker = workers[i];
            minCost = Math.min(minCost,
                    salaryCalculator.calculateTotalSalaryAndRemoveCurrentWorker(worker.quality, worker.wage));
        }
        return minCost;
    }

    private static class SalaryCalculator {

        private int qualitySum;
        private final CountTreeMap inSum;
        private final CountTreeMap outsideSum;

        SalaryCalculator(int[] qualities, int K) {
            final int N = qualities.length;
            final int[] qualitiesCopy = Arrays.copyOf(qualities, qualities.length);
            Arrays.sort(qualitiesCopy);

            this.qualitySum = sumOfRange(qualitiesCopy, K - 1);
            this.inSum = new CountTreeMap(qualitiesCopy, 0, K - 1);
            this.outsideSum = new CountTreeMap(qualitiesCopy, K - 1, N);
        }

        double calculateTotalSalaryAndRemoveCurrentWorker(int currQuality, int currWage) {
            updateState(currQuality);
            return calculateTotalSalary(currQuality, currWage);
        }

        private void updateState(int currQuality) {
            if (inSum.containsKey(currQuality)) {
                inSum.decreaseCount(currQuality);
                qualitySum -= currQuality;

                final Integer minQualityFromOutsideSum = outsideSum.firstKey();
                outsideSum.decreaseCount(minQualityFromOutsideSum);
                inSum.increaseCount(minQualityFromOutsideSum);
                qualitySum += minQualityFromOutsideSum;
            } else {
                outsideSum.decreaseCount(currQuality);
            }
        }

        double calculateTotalSalary(int currQuality, int currWage) {
            final double ratio = ((double) currWage) / currQuality;
            return currWage + ratio * qualitySum;
        }

        private static int sumOfRange(int[] qualities, int lastExcl) {
            int sum = 0;
            for (int i = 0; i < lastExcl; i++) {
                sum += qualities[i];
            }
            return sum;
        }

        private static class CountTreeMap extends TreeMap<Integer, Integer> {

            CountTreeMap(int[] elements, int firstIncl, int lastExcl) {
                for (int i = firstIncl; i < lastExcl; i++) {
                    increaseCount(elements[i]);
                }
            }

            void increaseCount(Integer key) {
                put(key, getOrDefault(key, 0) + 1);
            }

            void decreaseCount(Integer key) {
                put(key, get(key) - 1);
                if (get(key) == 0) {
                    remove(key);
                }
            }
        }
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