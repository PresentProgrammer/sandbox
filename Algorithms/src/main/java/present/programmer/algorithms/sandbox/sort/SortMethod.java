package present.programmer.algorithms.sandbox.sort;

import static java.lang.System.arraycopy;

abstract class SortMethod {

    private static final int FROM_FIRST_ELEMENT = 0;

    <T extends Comparable<T>> T[] sort(T[] unsortedArray) {
        final T[] array = makeCopyNotKeepInputUntouched(unsortedArray);
        applySortingMethodTo(array);
        return array;
    }

    abstract <T extends Comparable<T>> void applySortingMethodTo(T[] array);

    // Auxiliary Methods

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] makeCopyNotKeepInputUntouched(final T[] unsortedArray) {
        final T[] arrayCopy = (T[]) new Comparable[unsortedArray.length];
        arraycopy(unsortedArray, FROM_FIRST_ELEMENT, arrayCopy, FROM_FIRST_ELEMENT, unsortedArray.length);
        return arrayCopy;
    }
}
