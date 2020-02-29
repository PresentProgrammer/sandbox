package present.programmer.algorithms.sandbox.collection.map.hash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashMapLinkedListTest {

    @Test
    public void testHashMap() {
        final HashMapLinkedList<Character, Integer> map = new HashMapLinkedList<>();

        assertNull(map.get('S'));

        map.put('S', 1);
        map.put('E', 2);
        map.put('X', 3);
        map.put('B', 4);
        map.put('R', 5);
        map.put('C', 6);
        map.put('H', 7);
        map.put('M', 8);

        assertEquals(1, map.get('S').intValue());
        assertEquals(2, map.get('E').intValue());
        assertEquals(3, map.get('X').intValue());
        assertEquals(4, map.get('B').intValue());
        assertEquals(5, map.get('R').intValue());
        assertEquals(6, map.get('C').intValue());
        assertEquals(7, map.get('H').intValue());
        assertEquals(8, map.get('M').intValue());
        assertNull(map.get('A'));

        map.put('C', 9);
        assertEquals(9, map.get('C').intValue());
    }
}