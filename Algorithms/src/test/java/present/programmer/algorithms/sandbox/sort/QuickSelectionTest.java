package present.programmer.algorithms.sandbox.sort;

import org.junit.Test;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.sort;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class QuickSelectionTest {

    private static final int NUMBER_OF_INTEGERS = 100_000;

    @Test
    public void selectFirstTenWords() {
        assertFirstElementsSelectionWorksCorrectly(unsortedWords(), 10);
    }

    @Test
    public void selectFirstWordAsArray() {
        assertFirstElementsSelectionWorksCorrectly(unsortedWords(), 1);
    }

    @Test
    public void selectNoWords() {
        assertFirstElementsSelectionWorksCorrectly(unsortedWords(), 0);
    }

    @Test
    public void selectFirstTenNumbers() {
        assertFirstElementsSelectionWorksCorrectly(unsortedNumbers(), 10);
    }

    @Test
    public void selectFirstNumberAsArray() {
        assertFirstElementsSelectionWorksCorrectly(unsortedNumbers(), 1);
    }

    @Test
    public void selectNoNumbers() {
        assertFirstElementsSelectionWorksCorrectly(unsortedNumbers(), 0);
    }

    @Test
    public void selectTenthWord_countingFrom0() {
        assertOneElementSelectionWorksCorrectly(unsortedWords(), 9);
    }

    @Test
    public void selectTenthNumber_countingFrom0() {
        assertOneElementSelectionWorksCorrectly(unsortedNumbers(), 9);
    }

    @Test
    public void selectThirdWord_countingFrom0() {
        assertOneElementSelectionWorksCorrectly(unsortedWords(), 2);
    }

    @Test
    public void selectThirdNumber_countingFrom0() {
        assertOneElementSelectionWorksCorrectly(unsortedNumbers(), 2);
    }

    @Test
    public void selectFirstWord_countingFrom0() {
        assertOneElementSelectionWorksCorrectly(unsortedWords(), 0);
    }

    @Test
    public void selectFirstNumber_countingFrom0() {
        assertOneElementSelectionWorksCorrectly(unsortedNumbers(), 0);
    }

    // Auxiliary Methods

    private static <T extends Comparable<T>> void assertFirstElementsSelectionWorksCorrectly(final T[] unsortedInput, final int k) {
        assertArrayEquals(quickFirstElementsSelection(unsortedInput, k), sortAndGetFirstElements(unsortedInput, k));
    }

    private static <T extends Comparable<T>> T[] quickFirstElementsSelection(final T[] unsortedInput, final int k) {
        final T[] firstKElements = new QuickSelection().selectFirstKElements(unsortedInput, k);
        sort(firstKElements);
        return firstKElements;
    }

    private static <T extends Comparable<T>> T[] sortAndGetFirstElements(final T[] unsortedInput, final int k) {
        final T[] copyOfUnsortedInput = copyOf(unsortedInput, unsortedInput.length);
        sort(copyOfUnsortedInput);
        return copyOf(copyOfUnsortedInput, k);
    }

    private static <T extends Comparable<T>> void assertOneElementSelectionWorksCorrectly(final T[] unsortedInput, final int k) {
        assertEquals(quickElementSelection(unsortedInput, k), selectElementWithSorting(unsortedInput, k));
    }

    private static <T extends Comparable<T>> T quickElementSelection(final T[] unsortedInput, final int k) {
        return new QuickSelection().selectKElement(unsortedInput, k);
    }

    private static <T extends Comparable<T>> T selectElementWithSorting(final T[] unsortedInput, final int k) {
        final T[] copyOfUnsortedInput = copyOf(unsortedInput, unsortedInput.length);
        sort(copyOfUnsortedInput);
        return copyOfUnsortedInput[k];
    }

    private static String[] unsortedWords() {
        return new Inputs().getUnsortedWords();
    }

    private static Integer[] unsortedNumbers() {
        return new Inputs().getUnsortedIntegers(NUMBER_OF_INTEGERS);
    }
}