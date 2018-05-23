package present.programmer.algorithms.sandbox.sort;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

import org.junit.Test;

public class MergeSortTest {

    @Test
    public void applyMergeSort_sortWords() {
        commonTests().sortWords(new MergeSort());
    }

    @Test
    public void applyMergeSort_sortIntegers() {
        commonTests().sortIntegers(new MergeSort());
    }

    @Test
    public void applyMergeSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new MergeSort());
    }
}