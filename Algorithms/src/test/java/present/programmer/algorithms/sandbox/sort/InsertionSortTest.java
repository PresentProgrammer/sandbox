package present.programmer.algorithms.sandbox.sort;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

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
}