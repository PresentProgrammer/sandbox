package present.programmer.algorithms.sandbox.sort;

public class ShellSort extends InsertionSort {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        sortWithStep(array, 1);
    }

    /**
     * The method of increasing the step can be overridden for (possibly) better performance of the algorithm.
     */
    protected int increaseStep(final int previousStep) {
        return previousStep * 3 + 1;
    }

    // Auxiliary Methods

    private <T extends Comparable<T>> void sortWithStep(final T[] array, final int step) {
        if (step < array.length) {
            sortWithStep(array, increaseStep(step));
            for (int i = 0; i < step; i++) {
                for (int j = i + step; j < array.length; j += step) {
                    pushElementUntilLeftSideIsSorted(array, j, step);
                }
            }
        }
    }
}
