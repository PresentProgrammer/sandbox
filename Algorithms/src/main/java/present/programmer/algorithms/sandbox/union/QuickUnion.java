package present.programmer.algorithms.sandbox.union;

public class QuickUnion implements UnionFind {

    /**
     * E.g., i-th element's value j means that j is the parent of i.
     * null value means that i does not have parent.
     */
    private final Integer[] parentOf;

    public QuickUnion(final int numberOfNodes) {
        this.parentOf = new Integer[numberOfNodes];
    }

    @Override
    public boolean union(int p, int q) {
        final int rootOfP = rootOf(p);
        final int rootOfQ = rootOf(q);
        if (rootOfP == rootOfQ) {
            return false;
        } else {
            parentOf[rootOfP] = rootOfQ;
            return true;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return rootOf(p) == rootOf(q);
    }

    private int rootOf(final int p) {
        final Integer parent = parentOf[p];
        return parent == null ? p : rootOf(parent);
    }
}
