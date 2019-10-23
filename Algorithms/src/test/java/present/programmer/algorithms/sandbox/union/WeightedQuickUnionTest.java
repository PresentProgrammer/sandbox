package present.programmer.algorithms.sandbox.union;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.union.UnionFindTestCommons.N;
import static present.programmer.algorithms.sandbox.union.UnionFindTestCommons.testImplementation;

public class WeightedQuickUnionTest {

    @Test
    public void myFirstUnionFind() {
        testImplementation(new WeightedQuickUnion(N));
    }
}