package present.programmer.algorithms.sandbox.sort;

import static java.util.Arrays.copyOf;

abstract class SortMethod {

    <T extends Comparable<T>> T[] sort(T[] unsortedArray) {
        final T[] array = copyOf(unsortedArray, unsortedArray.length);
        applySortingMethodTo(array);
        return array;
    }

    abstract <T extends Comparable<T>> void applySortingMethodTo(T[] array);
}
