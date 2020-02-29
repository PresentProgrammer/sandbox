package present.programmer.algorithms.sandbox.collection.map.hash;

public class HashMapLinearProbing<K, V> {

    // TODO: implement resize and delete(K key)
    private static final int M = 16;

    private final K[] keys;
    private final V[] values;

    @SuppressWarnings("unchecked")
    public HashMapLinearProbing() {
        this.keys = (K[]) new Object[M];
        this.values = (V[]) new Object[M];
    }

    public void put(K key, V value) {
        int i = hashFn(key.hashCode());
        while (keys[i] != null && !keys[i].equals(key)) {
            i = (i + 1) % M;
        }
        keys[i] = key;
        values[i] = value;
    }

    public V get(K key) {
        int i = hashFn(key.hashCode());
        while (keys[i] != null && !keys[i].equals(key)) {
            i = (i + 1) % M;
        }
        return values[i];
    }

    private static int hashFn(int hashCode) {
        return (hashCode & Integer.MAX_VALUE) % M;
    }
}
