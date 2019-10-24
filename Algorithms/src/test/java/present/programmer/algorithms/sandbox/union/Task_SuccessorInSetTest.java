package present.programmer.algorithms.sandbox.union;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task_SuccessorInSetTest {

    @Test
    public void testAfterInit() {
        final int N = 8;
        final Task_SuccessorInSet set = new Task_SuccessorInSet(N);
        for (int i = 0; i < N; i++) {
            assertEquals(i, set.successor(i).intValue());
        }
        assertEquals(0, set.successor(-2).intValue());
        assertNull(set.successor(N));
    }

    @Test
    public void testFunctionality() {
        final int N = 8;
        final Task_SuccessorInSet set = new Task_SuccessorInSet(N);

        assertTrue(set.remove(2));
        assertEquals(3, set.successor(2).intValue());
        assertEquals(3, set.successor(3).intValue());

        assertTrue(set.remove(3));
        assertEquals(4, set.successor(2).intValue());
        assertEquals(4, set.successor(3).intValue());
        assertEquals(4, set.successor(4).intValue());

        assertTrue(set.remove(6));
        assertTrue(set.remove(5));
        assertEquals(4, set.successor(4).intValue());
        assertEquals(7, set.successor(5).intValue());
        assertEquals(7, set.successor(6).intValue());
        assertEquals(7, set.successor(7).intValue());

        assertTrue(set.remove(4));
        assertTrue(set.remove(0));
        assertTrue(set.remove(1));
        for (int i = -1; i < N; i++) {
            assertEquals(N - 1, set.successor(i).intValue());
        }

        assertTrue(set.remove(7));
        for (int i = -1; i <= N + 1; i++) {
            assertNull(set.successor(i));
        }
    }
}