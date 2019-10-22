import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #1192
 * Time complexity: O(e)
 * Space complexity: O(v + e)
 **/
public class CriticalConnectionsInNetwork {

    private AdjacencyNode[] adjacencyList;
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
        AdjacencyNode wNode = adjacencyList[v];
        while (wNode != null) {
            Integer w = wNode.node;
            if (index[w] == 0) {
                dfs(w, v);
                lowlink[v] = Math.min(lowlink[v], lowlink[w]);
                if (lowlink[w] == index[w]) {
                    criticalCons.add(Arrays.asList(v, w));
                }
            } else if (w != prev) {
                lowlink[v] = Math.min(lowlink[v], index[w]);
            }
            wNode = wNode.next;
        }
    }

    private static AdjacencyNode[] generateAdjacencyList(int n, List<List<Integer>> connections) {
        final AdjacencyNode[] adjacencyList = new AdjacencyNode[n];
        final AdjacencyNode[] lastInAdjacencyList = new AdjacencyNode[n];
        for (final List<Integer> con : connections) {
            final AdjacencyNode first = new AdjacencyNode(con.get(0));
            final AdjacencyNode second = new AdjacencyNode(con.get(1));
            updateAdjacencyList(adjacencyList, lastInAdjacencyList, first, second);
            updateAdjacencyList(adjacencyList, lastInAdjacencyList, second, first);
        }
        return adjacencyList;
    }

    private static void updateAdjacencyList(AdjacencyNode[] adjacencyList, AdjacencyNode[] lastInAdjacencyList,
            AdjacencyNode out, AdjacencyNode in) {
        if (adjacencyList[out.node] == null) {
            adjacencyList[out.node] = in;
            lastInAdjacencyList[out.node] = in;
        } else {
            lastInAdjacencyList[out.node].next = in;
            lastInAdjacencyList[out.node] = in;
        }
    }

    private static class AdjacencyNode {
        final Integer node;
        AdjacencyNode next;

        AdjacencyNode(final Integer node) {
            this.node = node;
        }
    }
}