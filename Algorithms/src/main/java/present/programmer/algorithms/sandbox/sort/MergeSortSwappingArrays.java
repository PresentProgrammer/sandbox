package present.programmer.algorithms.sandbox.sort;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;

public class MergeSortSwappingArrays extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        new MergeSort.MergeSorter<>(array).sort();
    }

    static class MergeSorter<T extends Comparable<T>> {

        private static final int CUTOFF = 7;

        private final T[] array;

        MergeSorter(final T[] array) {
            this.array = array;
        }

        void sort() {
            sort(array, copyOf(array, array.length), 0, array.length);
        }

        // Auxiliary Methods

        /**
         * Optimization to avoid copying elements: result and auxiliaryArray swap roles with each recursive call.
         */
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

        private boolean isMergingNeeded(final T[] result, final int mid) {
            return less(result[mid], result[mid - 1]);
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

        private boolean arePreconditionsMet(final T[] auxiliaryArray, final int begin, final int mid, final int end) {
            return parametersAreValid(begin, mid, end) &&
                    isSorted(auxiliaryArray, begin, mid) &&
                    isSorted(auxiliaryArray, mid, end);
        }

        private boolean parametersAreValid(final int begin, final int mid, final int end) {
            return begin >= 0 &&
                    mid > begin &&
                    end > mid &&
                    end <= array.length;
        }

        private boolean isSorted(final T[] array, final int begin, final int end) {
            for (int i = begin; i < end - 1; i++) {
                if (less(array[i + 1], array[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
