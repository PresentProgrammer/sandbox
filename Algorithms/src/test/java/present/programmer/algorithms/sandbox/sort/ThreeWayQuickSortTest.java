package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class ThreeWayQuickSortTest {

    private static final int NUMBER_OF_INTEGERS = 1_000_000;

    @Test
    public void applySelectionSort_sortWords() {
        commonTests().sortWords(new ThreeWayQuickSort<>());
    }

    @Test
    public void applySelectionSort_sortIntegers() {
        commonTests().sortIntegers(NUMBER_OF_INTEGERS, new ThreeWayQuickSort<>());
    }

    @Test
    public void applySelectionSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new ThreeWayQuickSort<>());
    }
}