import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Problem: 295
 * Space complexity: O(n)
 */
class MedianFinder {

    private final Queue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
    private final Queue<Integer> higher = new PriorityQueue<>();

    /**
     * Time complexity: O(log n), where n — previously added element count.
     */
    public void addNum(int num) {
        if (lower.peek() == null || lower.peek() < num) {
            addNum(num, higher, lower);
        } else {
            addNum(num, lower, higher);
        }
    }

    /**
     * Time complexity: O(1)
     */
    public double findMedian() {
        if (lower.size() == higher.size()) {
            return (lower.peek() + higher.peek()) / 2.0;
        } else if (lower.size() > higher.size()) {
            return lower.peek();
        } else {
            return higher.peek();
        }
    }

    private static void addNum(final int num, final Queue<Integer> whereToInsert, final Queue<Integer> other) {
        whereToInsert.offer(num);
        if (whereToInsert.size() - other.size() > 1) {
            other.offer(whereToInsert.poll());
        }
    }
}
