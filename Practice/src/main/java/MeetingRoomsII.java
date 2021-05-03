import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

/**
 * Problem #253
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        final List<Entry<Integer, Boolean>> list = new ArrayList<>();
        for (final int[] interval : intervals) {
            list.add(new SimpleEntry<>(interval[0], true));
            list.add(new SimpleEntry<>(interval[1], false));
        }
        list.sort(Comparator.<Entry<Integer, Boolean>>comparingInt(Entry::getKey)
                .thenComparing(Entry::getValue));

        int curr = 0;
        int max = 0;
        for (final Entry<Integer, Boolean> entry : list) {
            curr += entry.getValue() ? 1 : -1;
            max = Math.max(max, curr);
        }
        return max;
    }

    public static void main(final String[] args) {
        System.out.println("2 == " + new MeetingRoomsII().minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println("1 == " + new MeetingRoomsII().minMeetingRooms(new int[][]{{7, 10}, {2, 4}}));
    }
}