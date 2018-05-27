package present.programmer.algorithms.sandbox.sort.shuffle;

import java.util.Random;

import static java.util.Arrays.copyOf;

public class UniformRandomPermutation {

    public <T> T[] of(final T[] input) {
        final T[] array = copyOf(input, input.length);
        final Random random = new Random();
        for (int i = 1; i < array.length; i++) {
            swap(array, i, random.nextInt(i + 1));
        }
        return array;
    }

    // Auxiliary Methods

    private static <T> void swap(final T[] array, final int firstIndex, final int secondIndex) {
        final T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
