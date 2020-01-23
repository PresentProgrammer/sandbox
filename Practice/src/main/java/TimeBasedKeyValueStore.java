import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem #981
 * Time complexity:
 *   For set: O(key.length())
 *   For get: O(key.length() + log(duplicate keys number)
 * Space complexity: O(number of keys * key.length() + duplicate key number)
 **/
public class TimeBasedKeyValueStore {

    private final Trie trie = new Trie();

    public void set(String key, String value, int timestamp) {
        trie.set(key, value, timestamp);
    }

    public String get(String key, int timestamp) {
        return trie.get(key, timestamp);
    }

    private static class Trie {

        private Trie[] children;
        private List<Integer> timestamps;
        private List<String> values;

        void set(String key, String value, int timestamp) {
            set(key, value, timestamp, 0);
        }

        String get(String key, int timestamp) {
            return get(key, timestamp, 0);
        }

        private void set(String key, String value, int timestamp, int pos) {
            if (pos == key.length()) {
                if (timestamps == null) {
                    timestamps = new ArrayList<>();
                    values = new ArrayList<>();
                }
                timestamps.add(timestamp);
                values.add(value);
            } else {
                if (children == null) {
                    children = new Trie['z' - 'a' + 1];
                }
                final int childIndex = childIndex(key, pos);
                final Trie child;
                if (children[childIndex] == null) {
                    child = new Trie();
                    children[childIndex] = child;
                } else {
                    child = children[childIndex];
                }
                child.set(key, value, timestamp, pos + 1);
            }
        }

        private String get(String key, int timestamp, int pos) {
            if (pos == key.length()) {
                if (timestamps == null) {
                    return "";
                } else {
                    final int index = Collections.binarySearch(timestamps, timestamp);
                    if (index == -1) {
                        return "";
                    } else {
                        final int ind = index >= 0 ? index : -(index + 2);
                        return values.get(ind);
                    }
                }
            } else {
                final int childIndex = childIndex(key, pos);
                return children == null || children[childIndex] == null ? ""
                        : children[childIndex].get(key, timestamp, pos + 1);
            }
        }

        private static int childIndex(String key, int pos) {
            return key.charAt(pos) - 'a';
        }
    }

    public static void main(final String[] args) {
        final TimeBasedKeyValueStore store = new TimeBasedKeyValueStore();
        store.set("love", "high", 10);
        store.set("love", "low", 20);
        store.get("love", 10);
	}
}