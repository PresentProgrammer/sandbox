import java.util.Map;
import java.util.TreeMap;

/**
 * Problem #715
 * Time complexity: O((A + R + Q) * log(A + R)), where A - add, R - remove, Q - query operation count.
 * Space complexity: O(A + R)
 **/
public class RangeModuleWithStartEndIntervals {

    final TreeMap<Integer, Integer> map = new TreeMap<>();

    public void addRange(int left, int right) {
        final Map.Entry<Integer, Integer> leftFloor = map.floorEntry(left);
        final int start = leftFloor != null && leftFloor.getValue() >= left ? leftFloor.getKey() : left;

        final Map.Entry<Integer, Integer> rightFloor = map.floorEntry(right);
        final int end = rightFloor != null && rightFloor.getValue() > right ? rightFloor.getValue() : right;

        map.subMap(start, true, end, true).clear();
        map.put(start, end);
    }

    public boolean queryRange(int left, int right) {
        final Map.Entry<Integer, Integer> leftFloor = map.floorEntry(left);
        return leftFloor != null && leftFloor.getValue() >= right;
    }

    public void removeRange(int left, int right) {
        final Map.Entry<Integer, Integer> rightLower = map.lowerEntry(right);
        if (rightLower != null && rightLower.getValue() > right) {
            map.put(right, rightLower.getValue());
        }

        final Map.Entry<Integer, Integer> leftLower = map.lowerEntry(left);
        if (leftLower != null && leftLower.getValue() > left) {
            map.put(leftLower.getKey(), left);
        }

        map.subMap(left, true, right, false).clear();
    }

    public static void main(final String[] args) {
        final RangeModuleWithStartEndIntervals rm = new RangeModuleWithStartEndIntervals();
        rm.addRange(10, 20);
        rm.removeRange(14, 16);
        System.out.println("true == " + rm.queryRange(10, 14));
        System.out.println("true == " + rm.queryRange(16, 17));
        System.out.println("false == " + rm.queryRange(13, 15));
    }
}