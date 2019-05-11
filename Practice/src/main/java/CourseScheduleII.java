import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Problem #210
 * Time complexity: O(n + m), where n — number of nodes, m — sum number of edges.
 * Space complexity: O(n + m)
 **/
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
		final int[] inDegrees = new int[numCourses];
		final Map<Integer, List<Integer>> adjacencyLists = new HashMap<>();
		for (final int[] prerequisite : prerequisites) {
		    adjacencyLists.compute(prerequisite[1], (k, v) -> {
		        final List<Integer> list = v == null ? new ArrayList<>() : v;
		        list.add(prerequisite[0]);
		        return list;
            });
		    inDegrees[prerequisite[0]]++;
        }

		final List<Integer> topologicalSorting = new ArrayList<>();
		final Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < inDegrees.length; i++) {
		    if (inDegrees[i] == 0) {
		        q.offer(i);
            }
        }
		while (!q.isEmpty()) {
		    final int curr = q.poll();
		    topologicalSorting.add(curr);
		    for (final int adjacentVertex : adjacencyLists.getOrDefault(curr, Collections.emptyList())) {
		        if (--inDegrees[adjacentVertex] == 0) {
		            q.offer(adjacentVertex);
                }
            }
        }
		return topologicalSorting.size() < numCourses ? new int[]{} : topologicalSorting.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    public static void main(final String[] args) {
	}
}