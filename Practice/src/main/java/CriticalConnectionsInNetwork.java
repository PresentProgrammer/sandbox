import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #1192
 * Time complexity: O(e)
 * Space complexity: O(v + e)
 **/
public class CriticalConnectionsInNetwork {

    private List<Integer>[] adjacencyList;
    private int currIndex;
    private int[] index;
    private int[] lowlink;
    private List<List<Integer>> criticalCons;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.adjacencyList = generateAdjacencyList(n, connections);
        this.currIndex = 1;
        this.index = new int[n];
        this.lowlink = new int[n];
        this.criticalCons = new ArrayList<>();
        dfs(connections.get(0).get(0), -1);
        return criticalCons;
    }

    private void dfs(final int v, final int prev) {
        index[v] = currIndex;
        lowlink[v] = currIndex;
        currIndex++;
        for (final Integer w : adjacencyList[v]) {
            if (index[w] == 0) {
                dfs(w, v);
                lowlink[v] = Math.min(lowlink[v], lowlink[w]);
                if (lowlink[w] == index[w]) {
                    criticalCons.add(Arrays.asList(v, w));
                }
            } else if (w != prev) {
                lowlink[v] = Math.min(lowlink[v], index[w]);
            }
        }
    }

    private static List<Integer>[] generateAdjacencyList(int n, List<List<Integer>> connections) {
        final List<Integer>[] adjacencyList = new List[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        for (final List<Integer> con : connections) {
            adjacencyList[con.get(0)].add(con.get(1));
            adjacencyList[con.get(1)].add(con.get(0));
        }
        return adjacencyList;
    }
}