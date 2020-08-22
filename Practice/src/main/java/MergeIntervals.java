import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

/**
 * Problem #56
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 **/
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
		final List<Entry<Integer, Integer>> result = new ArrayList<>();
        int i = 0, left = 0, right = 0;
        boolean processing = false;
        while (i < intervals.length) {
            if (processing) {
                final int currLeft = intervals[i][0];
                final int currRight = intervals[i][1];
                if (currLeft <= right) {
                    right = Math.max(right, currRight);
                    i++;
                } else {
                    processing = false;
                    result.add(new SimpleEntry<>(left, right));
                }
            } else {
                left = intervals[i][0];
                right = intervals[i][1];
                i++;
                processing = true;
            }
        }
        if (processing) {
            result.add(new SimpleEntry<>(left, right));
        }
		return result.stream()
                .map(pair -> new int[]{ pair.getKey(), pair.getValue() })
                .toArray(int[][]::new);
    }
    
    public static void main(final String[] args) {
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(new int[][]{ {2,6},{8,10},{1,3},{15,18} })));
	}
}