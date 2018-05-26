package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class MergeSortBottomUpTest {

    private static final int NUMBER_OF_INTEGERS = 1_000_000;

    @Test
    public void applyMergeSort_sortWords() {
        commonTests().sortWords(new MergeSortBottomUp());
    }

    @Test
    public void applyMergeSort_sortIntegers() {
        commonTests().sortIntegers(NUMBER_OF_INTEGERS, new MergeSortBottomUp());
    }

    @Test
    public void applyMergeSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new MergeSortBottomUp());
    }
}