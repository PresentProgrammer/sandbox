package present.programmer.algorithms.sandbox.union;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayBasedLinkedListTest {

    private static final int N = 10;

    @Test
    public void withoutRemoval_successorsAreReturnedCorrectly() {
        final ArrayBasedLinkedList testedObject = new ArrayBasedLinkedList(N);
        for (int i = 0; i < N - 2; i++) {
            assertEquals(i + 1, testedObject.successor(i));
        }
        assertEquals(N - 1, testedObject.successor(N - 1));
    }

    @Test
    public void removeMiddleElements_successorsAreReturnedCorrectly() {
        final ArrayBasedLinkedList testedObject = new ArrayBasedLinkedList(N);
        testedObject.remove(1);
        testedObject.remove(2);
        testedObject.remove(4);
        testedObject.remove(N - 1);
        assertEquals(3, testedObject.successor(0));
        assertEquals(5, testedObject.successor(3));
        assertEquals(N - 2, testedObject.successor(N - 2));
    }

    @Test
    public void removeFirstElement_successorsAreReturnedCorrectly() {
        final ArrayBasedLinkedList testedObject = new ArrayBasedLinkedList(N);
        testedObject.remove(0);
        assertEquals(2, testedObject.successor(1));
    }

    @Test
    public void removeLastElement_successorOfbeforeLastElement_willBeTheSameElement() {
        final ArrayBasedLinkedList testedObject = new ArrayBasedLinkedList(N);
        testedObject.remove(N - 1);
        assertEquals(N - 2, testedObject.successor(N - 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeMiddleElementMultipleTimes_exceptionIsThrown() {
        final ArrayBasedLinkedList testedObject = new ArrayBasedLinkedList(N);
        testedObject.remove(2);
        testedObject.remove(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFirstElementMultipleTimes_exceptionIsThrown() {
        final ArrayBasedLinkedList testedObject = new ArrayBasedLinkedList(N);
        testedObject.remove(0);
        testedObject.remove(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeLastElementMultipleTimes_exceptionIsThrown() {
        final ArrayBasedLinkedList testedObject = new ArrayBasedLinkedList(N);
        testedObject.remove(N - 1);
        testedObject.remove(N - 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void queryForSuccessorOfRemovedElement_exceptionIsThrown() {
        final ArrayBasedLinkedList testedObject = new ArrayBasedLinkedList(N);
        testedObject.remove(N - 2);
        testedObject.successor(N - 2);
    }
}