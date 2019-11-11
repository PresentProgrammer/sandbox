package present.programmer.algorithms.sandbox.collection.queue;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriorityQueueTest {

    @Test
    public void testWithLetters() {
        final PriorityQueue<Character> q = new PriorityQueue<>();
        q.enqueue('T');
        q.enqueue('P');
        q.enqueue('R');
        q.enqueue('N');
        q.enqueue('H');
        q.enqueue('O');
        q.enqueue('A');
        q.enqueue('E');
        q.enqueue('I');
        q.enqueue('G');
        q.enqueue('S');
        assertEquals('T', q.dequeue().charValue());
        assertEquals('S', q.dequeue().charValue());
        q.enqueue('S');
        assertEquals('S', q.dequeue().charValue());
        assertEquals('R', q.dequeue().charValue());
        assertEquals('P', q.dequeue().charValue());
        assertEquals('O', q.dequeue().charValue());
        assertEquals('N', q.dequeue().charValue());
        assertEquals('I', q.dequeue().charValue());
        assertEquals('H', q.dequeue().charValue());
        assertEquals('G', q.dequeue().charValue());
        assertEquals('E', q.dequeue().charValue());
        assertEquals('A', q.dequeue().charValue());
    }
}