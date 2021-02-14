import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Problem #1376
 * Time complexity: O(N)
 * Space complexity: O(N)
 **/
public class TimeNeededToInformAllEmployees {

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        final Map<Integer, List<Integer>> subordinates = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (manager[i] >= 0) {
                subordinates.computeIfAbsent(manager[i], unusued -> new ArrayList<>())
                        .add(i);
            }
        }

        final Deque<Entry<Integer, Integer>> stack = new ArrayDeque<>();
        stack.push(new SimpleEntry<>(headID, 0));
        int maxTime = 0;
        while (!stack.isEmpty()) {
            final Entry<Integer, Integer> top = stack.pop();
            final Integer currId = top.getKey();
            final Integer currTime = top.getValue();

            if (subordinates.containsKey(currId)) {
                final int nextTime = currTime + informTime[currId];
                for (final Integer sub : subordinates.get(currId)) {
                    stack.push(new SimpleEntry<>(sub, nextTime));
                }
            } else {
                maxTime = Math.max(maxTime, currTime);
            }
        }
        return maxTime;
    }

    public static void main(final String[] args) {
        System.out.println("21 == " + new TimeNeededToInformAllEmployees().numOfMinutes(
                7, 6, new int[]{1, 2, 3, 4, 5, 6, -1}, new int[]{0, 6, 5, 4, 3, 2, 1}
        ));
    }
}