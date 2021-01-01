import java.util.HashMap;
import java.util.Map;

/**
 * Problem #753
 * Solved with Eulerian Path approach. For reference:
 * - Existence of Eulerian Paths and Circuits: https://www.youtube.com/watch?v=xR4sGgwtR2I
 * - Eulerian Path/Circuit algorithm (Hierholzer's algorithm): https://www.youtube.com/watch?v=8MpoO2zA2l4
 * Time complexity: O(|E|) = O(k ^ n), as |V| = k ^ (n - 1), and |E| = |V| * k
 * Space complexity: O(|V| + |E|) = O(k ^ n)
 **/
public class CrackingTheSafe {

    private final Map<String, Integer> nextEdges = new HashMap<>();
    private final StringBuilder resultBuilder = new StringBuilder();
    private int k;

    public String crackSafe(int n, int k) {
        this.k = k;
        final String start = createStart(n);
        dfs(start);
        return resultBuilder.append(start).toString();
    }

    private void dfs(String curr) {
        int nextEdge;
        while ((nextEdge = nextEdges.computeIfAbsent(curr, unusedKey -> 0)) < k) {
            nextEdges.put(curr, nextEdge + 1);
            dfs((curr + nextEdge).substring(1));
            resultBuilder.append(nextEdge);
        }
    }

    private static String createStart(int n) {
        final StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            strBuilder.append('0');
        }
        return strBuilder.toString();
    }

    public static void main(final String[] args) {
        System.out.println("01100 == " + new CrackingTheSafe().crackSafe(2, 2));
    }
}