package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.sort.CommonTests.commonTests;

public class ShellSortTest {

    private static final int NUMBER_OF_INTEGERS = 1_000_000;

    @Test
    public void applyShellSort_sortWords() {
        commonTests().sortWords(new ShellSort<>());
    }

    @Test
    public void applyShellSort_sortIntegers() {
        commonTests().sortIntegers(NUMBER_OF_INTEGERS, new ShellSort<>());
    }

    @Test
    public void applyShellSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new ShellSort<>());
    }
}
