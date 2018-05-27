package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class QuickSortAsInCourseTest {

    private static final int NUMBER_OF_INTEGERS = 1_000_000;

    @Test
    public void applySelectionSort_sortWords() {
        commonTests().sortWords(new QuickSortAsInCourse());
    }

    @Test
    public void applySelectionSort_sortIntegers() {
        commonTests().sortIntegers(NUMBER_OF_INTEGERS, new QuickSortAsInCourse());
    }

    @Test
    public void applySelectionSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new QuickSortAsInCourse());
    }
}