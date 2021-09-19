import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Problem #716
 * Time complexity: O(1) for peekMax, and O(log N) for other methods.
 * Space complexity: O(N)
 **/
public class MaxStack {

    private final Node head = new Node(0, null, null);
    private final Node tail = new Node(0, null, null);
    private final TreeMap<Integer, List<Node>> treeMap = new TreeMap<>();

    public MaxStack() {
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void push(int x) {
        final Node newNode = tail.insertPrev(x);
        treeMap.computeIfAbsent(x, unused -> new ArrayList<>())
                .add(newNode);
    }

    public int pop() {
        final Node popped = tail.prev;
        removeFromTreeMap(popped.val);
        popped.remove();
        return popped.val;
    }

    public int top() {
        return tail.prev.val;
    }

    public int peekMax() {
        return treeMap.lastKey();
    }

    public int popMax() {
        final int max = peekMax();
        final Node removed = removeFromTreeMap(max);
        removed.remove();
        return max;
    }

    private Node removeFromTreeMap(int val) {
        final List<Node> nodes = treeMap.get(val);
        final Node removed = nodes.remove(nodes.size() - 1);
        if (nodes.isEmpty()) {
            treeMap.remove(val);
        }
        return removed;
    }

    private static class Node {

        final int val;
        Node prev;
        Node next;

        Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

        void remove() {
            next.prev = prev;
            prev.next = next;
            prev = null;
            next = null;
        }

        /**
         * Supposed to be called on tail
         * @return ref to new node
         */
        Node insertPrev(int val) {
            final Node newNode = new Node(val, prev, this);
            prev.next = newNode;
            prev = newNode;
            return newNode;
        }
    }
}