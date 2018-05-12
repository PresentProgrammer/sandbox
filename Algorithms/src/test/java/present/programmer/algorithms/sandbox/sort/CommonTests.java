package present.programmer.algorithms.sandbox.sort;

import java.util.Arrays;

import static java.lang.System.arraycopy;
import static java.util.Arrays.sort;
import static org.junit.Assert.assertArrayEquals;

class CommonTests {

    private static final int FROM_FIRST_ELEMENT = 0;

    void sortWords(final SortMethod sortMethod) {
        assertSortingWorksCorrectly(sortMethod, inputWords());
    }

    void sortIntegers(final SortMethod sortMethod) {
        assertSortingWorksCorrectly(sortMethod, inputIntegers());
    }

    void sortEmptyArray(final SortMethod sortMethod) {
        sortMethod.sort(new Integer[0]);
    }

    // Auxiliary Methods

    private static <T extends Comparable<T>> void assertSortingWorksCorrectly(final SortMethod sortMethod, final T[]
            unsortedArray) {
        final T[] arraySortedByJavaApi = sortByJavaApi(unsortedArray);
        final T[] sortedArray = sortMethod.sort(unsortedArray);
        assertArrayEquals(arraySortedByJavaApi, sortedArray);
        System.out.println(Arrays.deepToString(sortedArray));
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] sortByJavaApi(final T[] unsortedArray) {
        final T[] arrayCopy = (T[]) new Comparable[unsortedArray.length];
        arraycopy(unsortedArray, FROM_FIRST_ELEMENT, arrayCopy, FROM_FIRST_ELEMENT, unsortedArray.length);
        sort(arrayCopy);
        return arrayCopy;
    }

    private static String[] inputWords() {
        return new Inputs().getUnsortedWords();
    }

    private Integer[] inputIntegers() {
        return new Inputs().getUnsortedIntegers();
    }
}
