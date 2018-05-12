package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

public class InsertionSortTest {

    @Test
    public void applyInsertionSort_sortWords() {
        commonTests().sortWords(new InsertionSort());
    }

    @Test
    public void applyInsertionSort_sortIntegers() {
        commonTests().sortIntegers(new InsertionSort());
    }

    @Test
    public void applyInsertionSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new InsertionSort());
    }

    // Auxiliary Methods

    private CommonTests commonTests() {
        return new CommonTests();
    }
}