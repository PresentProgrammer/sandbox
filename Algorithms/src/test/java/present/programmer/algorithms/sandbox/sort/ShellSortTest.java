package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

public class ShellSortTest {

    @Test
    public void applyShellSort_sortWords() {
        commonTests().sortWords(new ShellSort());
    }

    @Test
    public void applyShellSort_sortIntegers() {
        commonTests().sortIntegers(new ShellSort());
    }

    @Test
    public void applyShellSort_sortEmptyArray_noExceptionsThrown() {
        commonTests().sortEmptyArray(new ShellSort());
    }

    // Auxiliary Methods

    private CommonTests commonTests() {
        return new CommonTests();
    }
}
