package present.programmer.algorithms.sandbox.union;

import org.junit.Test;

import static present.programmer.algorithms.sandbox.union.UnionFindTestCommons.N;
import static present.programmer.algorithms.sandbox.union.UnionFindTestCommons.testImplementation;

public class MyFirstUnionFindTest {

    @Test
    public void myFirstUnionFind() {
        testImplementation(new MyFirstUnionFind(N));
    }
}