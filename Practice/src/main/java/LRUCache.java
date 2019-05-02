import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Problem #146
 * Time complexity: See each method
 * Space complexity: O(n)
 **/
public class LRUCache {

    private final int capacity;
    private final Map<Integer, Integer> map = new HashMap<>();
    private final Deque<Integer> q = new ArrayDeque<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Time complexity: O(n)
     */
    public int get(int key) {
        final Integer value = map.get(key);
        if (value == null) {
            return -1;
        } else {
            raiseSignificance(key);
            return value;
        }
    }

    /**
     * Time complexity: O(n)
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            raiseSignificance(key);
        } else {
            if (q.size() == capacity) {
                map.remove(q.poll());
            }
            q.offer(key);
        }
        map.put(key, value);
    }

    private void raiseSignificance(int key) {
        final Iterator<Integer> iter = q.iterator();
        while (iter.next() != key) {
            continue;
        }
        iter.remove();
        q.offer(key);
    }
    
    public static void main(final String[] args) {
        LRUCache cache = new LRUCache( 2 /* capacity */ );
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("1 == " + cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println("-1 == " + cache.get(2));       // returns -1
        cache.put(4, 4);    // evicts key 1
        System.out.println("-1 == " + cache.get(1));       // returns -1
        System.out.println("3 == " + cache.get(3));       // returns 3
        System.out.println("4 == " + cache.get(4));       // returns 4
	}
}