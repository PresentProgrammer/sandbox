package present.programmer.algorithms.sandbox.collection.map;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RedBlackBstTest {

    @Test
    public void testBST() {
        final RedBlackBst<Character, Integer> bst = new RedBlackBst<>();
        assertEquals(0, bst.size());

        bst.put('S', 1);
        bst.put('E', 2);
        bst.put('X', 3);
        bst.put('B', 4);
        bst.put('R', 5);
        bst.put('C', 6);
        bst.put('H', 7);
        bst.put('M', 8);
        assertEquals(8, bst.size());

        assertEquals(1, bst.get('S').intValue());
        assertEquals(2, bst.get('E').intValue());
        assertEquals(3, bst.get('X').intValue());
        assertEquals(4, bst.get('B').intValue());
        assertEquals(5, bst.get('R').intValue());
        assertEquals(6, bst.get('C').intValue());
        assertEquals(7, bst.get('H').intValue());
        assertEquals(8, bst.get('M').intValue());

        bst.put('C', 9);
        assertEquals(9, bst.get('C').intValue());
        assertEquals(8, bst.size());

        assertEquals('R', bst.floor('R').charValue());
        assertEquals('S', bst.floor('S').charValue());
        assertEquals('S', bst.floor('T').charValue());
        assertNull(bst.floor('A'));

        assertEquals(8, bst.rank('Z'));
        assertEquals(7, bst.rank('X'));
        assertEquals(6, bst.rank('S'));
        assertEquals(5, bst.rank('R'));
        assertEquals(4, bst.rank('M'));
        assertEquals(3, bst.rank('H'));
        assertEquals(2, bst.rank('E'));
        assertEquals(1, bst.rank('C'));
        assertEquals(0, bst.rank('B'));
        assertEquals(0, bst.rank('A'));

        assertEquals(asList('B', 'C', 'E', 'H', 'M', 'R', 'S', 'X'), bst.keys());

        assertEquals(asList(), bst.range('J', 'L'));
        assertEquals(asList(), bst.range('A', 'A'));
        assertEquals(asList(), bst.range('B', 'B'));
        assertEquals(asList('B'), bst.range('A', 'C'));
        assertEquals(asList('B'), bst.range('B', 'C'));
        assertEquals(asList('C', 'E', 'H', 'M'), bst.range('C', 'N'));
        assertEquals(asList('B', 'C', 'E', 'H', 'M', 'R', 'S', 'X'), bst.range('A', 'Z'));

        assertEquals(0, bst.rangeSize('J', 'L'));
        assertEquals(0, bst.rangeSize('A', 'A'));
        assertEquals(0, bst.rangeSize('B', 'B'));
        assertEquals(1, bst.rangeSize('A', 'C'));
        assertEquals(1, bst.rangeSize('B', 'C'));
        assertEquals(4, bst.rangeSize('C', 'N'));
        assertEquals(8, bst.rangeSize('A', 'Z'));
    }
}