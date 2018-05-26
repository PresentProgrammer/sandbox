package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class MergeSortTest {

    private static final int NUMBER_OF_INTEGERS = 1_000_000;

    @Test
    public void applyMergeSort_sortWords() {
        commonTests().sortWords(new MergeSort());
    }

    @Test
    public void applyMergeSort_sortIntegers() {
        commonTests().sortIntegers(NUMBER_OF_INTEGERS, new MergeSort());
    }

    @Test
    public void applyMergeSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new MergeSort());
    }
}