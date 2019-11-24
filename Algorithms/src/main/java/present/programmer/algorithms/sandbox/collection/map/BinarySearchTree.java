package present.programmer.algorithms.sandbox.collection.map;

/**
 * Note: keys to the left are *strictly* less than the node's key;
 * keys to the right are *strictly* greater than the node's key.
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private Node<K, V> root;

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public V get(K key) {
        Node<K, V> curr = root;
        while (curr != null) {
            final int cmp = key.compareTo(curr.key);
            if (cmp < 0) {
                curr = curr.left;
            } else if (cmp > 0) {
                curr = curr.right;
            } else {
                return curr.value;
            }
        }
        return null;
    }

    /**
     * Return largest key <= the given key.
     */
    public K floor(K key) {
        K candidate = null;
        Node<K, V> curr = root;
        while (curr != null) {
            final int cmp = key.compareTo(curr.key);
            if (cmp < 0) {
                curr = curr.left;
            } else if (cmp > 0) {
                candidate = curr.key;
                curr = curr.right;
            } else {
                return key;
            }
        }
        return candidate;
    }

    public void delete(K key) {

    }

    public int size() {
        return root == null ? 0 : root.size;
    }

    private static <K extends Comparable<K>, V> Node<K, V> put(Node<K, V> curr, K key, V value) {
        if (curr == null) {
            return new Node<>(key, value);
        } else {
            final int cmp = key.compareTo(curr.key);
            if (cmp < 0) {
                final int prevSize = curr.left == null ? 0 : curr.left.size;
                curr.left = put(curr.left, key, value);
                if (curr.left.size > prevSize) {
                    curr.size++;
                }
            } else if (cmp > 0) {
                final int prevSize = curr.right == null ? 0 : curr.right.size;
                curr.right = put(curr.right, key, value);
                if (curr.right.size > prevSize) {
                    curr.size++;
                }
            } else {
                curr.value = value;
            }
            return curr;
        }
    }



    private static class Node<K extends Comparable<K>, V> {

        final K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        int size;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }
}
