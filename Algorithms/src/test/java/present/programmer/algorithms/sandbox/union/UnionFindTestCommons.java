package present.programmer.algorithms.sandbox.union;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class UnionFindTestCommons {

    static final int N = 10;

    static void testImplementation(final UnionFind unionFind) {
        assertTrue(unionFind.union(4, 3));
        assertTrue(unionFind.union(3, 8));
        assertTrue(unionFind.union(6, 5));
        assertTrue(unionFind.union(9, 4));
        assertTrue(unionFind.union(2, 1));
        assertFalse(unionFind.union(8, 9));
        assertTrue(unionFind.union(5, 0));
        assertTrue(unionFind.union(7, 2));
        assertTrue(unionFind.union(6, 2));
        assertFalse(unionFind.union(1, 0));
        assertFalse(unionFind.union(6, 7));

        // In the result, there are 2 components: {0,1,2,6,5,7}, {3,4,8,9}.

        assertTrue(unionFind.connected(3, 8));
        assertTrue(unionFind.connected(3, 4));
        assertTrue(unionFind.connected(3, 9));
        assertTrue(unionFind.connected(8, 9));

        assertTrue(unionFind.connected(0, 2));
        assertTrue(unionFind.connected(5, 7));
        assertTrue(unionFind.connected(1, 6));

        assertFalse(unionFind.connected(0, 8));
        assertFalse(unionFind.connected(5, 9));
        assertFalse(unionFind.connected(6, 3));
        assertFalse(unionFind.connected(2, 3));
        assertFalse(unionFind.connected(8, 7));
    }
}
