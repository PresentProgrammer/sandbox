package present.programmer.algorithms.sandbox.sort;

import static java.lang.System.arraycopy;

/**
 * Optimization to avoid copying elements to auxiliary array: result and auxiliaryArray swap roles with each recursive call.
 */
public class MergeSortSwappingArrays extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        new MergeSorter<>(array).sort();
    }

    private static class MergeSorter<T extends Comparable<T>> extends MergeSort.MergeSorter<T> {

        private static final int CUTOFF = 7;

        MergeSorter(final T[] array) {
            super(array);
        }

        @Override
        void sort() {
            sort(result, auxiliaryArray, 0, result.length);
        }

        // Auxiliary Methods

        private void sort(T[] result, T[] auxiliaryArray, final int begin, final int end) {
            if (end - begin > CUTOFF) {
                final int mid = begin + ((end - begin) / 2);
                sort(auxiliaryArray, result, begin, mid);
                sort(auxiliaryArray, result, mid, end);
                if (isMergingNeeded(auxiliaryArray, mid)) {
                    merge(auxiliaryArray, result, begin, mid, end);
                } else {
                    arraycopy(auxiliaryArray, begin, result, begin, end - begin);
                }
            } else {
                new InsertionSort().applySortingMethodTo(result, begin, end);
            }
        }

        private void merge(T[] auxiliaryArray, T[] result, final int begin, final int mid, final int end) {
            assert arePreconditionsMet(auxiliaryArray, begin, mid, end);
            mergeFromAuxiliaryArray(auxiliaryArray, result, begin, mid, end);
            assert isSorted(result, begin, end);
        }

        void mergeFromAuxiliaryArray(T[] auxiliaryArray, T[] result, final int begin, final int mid, final int end) {
            int i = begin;
            int j = mid;
            for (int k = begin; k < end; k++) {
                if (i >= mid) {
                    result[k] = auxiliaryArray[j++];
                } else if (j >= end) {
                    result[k] = auxiliaryArray[i++];
                } else if (lessOrEquals(auxiliaryArray[i], auxiliaryArray[j])) {
                    result[k] = auxiliaryArray[i++];
                } else {
                    result[k] = auxiliaryArray[j++];
                }
            }
        }
    }
}
