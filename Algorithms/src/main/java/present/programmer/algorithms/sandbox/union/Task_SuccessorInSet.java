package present.programmer.algorithms.sandbox.union;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Solution to Coursera / Algorithms / Part 1 / Week 1 / 3rd interview question.
 */
public class Task_SuccessorInSet {

    private final int n;
    private final int[] parentOf;
    private final int[] sizeOfTree;
    private final int[] maxInTree;

    public Task_SuccessorInSet(final int n) {
        this.n = n;
        this.parentOf = IntStream.range(0, n + 1).toArray();
        this.maxInTree = Arrays.copyOf(this.parentOf, this.parentOf.length);
        this.sizeOfTree = IntStream.generate(() -> 1).limit(n + 1).toArray();
    }

    public boolean remove(final int x) {
        if (x < 0 || x >= n) {
            throw new IllegalArgumentException("Argument must be in range [0, n)");
        }
        return union(x, x + 1);
    }

    public Integer successor(final int x) {
        if (x < 0) {
            return successor(0);
        } else if (x >= n) {
            return null;
        } else {
            final int succ = maxInTree[rootOf(x)];
            return succ == n ? null : succ;
        }
    }

    private boolean union(final int p, final int q) {
        final int rootOfP = rootOf(p);
        final int rootOfQ = rootOf(q);
        if (rootOfP == rootOfQ) {
            return false;
        } else {
            final int rootOfBiggerTree = sizeOfTree[rootOfP] >= sizeOfTree[rootOfQ] ? rootOfP : rootOfQ;
            final int rootOfSmallerTree = rootOfBiggerTree == rootOfP ? rootOfQ : rootOfP;
            parentOf[rootOfSmallerTree] = rootOfBiggerTree;
            sizeOfTree[rootOfBiggerTree] += sizeOfTree[rootOfSmallerTree];
            maxInTree[rootOfBiggerTree] = Math.max(maxInTree[rootOfBiggerTree], maxInTree[rootOfSmallerTree]);
            return true;
        }
    }

    private int rootOf(final int p) {
        if (parentOf[p] == p) {
            return p;
        } else {
            final int rootOfP = rootOf(parentOf[p]);
            parentOf[p] = rootOfP;
            return rootOfP;
        }
    }
}
