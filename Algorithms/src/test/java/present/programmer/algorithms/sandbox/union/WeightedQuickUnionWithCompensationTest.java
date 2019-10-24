package present.programmer.algorithms.sandbox.union;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.union.UnionFindTestCommons.N;
import static present.programmer.algorithms.sandbox.union.UnionFindTestCommons.testImplementation;

public class WeightedQuickUnionWithCompensationTest {

    @Test
    public void myFirstUnionFind() {
        final WeightedQuickUnionWithCompensation unionFind = new WeightedQuickUnionWithCompensation(N);
        testImplementation(unionFind);
        System.out.println(unionFind);
    }
}