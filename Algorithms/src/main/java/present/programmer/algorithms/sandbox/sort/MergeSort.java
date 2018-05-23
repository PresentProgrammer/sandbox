package present.programmer.algorithms.sandbox.sort;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;

public class MergeSort extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        new MergeSorter<>(array).sort();
    }

    private static class MergeSorter<T extends Comparable<T>> {

        private final T[] result;
        private final T[] auxiliaryArray;

        /**
         * Note: auxiliaryArray is one element larger to optimize number of array accesses while merging.
         */
        MergeSorter(final T[] unsortedArray) {
            result = unsortedArray;
            auxiliaryArray = copyOf(unsortedArray, unsortedArray.length + 1);
        }

        void sort() {
            divideAndConquer(0, result.length);
        }

        // Auxiliary Methods

        private void divideAndConquer(final int begin, final int end) {
            if (end - begin > 1) {
                final int mid = begin + ((end - begin) / 2);
                divideAndConquer(begin, mid);
                divideAndConquer(mid, end);
                merge(begin, mid, end);
            }
        }

        private void merge(final int begin, final int mid, final int end) {
            try {
                assert arePreconditionsMet(begin, mid, end);
                prepareAuxiliaryArray(begin, end);
                int k = begin;
                int i = begin;
                int j = mid;
                T left = auxiliaryArray[i];
                T right = auxiliaryArray[j];
                while (i < mid && j < end) {
                    if (lessOrEquals(left, right)) {
                        result[k++] = left;
                        left = auxiliaryArray[++i];
                    } else {
                        result[k++] = right;
                        right = auxiliaryArray[++j];
                    }
                }
                copyLeftSubArrayIfNotExhausted(mid, i, k);
                assert isResultSorted(begin, end);
            } catch (final Throwable e) {
                System.out.printf("begin: %d, mid: %d, end: %d", begin, mid, end);
                throw e;
            }
        }

        private void prepareAuxiliaryArray(final int begin, final int end) {
            arraycopy(result, begin, auxiliaryArray, begin, end - begin);
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
