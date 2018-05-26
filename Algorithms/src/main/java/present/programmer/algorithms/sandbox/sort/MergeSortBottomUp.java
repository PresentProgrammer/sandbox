package present.programmer.algorithms.sandbox.sort;

/**
 * Merge Sort without recursion.
 */
public class MergeSortBottomUp extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        new MergeSorter<>(array).sort();
    }

    private static class MergeSorter<T extends Comparable<T>> extends MergeSort.MergeSorter<T> {

        private MergeSorter(final T[] unsortedArray) {
            super(unsortedArray);
        }

        @Override
        void sort() {
            for (int arraySize = 1; arraySize < result.length; arraySize *= 2) {
                for (int begin = 0; begin + arraySize < result.length; begin += arraySize * 2) {
                    final int mid = begin + arraySize;
                    final int possiblyOutOfBoundsEnd = begin + arraySize * 2;
                    final int end = possiblyOutOfBoundsEnd <= result.length ? possiblyOutOfBoundsEnd : result.length;
                    merge(begin, mid, end);
                }
            }
        }
    }
}