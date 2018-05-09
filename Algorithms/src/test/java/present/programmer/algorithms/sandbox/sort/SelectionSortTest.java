package present.programmer.algorithms.sandbox.sort;

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

    // Auxiliary Methods

    private CommonTests commonTests() {
        return new CommonTests();
    }
}