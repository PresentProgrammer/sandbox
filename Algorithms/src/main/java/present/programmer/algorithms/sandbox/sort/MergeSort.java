package present.programmer.algorithms.sandbox.sort;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;
import static present.programmer.algorithms.sandbox.sort.util.Compare.less;
import static present.programmer.algorithms.sandbox.sort.util.Compare.lessOrEquals;

public class MergeSort extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        new MergeSorter<>(array).sort();
    }

    static class MergeSorter<T extends Comparable<T>> {

        private static final int CUTOFF = 7;

        final T[] result;
        final T[] auxiliaryArray;

        MergeSorter(final T[] unsortedArray) {
            result = unsortedArray;
            auxiliaryArray = copyOf(unsortedArray, unsortedArray.length);
        }

        void sort() {
            sort(0, result.length);
        }

        // Auxiliary Methods

        private void sort(final int begin, final int end) {
            if (end - begin > CUTOFF) {
                final int mid = begin + ((end - begin) / 2);
                sort(begin, mid);
                sort(mid, end);
                if (isMergingNeeded(mid)) {
                    merge(begin, mid, end);
                }
            } else {
                new InsertionSort().applySortingMethodTo(result, begin, end);
            }
        }

        boolean isMergingNeeded(final int mid) {
            return isMergingNeeded(result, mid);
        }

        boolean isMergingNeeded(final T[] result, final int mid) {
            return less(result[mid], result[mid - 1]);
        }

        void merge(final int begin, final int mid, final int end) {
            assert arePreconditionsMet(begin, mid, end);
            prepareAuxiliaryArray(begin, end);
            mergeFromAuxiliaryArray(begin, mid, end);
            assert isSorted(result, begin, end);
        }

        private void prepareAuxiliaryArray(final int begin, final int end) {
            arraycopy(result, begin, auxiliaryArray, begin, end - begin);
        }

        private void mergeFromAuxiliaryArray(final int begin, final int mid, final int end) {
            int k = begin;
            int i = begin;
            int j = mid;
            while (i < mid && j < end) {
                final T left = auxiliaryArray[i];
                final T right = auxiliaryArray[j];
                if (lessOrEquals(left, right)) {
                    result[k++] = left;
                    i++;
                } else {
                    result[k++] = right;
                    j++;
                }
            }
            copyLeftSubArrayIfNotExhausted(mid, i, k);
        }

        private void copyLeftSubArrayIfNotExhausted(final int mid, final int i, final int k) {
            arraycopy(auxiliaryArray, i, result, k, mid - i);
        }

        private boolean arePreconditionsMet(final int begin, final int mid, final int end) {
            return arePreconditionsMet(result, begin, mid, end);
        }

        boolean arePreconditionsMet(final T[] array, final int begin, final int mid, final int end) {
            return parametersAreValid(array, begin, mid, end) &&
                    isSorted(array, begin, mid) &&
                    isSorted(array, mid, end);
        }

        private boolean parametersAreValid(final T[] auxiliaryArray, final int begin, final int mid, final int end) {
            return begin >= 0 &&
                    mid > begin &&
                    end > mid &&
                    end <= auxiliaryArray.length;
        }

        boolean isSorted(final T[] array, final int begin, final int end) {
            for (int i = begin; i < end - 1; i++) {
                if (less(array[i + 1], array[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
