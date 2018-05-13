package present.programmer.algorithms.sandbox.sort.shuffle;

import org.junit.Test;

import java.util.stream.Stream;

import static java.lang.Math.abs;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class UniformRandomPermutationTest {

    private static final int SIZE = 10;
    private static final double EPSILON = 0.01;

    @Test
    public void permutationOfIntegers() {
        final Statistics statistics = generatePermutations();
        assertPermutationsAreUniformlyRandom(statistics);
    }

    @Test
    public void permutationOfEmptyArray_emptyArrayReturned() {
        assertArrayEquals(new Integer[0], new UniformRandomPermutation().of(new Integer[0]));
    }

    // Auxiliary Tests

    @Test
    public void factorialWorksCorrectly() {
        assertEquals(120, factorial(5));
        assertEquals(39_916_800, factorial(11));
        assertEquals(1, factorial(1));
        assertEquals(1, factorial(0));
    }

    // Auxiliary Methods, Classes

    private Statistics generatePermutations() {
        final Statistics statistics = new Statistics();
        final Integer[] sequence = integerSequence();
        for (int i = 0; i < factorial(SIZE); i++) {
            statistics.add(new UniformRandomPermutation().of(sequence));
        }
        return statistics;
    }

    private static Integer[] integerSequence() {
        final Integer[] sequence = new Integer[SIZE];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = i;
        }
        return sequence;
    }

    private long factorial(final int n) {
        return n <= 1 ? 1 : factorial(n - 1) * n;
    }

    private static void assertPermutationsAreUniformlyRandom(final Statistics statistics) {
        final double expectedAverageValue = Stream.of(integerSequence())
                .mapToInt(Integer::intValue)
                .average()
                .orElse(Double.NaN);
        final double[] averageValues = statistics.averageValuesOfElements();
        for (final double averageValue : averageValues) {
            assertGravitates(expectedAverageValue, averageValue);
        }
    }

    private static void assertGravitates(final double expectedAverageValue, final double averageValue) {
        assertTrue(abs(expectedAverageValue - averageValue) < EPSILON);
    }


    private static class Statistics {

        private long[] sumOfElements = new long[SIZE];
        private int numberOfPermutations = 0;

        void add(final Integer[] permutation) {
            for (int i = 0; i < SIZE; i++) {
                sumOfElements[i] += permutation[i];
            }
            numberOfPermutations++;
        }

        double[] averageValuesOfElements() {
            final double[] averages = new double[SIZE];
            for (int i = 0; i < SIZE; i++) {
                averages[i] = (double) sumOfElements[i] / numberOfPermutations;
            }
            return averages;
        }
    }
}