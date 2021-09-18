import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem #759
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class EmployeeFreeTimeWithSortingAllIntervals {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        final List<Interval> intervals = schedule.stream()
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt((Interval interval) -> interval.start))
                .collect(Collectors.toUnmodifiableList());

        int maxEnd = -1;
        final List<Interval> result = new ArrayList<>();
        for (int i = 0; i < intervals.size() - 1; i++) {
            maxEnd = Math.max(maxEnd, intervals.get(i).end);
            final int nextStart = intervals.get(i + 1).start;
            if (maxEnd < nextStart) {
                result.add(new Interval(maxEnd, nextStart));
            }
        }
        return result;
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