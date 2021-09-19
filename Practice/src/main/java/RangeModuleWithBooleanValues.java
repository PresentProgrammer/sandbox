import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Problem #715
 * Time complexity: O((A + R + Q) * log(A + R)), where A - add, R - remove, Q - query operation count.
 * Space complexity: O(A + R)
 **/
public class RangeModuleWithBooleanValues {

    private static final boolean START = true;
    private static final boolean END = false;

    private final TreeMap<Integer, Boolean> treeMap = new TreeMap<>();

    public void addRange(int left, int right) {
        treeMap.subMap(left, right).clear();

        final Entry<Integer, Boolean> lowerEntry = treeMap.lowerEntry(left);
        if (lowerEntry == null || lowerEntry.getValue() == END) {
            treeMap.put(left, START);
        }

        final Boolean rightVal = treeMap.get(right);
        if (rightVal == null) {
            final Entry<Integer, Boolean> higherEntry = treeMap.higherEntry(right);
            if (higherEntry == null || higherEntry.getValue() == START) {
                treeMap.put(right, END);
            }
        } else if (rightVal == START) {
            treeMap.remove(right);
        }
    }

    public boolean queryRange(int left, int right) {
        final Entry<Integer, Boolean> floorEntry = treeMap.floorEntry(left);
        return floorEntry != null && floorEntry.getValue() == START &&
                floorEntry.getKey().equals(treeMap.lowerKey(right));
    }

    public void removeRange(int left, int right) {
        treeMap.subMap(left, right).clear();

        final Entry<Integer, Boolean> lowerEntry = treeMap.lowerEntry(left);
        if (lowerEntry != null && lowerEntry.getValue() == START) {
            treeMap.put(left, END);
        }

        final Boolean rightVal = treeMap.get(right);
        if (rightVal == null) {
            final Entry<Integer, Boolean> higherEntry = treeMap.higherEntry(right);
            if (higherEntry != null && higherEntry.getValue() == END) {
                treeMap.put(right, START);
            }
        } else if (rightVal == END) {
            treeMap.remove(right);
        }
    }

    public static void main(final String[] args) {
        final RangeModuleWithBooleanValues rm = new RangeModuleWithBooleanValues();
        rm.addRange(10, 20);
        rm.removeRange(14, 16);
        System.out.println("true == " + rm.queryRange(10, 14));
        System.out.println("true == " + rm.queryRange(16, 17));
        System.out.println("false == " + rm.queryRange(13, 15));
    }
}