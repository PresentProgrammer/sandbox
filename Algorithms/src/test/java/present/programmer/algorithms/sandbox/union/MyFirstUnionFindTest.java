package present.programmer.algorithms.sandbox.union;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyFirstUnionFindTest {

    private static final int N = 10;

    @Test
    public void myFirstUnionFind() {
        final UnionFind unionFind = new MyFirstUnionFind(N);
        assertTrue(unionFind.union(4, 3));
        assertTrue(unionFind.union(3, 8));
        assertTrue(unionFind.union(6, 5));
        assertTrue(unionFind.union(9, 4));
        assertTrue(unionFind.union(2, 1));
        assertFalse(unionFind.union(8, 9));
        assertTrue(unionFind.union(5, 0));
        assertTrue(unionFind.union(7, 2));
        assertTrue(unionFind.union(6, 1));
        assertFalse(unionFind.union(1, 0));
        assertFalse(unionFind.union(6, 7));
    }
}