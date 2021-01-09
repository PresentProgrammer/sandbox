import java.util.HashMap;
import java.util.Map;

/**
 * Problem #460
 * Time complexity: O(1)
 * Space complexity: O(N), where N = capacity
 **/
class LFUCache {

    private final Map<Integer, Node> map = new HashMap<>();
    private final Map<Integer, Node> mostRecent = new HashMap<>();
    private final int capacity;
    private final Node tail = new Node(-1, -1, -1, null, null);

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        return map.containsKey(key) ? increaseFreq(map.get(key)).val : -1;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            increaseFreq(map.get(key)).val = val;
        } else if (capacity > 0) {
            if (map.size() == capacity) {
                map.remove(delete(tail.next).key);
            }
            map.put(key, insert(new Node(key, val, 1, null, tail)));
        }
    }

    private Node increaseFreq(Node curr) {
        delete(curr);
        curr.freq++;
        insert(curr);
        return curr;
    }

    private Node delete(Node curr) {
        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }
        curr.prev.next = curr.next;
        if (mostRecent.get(curr.freq) == curr) {
            if (curr.prev.freq == curr.freq) {
                mostRecent.put(curr.freq, curr.prev);
            } else {
                mostRecent.remove(curr.freq);
            }
        }
        return curr;
    }

    private Node insert(Node curr) {
        final Node prev = getPrev(curr);
        curr.prev = prev;
        curr.next = prev.next;
        if (prev.next != null) {
            prev.next.prev = curr;
        }
        prev.next = curr;
        mostRecent.put(curr.freq, curr);
        return curr;
    }

    private Node getPrev(Node curr) {
        return mostRecent.containsKey(curr.freq) ? mostRecent.get(curr.freq)
                : mostRecent.containsKey(curr.freq - 1) ? mostRecent.get(curr.freq - 1)
                : curr.prev;
    }

    private static class Node {
        final int key;
        int val;
        int freq;
        Node next;
        Node prev;

        Node(int key, int val, int freq, Node next, Node prev) {
            this.key = key;
            this.val = val;
            this.freq = freq;
            this.next = next;
            this.prev = prev;
        }
    }
}