import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Problem #399
 * Time complexity: O(N + M * log*N), where N — number of vertices, M — number of queries
 * Space complexity: O(N)
 **/
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        final UnionFind uf = new UnionFind(equations, values);
        return queries.stream()
                .mapToDouble(uf::query)
                .toArray();
    }

    private static class UnionFind {

        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Double> coef = new HashMap<>();
        private final Map<String, Integer> size = new HashMap<>();

        UnionFind(List<List<String>> equations, double[] values) {
            equations.stream()
                    .flatMap(List::stream)
                    .distinct()
                    .forEach(vertex -> {
                        parent.put(vertex, vertex);
                        coef.put(vertex, 1.0);
                        size.put(vertex, 1);
                    });
            for (int i = 0; i < equations.size(); i++) {
                final List<String> eq = equations.get(i);
                union(eq.get(0), eq.get(1), values[i]);
            }
        }

        double query(List<String> q) {
            final String q1 = q.get(0);
            final String q2 = q.get(1);
            if (parent.containsKey(q1) && parent.containsKey(q2) && rootOf(q1).equals(rootOf(q2))) {
                return coef.get(q1) / coef.get(q2);
            } else {
                return -1.0;
            }
        }

        private void union(String s1, String s2, double val) {
            final String r1 = rootOf(s1);
            final String r2 = rootOf(s2);
            if (!r1.equals(r2)) {
                if (size.get(r1) <= size.get(r2)) {
                    size.put(r2, size.get(r1) + size.get(r2));
                    coef.put(r1, val * coef.get(s2) / coef.get(s1));
                    parent.put(r1, r2);
                } else {
                    size.put(r1, size.get(r1) + size.get(r2));
                    coef.put(r2, 1.0 / val * coef.get(s1) / coef.get(s2));
                    parent.put(r2, r1);
                }
            }
        }

        private String rootOf(String curr) {
            if (parent.get(curr).equals(curr)) {
                return curr;
            } else {
                final String root = rootOf(parent.get(curr));
                coef.put(curr, coef.get(curr) * coef.get(parent.get(curr)));
                parent.put(curr, root);
                return root;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Expected: [6.0]");
        System.out.println(Arrays.toString(new EvaluateDivision().calcEquation(
                asList(asList("a", "b"), asList("b", "c"), asList("d", "e"), asList("a", "d")),
                new double[]{ 1.0, 2.0, 3.0, 4.0 },
                singletonList(asList("c", "e"))
        )));
    }
}