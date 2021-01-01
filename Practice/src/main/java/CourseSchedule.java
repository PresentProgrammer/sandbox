import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Problem #207
 * Time complexity: O(n + k)
 * Space complexity: O(n + k), where n — number of vertices, k — number of edges.
 **/
public class CourseSchedule {

    private static final float HASH_MAP_DEFAULT_LOAD_FACTOR = 0.75F;

    /**
     * Solution applies Kahn's algorithm to determine whether there is a cycle in the graph.
     * See: https://en.wikipedia.org/wiki/Topological_sorting#Algorithms
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        final Map<Integer, List<Integer>> adjacencyLists = new HashMap<>((int) (numCourses / HASH_MAP_DEFAULT_LOAD_FACTOR + 1));
        final int[] inDegrees = new int[numCourses];
        for (final int[] edge : prerequisites) {
            adjacencyLists.compute(edge[0], (k, v) -> nullableWithElement(v, edge[1]));
            inDegrees[edge[1]]++;
        }

        final Queue<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                s.offer(i);
            }
        }

        int visitedCount = 0;
        while (!s.isEmpty()) {
            final int currVertex = s.poll();
            visitedCount++;
            for (final int to : adjacencyLists.getOrDefault(currVertex, Collections.emptyList())) {
                if (--inDegrees[to] == 0) {
                    s.offer(to);
                }
            }
        }
        return visitedCount == numCourses;
    }

    private static List<Integer> nullableWithElement(List<Integer> nullableList, Integer elem) {
        final List<Integer> list = nullableList == null ? new ArrayList<>() : nullableList;
        list.add(elem);
        return list;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new CourseSchedule().canFinish(2, new int[][]{{1, 0}}));
        System.out.println("false == " + new CourseSchedule().canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println("true == " + new CourseSchedule().canFinish(3, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
    }
}