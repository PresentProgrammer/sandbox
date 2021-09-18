import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Problem #759
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class EmployeeFreeTimeWithMaintainingIntervals {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        final TreeMap<Integer, Integer> map = new TreeMap<>();
        for (final List<Interval> employee : schedule) {
            for (final Interval interval : employee) {
                insert(map, interval);
            }
        }

        final List<Interval> result = new ArrayList<>();
        final Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        int start = iter.next().getValue();
        while (iter.hasNext()) {
            final Map.Entry<Integer, Integer> next = iter.next();
            result.add(new Interval(start, next.getKey()));
            start = next.getValue();
        }
        return result;
    }

    private static void insert(TreeMap<Integer, Integer> map, Interval interval) {
        final Interval merged = new Interval(interval.start, interval.end);

        final Map.Entry<Integer, Integer> left = map.floorEntry(merged.start);
        if (left != null && left.getValue() >= merged.start) {
            merged.start = left.getKey();
        }

        final Map.Entry<Integer, Integer> right = map.floorEntry(merged.end);
        if (right != null && right.getValue() >= merged.end) {
            merged.end = right.getValue();
        }

        map.subMap(merged.start, true, merged.end, true).clear();
        map.put(merged.start, merged.end);
    }

    private static class Interval {
        int start;
        int end;

        public Interval() {}

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}