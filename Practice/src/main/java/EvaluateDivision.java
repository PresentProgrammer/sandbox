import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Problem #399
 * Time complexity: O(N * M), where N — number of vertices, M — number of queries
 * Space complexity: O(N ^ 2)
 **/
public class EvaluateDivision {

    private static final double NO_PATH = -1.0;

    private final Set<String> vertices = new HashSet<>();
    private final Map<String, Map<String, Double>> incidence = new HashMap<>();

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        for (int i = 0; i < equations.size(); i++) {
            final List<String> eq = equations.get(i);
            addEdges(eq.get(0), eq.get(1), values[i]);
            vertices.add(eq.get(0));
            vertices.add(eq.get(1));
        }

        return queries.stream()
                .mapToDouble(q -> vertices.contains(q.get(0)) && vertices.contains(q.get(1))
                        ? search(q.get(0), q.get(1), 1.0, setOf(q.get(0)))
                        : NO_PATH)
                .toArray();
    }

    private double search(String source, String target, double prevWeight, Set<String> visited) {
        final Map<String, Double> sourceInc = incidence.get(source);
        if (source.equals(target)) {
            return prevWeight;
        } else if (sourceInc.containsKey(target)) {
            return prevWeight * search(target, target, sourceInc.get(target), visited);
        } else {
            for (final Map.Entry<String, Double> inc : sourceInc.entrySet()) {
                if (!visited.contains(inc.getKey())) {
                    visited.add(inc.getKey());
                    final double val = search(inc.getKey(), target, inc.getValue(), visited);
                    if (val != NO_PATH) {
                        addEdges(source, target, val);
                        return prevWeight * val;
                    }
                }
            }
            return NO_PATH;
        }
    }

    private void addEdges(String source, String target, double weight) {
        incidence.computeIfAbsent(source, unused -> new HashMap<>())
                .putIfAbsent(target, weight);
        incidence.computeIfAbsent(target, unused -> new HashMap<>())
                .putIfAbsent(source, weight == NO_PATH ? NO_PATH : 1.0 / weight);
    }

    private Set<String> setOf(String source) {
        final Set<String> set = new HashSet<>();
        set.add(source);
        return set;
    }
}