package present.programmer.algorithms.sandbox.sort;

import java.time.Duration;
import java.time.LocalTime;

import static java.lang.System.arraycopy;
import static java.time.LocalTime.now;
import static java.util.Arrays.sort;
import static org.junit.Assert.assertArrayEquals;

class CommonTests {

    private static final int FROM_FIRST_ELEMENT = 0;

    static CommonTests commonTests() {
        return new CommonTests();
    }

    void sortWords(final SortMethod sortMethod) {
        assertSortingWorksCorrectly(sortMethod, inputWords());
    }

    void sortIntegers(final int size, final SortMethod sortMethod) {
        assertSortingWorksCorrectly(sortMethod, inputIntegers(size));
    }

    void sortAlreadySortedIntegers(final int size, final SortMethod sortMethod) {
        assertSortingWorksCorrectly(sortMethod, inputSortedIntegers(size));
    }

    void sortEmptyArray(final SortMethod sortMethod) {
        sortMethod.sort(new Integer[0]);
    }

    // Auxiliary Methods

    private static <T extends Comparable<T>> void assertSortingWorksCorrectly(final SortMethod sortMethod, final T[] unsortedArray) {
        final T[] arraySortedByJavaApi = sortByJavaApi(unsortedArray);
        final T[] sortedArray = sortByMethod(sortMethod, unsortedArray);
        assertArrayEquals(arraySortedByJavaApi, sortedArray);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] sortByJavaApi(final T[] unsortedArray) {
        final T[] arrayCopy = (T[]) new Comparable[unsortedArray.length];
        arraycopy(unsortedArray, FROM_FIRST_ELEMENT, arrayCopy, FROM_FIRST_ELEMENT, unsortedArray.length);
        sort(arrayCopy);
        return arrayCopy;
    }

    private static <T extends Comparable<T>> T[] sortByMethod(final SortMethod sortMethod, final T[] unsortedArray) {
        final LocalTime timeBeforeSorting = now();
        final T[] sorted = sortMethod.sort(unsortedArray);
        System.out.println("Sorting took: " + Duration.between(timeBeforeSorting, now()));
        return sorted;
    }

    private static String[] inputWords() {
        return new Inputs().getUnsortedWords();
    }

    private static Integer[] inputIntegers(final int size) {
        return new Inputs().getUnsortedIntegers(size);
    }

    private static Integer[] inputSortedIntegers(final int size) {
        return new Inputs().getSortedIntegers(size);
    }
}
