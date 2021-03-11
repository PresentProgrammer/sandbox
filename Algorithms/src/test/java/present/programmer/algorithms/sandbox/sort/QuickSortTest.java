package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class QuickSortTest {

    private static final int NUMBER_OF_INTEGERS = 1_000_000;

    @Test
    public void applyQuickSort_sortWords() {
        commonTests().sortWords(new QuickSort<>());
    }

    @Test
    public void applyQuickSort_sortIntegers() {
        commonTests().sortIntegers(NUMBER_OF_INTEGERS, new QuickSort<>());
    }

    @Test
    public void applyQuickSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new QuickSort<>());
    }
}