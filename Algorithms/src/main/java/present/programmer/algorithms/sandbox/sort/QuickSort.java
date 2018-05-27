package present.programmer.algorithms.sandbox.sort;

import present.programmer.algorithms.sandbox.sort.shuffle.UniformRandomPermutation;

import static java.lang.System.arraycopy;

public class QuickSort extends SortMethod {

    @Override
    <T extends Comparable<T>> void applySortingMethodTo(final T[] array) {
        new QuickSorter<>(array).sort();
    }

    static class QuickSorter<T extends Comparable<T>> {

        static final int ARRAY_BEGINNING = 0;

        final T[] array;

        QuickSorter(final T[] array) {
            this.array = array;
        }

        void sort() {
            shuffle();
            sort(ARRAY_BEGINNING, array.length);
        }

        // Auxiliary Methods

        private void shuffle() {
            final T[] shuffledArray = new UniformRandomPermutation().of(array);
            arraycopy(shuffledArray, ARRAY_BEGINNING, array, ARRAY_BEGINNING, array.length);
        }

        void sort(final int begin, final int end) {
            if (areAtLeastTwoElements(begin, end)) {
                final int indexOfElementInPlace = partition(begin, end);
                sort(begin, indexOfElementInPlace);
                sort(indexOfElementInPlace + 1, end);
            }
        }

        private boolean areAtLeastTwoElements(final int begin, final int end) {
            return end - begin >= 2;
        }

        int partition(final int begin, final int end) {
            final T partitioningElement = array[begin];
            int i = begin + 1;
            int j = end - 1;
            while (i <= j) {
                while (i <= j && less(array[i], partitioningElement)) {
                    i++;
                }
                while (i <= j && greater(array[j], partitioningElement)) {
                    j--;
                }
                if (i < j) {
                    swap(i, j);
                    i++;
                    j--;
                } else if (i == j) {
                    j--;
                }
            }
            swap(begin, j);
            return j;
        }

        void swap(final int firstIndex, final int secondIndex) {
            final T temp = array[firstIndex];
            array[firstIndex] = array[secondIndex];
            array[secondIndex] = temp;
        }
    }
}
