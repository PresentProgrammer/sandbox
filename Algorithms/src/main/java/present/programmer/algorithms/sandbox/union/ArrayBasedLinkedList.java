package present.programmer.algorithms.sandbox.union;

public class ArrayBasedLinkedList {

    private boolean[] present;
    private int[] successors;
    private int[] predecessors;

    public ArrayBasedLinkedList(final int n) {
        initializePresent(n);
        initializeSuccessors(n);
        initializePredecessors(n);
    }

    /**
     * Takes constant time.
     */
    public void remove(final int x) {
        elementMustBePresent(x);
        if (isFirst(x)) {
            makeSuccessorFirst(x);
        } else if (isLast(x)) {
            makePredecessorFirst(x);
        } else {
            removeMiddleElement(x);
        }
        present[x] = false;
    }

    /**
     * Takes constant time.
     */
    public int successor(final int x) {
        elementMustBePresent(x);
        return successors[x];
    }

    //
    // Auxiliary Methods
    //

    private void initializePresent(final int n) {
        present = new boolean[n];
        for (int i = 0; i < n; i++) {
            present[i] = true;
        }
    }

    private void initializePredecessors(final int n) {
        predecessors = new int[n];
        for (int i = n - 1; i > 0; i--) {
            predecessors[i] = i - 1;
        }
        predecessors[0] = 0;
    }

    private void initializeSuccessors(final int n) {
        successors = new int[n];
        for (int i = 0; i < (n - 1); i++) {
            successors[i] = i + 1;
        }
        successors[n - 1] = n - 1;
    }

    private void elementMustBePresent(final int x) {
        if (!present[x]) {
            throw new IllegalArgumentException("element " + x + " has been removed earlier");
        }
    }

    private boolean isFirst(final int x) {
        return predecessors[x] == x;
    }

    private void makeSuccessorFirst(final int x) {
        final int xSuccessor = successors[x];
        predecessors[xSuccessor] = xSuccessor;
    }

    private boolean isLast(final int x) {
        return successors[x] == x;
    }

    private void makePredecessorFirst(final int x) {
        final int xPredecessor = predecessors[x];
        successors[xPredecessor] = xPredecessor;
    }

    private void removeMiddleElement(final int x) {
        final int xSuccessor = successors[x];
        final int xPredecessor = predecessors[x];
        predecessors[xSuccessor] = xPredecessor;
        successors[xPredecessor] = xSuccessor;
    }
}
