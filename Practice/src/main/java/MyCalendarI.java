import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Problem #729
 * Time complexity: O(N log(N))
 * Space complexity: O(N)
 **/
public class MyCalendarI {

    private final TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public boolean book(int start, int end) {
        final Entry<Integer, Integer> leftEntry = treeMap.floorEntry(start);
        if (leftEntry == null || leftEntry.getValue() <= start) {
            final Entry<Integer, Integer> rightEntry = treeMap.lowerEntry(end);
            if (rightEntry == null || (leftEntry != null && rightEntry.getKey().equals(leftEntry.getKey()))) {

                final int newStart;
                if (leftEntry != null && leftEntry.getValue() == start) {
                    treeMap.remove(start);
                    newStart = leftEntry.getKey();
                } else {
                    newStart = start;
                }

                final int newEnd;
                final Entry<Integer, Integer> ceilingEntry = treeMap.ceilingEntry(end);
                if (ceilingEntry != null && ceilingEntry.getKey() == end) {
                    treeMap.remove(end);
                    newEnd = ceilingEntry.getValue();
                } else {
                    newEnd = end;
                }

                treeMap.put(newStart, newEnd);
                return true;
            }
        }
        return false;
    }

    public static void main(final String[] args) {
        final MyCalendarI myCalendar = new MyCalendarI();
        System.out.println("true == " + myCalendar.book(10, 20));
        System.out.println("false == " + myCalendar.book(15, 25));
        System.out.println("true == " + myCalendar.book(20, 30));
    }
}