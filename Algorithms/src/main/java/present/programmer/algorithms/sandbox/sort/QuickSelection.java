package present.programmer.algorithms.sandbox.sort;

import present.programmer.algorithms.sandbox.sort.QuickSort.QuickSorter;

import static java.util.Arrays.copyOf;

public class QuickSelection {

    <T extends Comparable<T>> T[] selectFirstKElements(final T[] array, final int k) {
        final T[] copyOfInput = copyInputNotToChangeIt(array);
        return new QuickSelector<>(copyOfInput, k).selectFirstKElements();
    }

    <T extends Comparable<T>> T selectKElement(final T[] array, final int k) {
        final T[] copyOfInput = copyInputNotToChangeIt(array);
        return new QuickSelector<>(copyOfInput, k).selectKElement();
    }

    private <T extends Comparable<T>> T[] copyInputNotToChangeIt(final T[] array) {
        return copyOf(array, array.length);
    }

    static class QuickSelector<T extends Comparable<T>> extends QuickSorter<T> {

        private final int k;

        QuickSelector(final T[] array, final int k) {
            super(array);
            this.k = k;
        }

        T[] selectFirstKElements() {
            shuffle();
            return partitionUntilKFirstElementsFound(ARRAY_BEGINNING, array.length);
        }

        T selectKElement() {
            shuffle();
            return partitionUntilKElementsFound(ARRAY_BEGINNING, array.length);
        }

        // Auxiliary Methods

        private T[] partitionUntilKFirstElementsFound(final int begin, final int end) {
            final int indexOfElementInPlace = partition(begin, end);
            if (indexOfElementInPlace == k) {
                return copyOf(array, k);
            } else if (indexOfElementInPlace > k) {
                return partitionUntilKFirstElementsFound(begin, indexOfElementInPlace);
            } else {
                return partitionUntilKFirstElementsFound(indexOfElementInPlace + 1, end);
            }
        }

        private T partitionUntilKElementsFound(final int begin, final int end) {
            final int indexOfElementInPlace = partition(begin, end);
            if (indexOfElementInPlace == k) {
                return array[k];
            } else if (indexOfElementInPlace > k) {
                return partitionUntilKElementsFound(begin, indexOfElementInPlace);
            } else {
                return partitionUntilKElementsFound(indexOfElementInPlace + 1, end);
            }
        }
    }
}
