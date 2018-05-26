package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class SelectionSortTest {

    private static final int NUMBER_OF_INTEGERS = 10_000;

    @Test
    public void applySelectionSort_sortWords() {
        commonTests().sortWords(new SelectionSort());
    }

    @Test
    public void applySelectionSort_sortIntegers() {
        commonTests().sortIntegers(NUMBER_OF_INTEGERS, new SelectionSort());
    }

    @Test
    public void applySelectionSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new SelectionSort());
    }
}