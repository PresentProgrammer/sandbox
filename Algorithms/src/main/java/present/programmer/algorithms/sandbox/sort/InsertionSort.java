package present.programmer.algorithms.sandbox.sort;

public class InsertionSort extends SortMethod {

    private static final int NO_STEP = 1;

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        for (int i = 1; i < array.length; i++) {
            pushElementUntilLeftSideIsSorted(array, i, NO_STEP);
        }
    }

    protected static <T extends Comparable<T>> void pushElementUntilLeftSideIsSorted(
            final T[] array, final int pushedIndex, final int step) {
        final T pushedValue = array[pushedIndex];
        int potentialSwapIndex = pushedIndex - step;
        while (potentialSwapIndex >= 0) {
            final T potentialSwapValue = array[potentialSwapIndex];
            if (less(pushedValue, potentialSwapValue)) {
                swap(array, potentialSwapIndex, step, pushedValue, potentialSwapValue);
                potentialSwapIndex -= step;
            } else {
                break;
            }
        }
    }

    // Auxiliary Methods

    private static <T extends Comparable<T>> void swap(final T[] array, final int potentialSwapIndex, final int step,
             final T pushedValue, final T potentialSwapValue) {
        array[potentialSwapIndex] = pushedValue;
        array[potentialSwapIndex + step] = potentialSwapValue;
    }
}
