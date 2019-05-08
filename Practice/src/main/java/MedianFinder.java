import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem 295
 * Space complexity: O(n)
 */
class MedianFinder {

    final List<Integer> list = new ArrayList<>();

    /**
     * Time complexity: O(log n) + O(n) → O(n)
     */
    public void addNum(int num) {
        list.add(Math.abs(Collections.binarySearch(list, num) + 1), num);
    }

    /**
     * Time complexity: O(1)
     */
    public double findMedian() {
        return list.size() % 2 == 0
                ? (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0
                : list.get(list.size() / 2);
    }
}
