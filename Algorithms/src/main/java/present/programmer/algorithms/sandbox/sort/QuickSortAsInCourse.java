package present.programmer.algorithms.sandbox.sort;

public class QuickSortAsInCourse extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        new QuickSorter<>(array).sort();
    }

    private static class QuickSorter<T extends Comparable<T>> extends QuickSort.QuickSorter<T> {

        private static final int CUTOFF = 10;

        private QuickSorter(final T[] array) {
            super(array);
        }

        @Override
        void sort(final int begin, final int end) {
            if (end - begin > CUTOFF) {
                swapPartitionElementWithMedian(begin, end);
                final int indexOfElementInPlace = partition(begin, end);
                sort(begin, indexOfElementInPlace);
                sort(indexOfElementInPlace + 1, end);
            }
            else {
                new InsertionSort().applySortingMethodTo(array, begin, end);
            }
        }

        private void swapPartitionElementWithMedian(final int begin, final int end) {
            final int endInclusive = end - 1;
            final int mid = begin + (endInclusive - begin) / 2;
            final int median = medianOf3(begin, mid, endInclusive);
            swap(begin, median);
        }

        @SuppressWarnings("Duplicates")
        private int medianOf3(final int aIndex, final int bIndex, final int cIndex) {
            final T a = array[aIndex];
            final T b = array[bIndex];
            final T c = array[cIndex];
            if (less(a, b)) {
                if (less(b, c)) {
                    return bIndex;
                } else if (less(a, c)) {
                    return cIndex;
                } else {
                    return aIndex;
                }
            } else if (less(c, b)) {
                return bIndex;
            } else if (less(a, c)) {
                return aIndex;
            } else {
                return cIndex;
            }
        }

        @Override
        int partition(final int begin, final int end) {
            final T partitioningElement = array[begin];
            int i = begin + 1;
            int j = end - 1;
            while (didNotCross(i, j)) {
                while (i < end && less(array[i], partitioningElement)) {
                    i++;
                }
                while (greater(array[j], partitioningElement)) {
                    j--;
                }
                if (didNotCross(i, j)) {
                    swap(i, j);
                    i++;
                    j--;
                }
            }
            if (isAfterCrossingGreater(j, partitioningElement)) {
                j--;
            }
            swap(begin, j);
            return j;
        }

        private static boolean didNotCross(final int i, final int j) {
            return i < j;
        }

        private boolean isAfterCrossingGreater(final int j, final T partitioningElement) {
            return greater(array[j], partitioningElement);
        }
    }
}
