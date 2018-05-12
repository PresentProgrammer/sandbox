package present.programmer.algorithms.sandbox.sort;

public class InsertionSort extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        for (int i = 1; i < array.length; i++) {
            pushElementUntilLeftSideIsSorted(array, i);
        }
    }

    // Auxiliary Methods

    private <T extends Comparable<T>> void pushElementUntilLeftSideIsSorted(final T[] array, final int i) {
        final T pushedValue = array[i];
        int potentialSwapIndex = i - 1;
        while (potentialSwapIndex >= 0) {
            final T potentialSwapValue = array[potentialSwapIndex];
            if (less(pushedValue, potentialSwapValue)) {
                swap(array, potentialSwapIndex, pushedValue, potentialSwapValue);
                potentialSwapIndex--;
            } else {
                break;
            }
        }
    }

    private static <T extends Comparable<T>> void swap(final T[] array, final int potentialSwapIndex,
             final T pushedValue, final T potentialSwapValue) {
        array[potentialSwapIndex] = pushedValue;
        array[potentialSwapIndex + 1] = potentialSwapValue;
    }
}
