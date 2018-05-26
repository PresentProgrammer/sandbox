package present.programmer.algorithms.sandbox.sort;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;

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
                merge(begin, mid, end);
            } else {
                new InsertionSort().applySortingMethodTo(result, begin, end);
            }
        }

        private void merge(final int begin, final int mid, final int end) {
            assert arePreconditionsMet(begin, mid, end);
            prepareAuxiliaryArray(begin, end);
            mergeFromAuxiliaryArray(begin, mid, end);
            assert isResultSorted(begin, end);
        }

        private void prepareAuxiliaryArray(final int begin, final int end) {
            arraycopy(result, begin, auxiliaryArray, begin, end - begin);
        }

        void mergeFromAuxiliaryArray(final int begin, final int mid, final int end) {
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
            return parametersAreValid(begin, mid, end) &&
                    isResultSorted(begin, mid) &&
                    isResultSorted(mid, end);
        }

        private boolean parametersAreValid(final int begin, final int mid, final int end) {
            return begin >= 0 &&
                    mid > begin &&
                    end > mid &&
                    end <= result.length;
        }

        private boolean isResultSorted(final int begin, final int end) {
            for (int i = begin; i < end - 1; i++) {
                if (less(result[i + 1], result[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
