package present.programmer.algorithms.sandbox.union;

import java.util.Arrays;
import java.util.stream.IntStream;

public class WeightedQuickUnionWithCompensation implements UnionFind {

    /**
     * E.g., i-th element's value j means that j is the parent of i.
     * null value means that i does not have parent.
     */
    private final Integer[] parentOf;
    private final int[] sizeOfTree;

    public WeightedQuickUnionWithCompensation(final int numberOfNodes) {
        this.parentOf = new Integer[numberOfNodes];
        this.sizeOfTree = IntStream.generate(() -> 1).limit(numberOfNodes).toArray();
    }

    @Override
    public boolean union(int p, int q) {
        final int rootOfP = rootOf(p);
        final int rootOfQ = rootOf(q);
        if (rootOfP == rootOfQ) {
            return false;
        } else {
            if (sizeOfTree[rootOfP] <= sizeOfTree[rootOfQ]) {
                parentOf[rootOfP] = rootOfQ;
                sizeOfTree[rootOfQ] += sizeOfTree[rootOfP];
            } else {
                parentOf[rootOfQ] = rootOfP;
                sizeOfTree[rootOfP] += sizeOfTree[rootOfQ];
            }
            return true;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return rootOf(p) == rootOf(q);
    }

    /**
     * While looking for the root, path is compensated.
     */
    private int rootOf(final int p) {
        final Integer parent = parentOf[p];
        if (parent == null) {
            return p;
        } else {
            final Integer root = rootOf(parent);
            parentOf[p] = root;
            return root;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(parentOf);
    }
}
