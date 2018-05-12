package present.programmer.algorithms.sandbox.sort;

public class SelectionSort extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            final int minIndex = findMinIndexInSubArray(array, i);
            swap(array, i, minIndex);
        }
    }

    // Auxiliary Methods

    private <T extends Comparable<T>> int findMinIndexInSubArray(final T[] array, final int i) {
        int minIndex = i;
        T minValue = array[i];
        for (int j = i + 1; j < array.length; j++) {
            if (less(array[j], minValue)) {
                minIndex = j;
                minValue = array[j];
            }
        }
        return minIndex;
    }

    private static <T> void swap(final T[] array, final int firstIndex, final int secondIndex) {
        final T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
}
