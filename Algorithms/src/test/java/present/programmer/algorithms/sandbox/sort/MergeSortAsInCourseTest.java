package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class MergeSortAsInCourseTest {

    @Test
    public void applyMergeSort_sortWords() {
        commonTests().sortWords(new MergeSortAsInCourse());
    }

    @Test
    public void applyMergeSort_sortIntegers() {
        commonTests().sortIntegers(new MergeSortAsInCourse());
    }

    @Test
    public void applyMergeSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new MergeSortAsInCourse());
    }
}