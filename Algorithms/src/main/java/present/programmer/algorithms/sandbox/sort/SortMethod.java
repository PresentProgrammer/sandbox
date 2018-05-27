package present.programmer.algorithms.sandbox.sort;

import static java.util.Arrays.copyOf;

abstract class SortMethod {

    <T extends Comparable<T>> T[] sort(T[] unsortedArray) {
        final T[] array = copyOf(unsortedArray, unsortedArray.length);
        applySortingMethodTo(array);
        return array;
    }

    abstract <T extends Comparable<T>> void applySortingMethodTo(T[] array);

    static <T> boolean less(final Comparable<T> first, final T second) {
        return first.compareTo(second) < 0;
    }

    static <T> boolean lessOrEquals(final Comparable<T> first, final T second) {
        return first.compareTo(second) <= 0;
    }

    static <T> boolean greater(final Comparable<T> first, final T second) {
        return first.compareTo(second) > 0;
    }
}
