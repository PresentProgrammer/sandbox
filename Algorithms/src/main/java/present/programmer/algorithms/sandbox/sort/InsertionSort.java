package present.programmer.algorithms.sandbox.sort;

public class InsertionSort extends SortMethod {

    private static final int NO_STEP = 1;
    private static final int WHOLE_LEFT_SIDE = 0;

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        applySortingMethodTo(array, 0, array.length);
    }

    <T extends Comparable<T>> void applySortingMethodTo(final T[] array, int begin, int end) {
        for (int i = begin + 1; i < end; i++) {
            pushElementUntilLeftSideIsSorted(array, i, NO_STEP, begin);
        }
    }

    protected static <T extends Comparable<T>> void pushElementUntilLeftSideIsSorted(
            final T[] array, final int pushedIndex, final int step) {
        pushElementUntilLeftSideIsSorted(array, pushedIndex, step, WHOLE_LEFT_SIDE);
    }

    private static <T extends Comparable<T>> void pushElementUntilLeftSideIsSorted(
            final T[] array, final int pushedIndex, final int step, final int begin) {
        final T pushedValue = array[pushedIndex];
        int potentialSwapIndex = pushedIndex - step;
        while (potentialSwapIndex >= begin) {
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
