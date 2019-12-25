package present.programmer.algorithms.sandbox.collection.map;

/**
 * Note: search and order-operations (`floor`, `rank`, etc.) are the same as for simple BST.
 * Therefore, extending BST.
 */
public class RedBlackBst<K extends Comparable<K>, V> extends BinarySearchTree<K, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    @Override
    public void put(K key, V val) {
        final RedBlackNode tempRoot = put((RedBlackNode) root, key, val);
        tempRoot.color = BLACK;
        root = tempRoot;
    }

    private RedBlackNode put(RedBlackNode h, K key, V val) {
        if (h == null) {
            return new RedBlackNode(key, val);
        }
        final int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left(), key, val);
        } else if (cmp > 0) {
            h.right = put(h.right(), key, val);
        } else {
            h.value = val;
        }
        h = maintainRebBlackStructure(h);
        h.size = calculateSize(h);
        return h;
    }

    private RedBlackNode maintainRebBlackStructure(RedBlackNode h) {
        if (isRed(h.right()) && !isRed(h.left())) {
            h = rotateLeft(h);
        }
        if (isRed(h.left()) && isRed(h.left().left())) {
            h = rotateRight(h);
        }
        if (isRed(h.left()) && isRed(h.right())) {
            flipColors(h);
        }
        return h;
    }

    // Elementary operations:

    /**
     * Properties of operation:
     * - Maintains symmetric order (in-order traversal yields elements in asc order).
     * - Maintains perfect black balance.
     */
    private RedBlackNode rotateLeft(RedBlackNode h) {
        final RedBlackNode x = h.right();
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;

        x.size = h.size;
        h.size = calculateSize(h);

        return x;
    }

    /**
     * Same properties as for {@link RedBlackBst#rotateLeft}.
     */
    private RedBlackNode rotateRight(RedBlackNode h) {
        final RedBlackNode x = h.left();
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;

        x.size = h.size;
        h.size = calculateSize(h);

        return x;
    }

    /**
     * Splitting a "temporary 4-node" (corresponding to a 4-node in 2-3 trees).
     */
    private void flipColors(RedBlackNode h) {
        assert !isRed(h);
        assert isRed(h.left()) && isRed(h.right());
        h.color = RED;
        h.left().color = BLACK;
        h.right().color = BLACK;
    }

    private boolean isRed(RedBlackNode node) {
        return node != null && node.color == RED;
    }

    private class RedBlackNode extends BinarySearchTree<K, V>.Node {

        /**
         * When new node is inserted, its parent link is always RED.
         */
        private boolean color = RED;

        RedBlackNode(K key, V value) {
            super(key, value);
        }

        private RedBlackNode left() {
            return (RedBlackNode) left;
        }

        private RedBlackNode right() {
            return (RedBlackNode) right;
        }
    }
}
