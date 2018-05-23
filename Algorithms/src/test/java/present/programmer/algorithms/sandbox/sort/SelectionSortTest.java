package present.programmer.algorithms.sandbox.sort;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

import org.junit.Test;

public class SelectionSortTest {

    @Test
    public void applySelectionSort_sortWords() {
        commonTests().sortWords(new SelectionSort());
    }

    @Test
    public void applySelectionSort_sortIntegers() {
        commonTests().sortIntegers(new SelectionSort());
    }

    @Test
    public void applySelectionSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new SelectionSort());
    }
}