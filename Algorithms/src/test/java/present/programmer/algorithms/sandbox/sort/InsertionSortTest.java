package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class InsertionSortTest {

    private static final int NUMBER_OF_INTEGERS = 10_000;

    @Test
    public void applyInsertionSort_sortWords() {
        commonTests().sortWords(new InsertionSort<>());
    }

    @Test
    public void applyInsertionSort_sortIntegers() {
        commonTests().sortIntegers(NUMBER_OF_INTEGERS, new InsertionSort<>());
    }

    @Test
    public void applyInsertionSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new InsertionSort<>());
    }
}