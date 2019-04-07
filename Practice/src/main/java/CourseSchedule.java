import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Problem #207
 * Time complexity: O(k), where k — number of vertices.
 * Space complexity: O(n + k), where n — number of vertices, k — number of edges.
 **/
public class CourseSchedule {

    private static final float HASH_MAP_DEFAULT_LOAD_FACTOR = 0.75F;

    private Map<Integer, List<Integer>> adjacencyLists;
    private Set<Integer> visitedVertices;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adjacencyLists = new HashMap<>((int) (numCourses / HASH_MAP_DEFAULT_LOAD_FACTOR + 1));
        for (final int[] edge : prerequisites) {
            adjacencyLists.compute(edge[0], (k, v) -> nullableWithElement(v, edge[1]));
        }

        visitedVertices = new HashSet<>();

        for (final Integer start : adjacencyLists.keySet()) {
            if (!visitedVertices.contains(start)) {
                final Set<Integer> path = new HashSet<>();
                if (findCycle(start, path)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean findCycle(Integer currVertex, Set<Integer> path) {
        if (path.contains(currVertex)) {
            return true;
        } else if (!visitedVertices.contains(currVertex)) {
            visitedVertices.add(currVertex);
            path.add(currVertex);
            for (final Integer next : adjacencyLists.getOrDefault(currVertex, Collections.emptyList())) {
                if (findCycle(next, path)) {
                    return true;
                }
            }
            path.remove(currVertex);
        }
        return false;
    }

    private static List<Integer> nullableWithElement(List<Integer> nullableList, Integer elem) {
        final List<Integer> list = nullableList == null ? new ArrayList<>() : nullableList;
        list.add(elem);
        return list;
    }
    
    public static void main(final String[] args) {
        System.out.println("true == " + new CourseSchedule().canFinish(2, new int[][] {{ 1, 0 }} ));
        System.out.println("false == " + new CourseSchedule().canFinish(2, new int[][] {{ 1, 0 }, { 0, 1 }} ));
        System.out.println("true == " + new CourseSchedule().canFinish(3, new int[][] {{ 0, 1 }, { 0, 2 }, { 1, 2 }} ));
	}
}