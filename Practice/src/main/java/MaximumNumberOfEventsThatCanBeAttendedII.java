import java.util.Arrays;
import java.util.Comparator;

/**
 * Problem #1751
 * Time complexity: O(N * log(N) * K)
 * Space complexity: O(N * K)
 **/
public class MaximumNumberOfEventsThatCanBeAttendedII {

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, Comparator.comparingInt(ev -> ev[0]));
        return maxValueMemoed(new Integer[k + 1][events.length + 1], events, 0, k);
    }

    private static int maxValueMemoed(Integer[][] valueMemo, int[][] events, int first, int kRem) {
        if (valueMemo[kRem][first] == null) {
            valueMemo[kRem][first] = maxValue(valueMemo, events, first, kRem);
        }
        return valueMemo[kRem][first];
    }

    private static int maxValue(Integer[][] valueMemo, int[][] events, int curr, int kRem) {
        if (kRem == 0 || curr == events.length) {
            return 0;
        } else {
            final int nextIfAttended = findNext(events, curr + 1, events[curr][1] + 1);
            final int valueIfAttended = events[curr][2] + maxValueMemoed(valueMemo, events, nextIfAttended, kRem - 1);
            final int valueIfSkipped = maxValueMemoed(valueMemo, events, curr + 1, kRem);
            return Math.max(valueIfAttended, valueIfSkipped);
        }
    }

    private static int findNext(int[][] events, int from, int key) {
        int lo = from;
        int hi = events.length - 1;
        while (lo <= hi) {
            final int mid = (lo + hi) >>> 1;
            if (events[mid][0] < key) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    public static void main(final String[] args) {
        System.out.println("7 == " + new MaximumNumberOfEventsThatCanBeAttendedII().maxValue(
                new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 1}}, 2
        ));
    }
}