package present.programmer.algorithms.sandbox.collection.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Note: keys to the left are *strictly* less than the node's key;
 * keys to the right are *strictly* greater than the node's key.
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private Node root;

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public V get(K key) {
        Node curr = root;
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
        Node curr = root;
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
        return size(root);
    }

    /**
     * How many keys < given key.
     */
    public int rank(K key) {
        return rank(key, root);
    }

    public Iterable<K> keys() {
        final List<K> keys = new ArrayList<>();
        gatherKeys(root, keys);
        return keys;
    }

    private Node put(Node curr, K key, V value) {
        if (curr == null) {
            return new Node(key, value);
        } else {
            final int cmp = key.compareTo(curr.key);
            if (cmp < 0) {
                curr.left = put(curr.left, key, value);
            } else if (cmp > 0) {
                curr.right = put(curr.right, key, value);
            } else {
                curr.value = value;
            }
            curr.size = 1 + size(curr.left) + size(curr.right);
            return curr;
        }
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    private int rank(K key, Node curr) {
        if (curr == null) {
            return 0;
        } else {
            final int cmp = key.compareTo(curr.key);
            if (cmp < 0) {
                return rank(key, curr.left);
            } else if (cmp > 0) {
                return 1 + size(curr.left) + rank(key, curr.right);
            } else {
                return size(curr.left);
            }
        }
    }

    private void gatherKeys(Node curr, List<K> keys) {
        if (curr != null) {
            gatherKeys(curr.left, keys);
            keys.add(curr.key);
            gatherKeys(curr.right, keys);
        }
    }

    private class Node {

        final K key;
        V value;
        Node left;
        Node right;
        int size = 1;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
