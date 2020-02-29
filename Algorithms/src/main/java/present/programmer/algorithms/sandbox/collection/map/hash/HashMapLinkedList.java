package present.programmer.algorithms.sandbox.collection.map.hash;

public class HashMapLinkedList<K, V> {

    private static final int M = 4;

    @SuppressWarnings("unchecked")
    private final Node<K, V>[] table = (Node<K, V>[]) new Node[M];

    public void put(K key, V value) {
        final int hash = key.hashCode();
        final int i = hashFn(hash);
        for (Node<K, V> curr = table[i]; curr != null; curr = curr.next) {
            if (keysEqual(curr, key, hash)) {
                curr.value = value;
                return;
            }
        }
        table[i] = new Node<>(key, value, hash, table[i]);
    }

    public V get(K key) {
        final int hash = key.hashCode();
        for (Node<K, V> curr = table[hashFn(hash)]; curr != null; curr = curr.next) {
            if (keysEqual(curr, key, hash)) {
                return curr.value;
            }
        }
        return null;
    }

    private int hashFn(int hashCode) {
        return (hashCode & Integer.MAX_VALUE) % M;
    }

    private boolean keysEqual(final Node<K, V> curr, final K key, final int hash) {
        return curr.hash == hash && curr.key.equals(key);
    }

    private static class Node<K, V> {

        private final K key;
        private V value;
        private final int hash;
        private final Node<K, V> next;

        private Node(K key, V value, int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }
}
