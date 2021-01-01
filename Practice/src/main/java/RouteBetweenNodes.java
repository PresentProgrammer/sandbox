import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static java.util.Collections.emptyList;
import static java.util.Collections.reverse;
import static java.util.Collections.singletonList;

/**
 * Problem #4.1
 * Time complexity: O(v * k ^ (d / 2) + e) in case there is path:
 * O(e): to create adj. matrix (e — number of edges);
 * O(k ^ (d / 2)): how many nodes will be visited (k — average successor count, d — distance between nodes of interest);
 * O(v): we go through the column or row of matrix for each visited node.
 * O(v ^ 2 + e) in case there is no path, and this is the worst case.
 * Space complexity: O(v ^ 2)).
 **/
public class RouteBetweenNodes {

    public List<Integer> findPath(final int[][] edges, final int start, final int end) {
        if (start == end) {
            return singletonList(start);
        }
        final int[][] adjacencyMatrix = createAdjacencyMatrix(edges);
        final Map<Integer, Integer> leftPrev = new HashMap<>();
        final Queue<Integer> leftQ = new ArrayDeque<>();
        final Map<Integer, Integer> rightPrev = new HashMap<>();
        final Queue<Integer> rightQ = new ArrayDeque<>();
        leftPrev.put(start, -1);
        leftQ.offer(start);
        rightPrev.put(end, -1);
        rightQ.offer(end);
        while (!leftQ.isEmpty() && !rightQ.isEmpty()) {
            final int currLeft = leftQ.poll();
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[currLeft][j] == 1 && !leftPrev.containsKey(j)) {
                    leftPrev.put(j, currLeft);
                    if (rightPrev.containsKey(j)) {
                        return getPath(j, leftPrev, rightPrev);
                    } else {
                        leftQ.offer(j);
                    }
                }
            }

            final int currRight = rightQ.poll();
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[i][currRight] == 1 && !rightPrev.containsKey(i)) {
                    rightPrev.put(i, currRight);
                    if (leftPrev.containsKey(i)) {
                        return getPath(i, leftPrev, rightPrev);
                    } else {
                        rightQ.offer(i);
                    }
                }
            }
        }
        return emptyList();
    }

    private static int[][] createAdjacencyMatrix(final int[][] edges) {
        final int n = getNodeCount(edges);
        final int[][] matrix = new int[n + 1][n + 1];
        for (final int[] edge : edges) {
            matrix[edge[0]][edge[1]] = 1;
        }
        return matrix;
    }

    private static int getNodeCount(final int[][] edges) {
        int n = 0;
        for (final int[] edge : edges) {
            n = Math.max(n, edge[0]);
            n = Math.max(n, edge[1]);
        }
        return n;
    }

    private static List<Integer> getPath(final int meetingPoint,
            final Map<Integer, Integer> leftPrev, final Map<Integer, Integer> rightPrev) {
        final List<Integer> path = pathTo(meetingPoint, leftPrev);
        reverse(path);
        path.add(meetingPoint);
        path.addAll(pathTo(meetingPoint, rightPrev));
        return path;
    }

    private static List<Integer> pathTo(final int meetingPoint, final Map<Integer, Integer> prev) {
        final List<Integer> path = new ArrayList<>();
        int curr = prev.get(meetingPoint);
        while (curr != -1) {
            path.add(curr);
            curr = prev.get(curr);
        }
        return path;
    }

    public static void main(final String[] args) {
        final int[][] edges = {
                {1, 2}, {1, 3}, {1, 4},
                {2, 5},
                {4, 5}, {4, 6}, {4, 7}, {4, 8},
                {5, 6},
                {8, 9}, {8, 12},
                {9, 10},
                {10, 11}, {10, 11}, {10, 12}, {10, 15},
                {11, 12},
                {13, 14}
        };
        System.out.println("[1, 4, 8, 9/12, 10] == " + new RouteBetweenNodes().findPath(edges, 1, 10));
        System.out.println("[1, 4, 8, 12] == " + new RouteBetweenNodes().findPath(edges, 1, 12));
        System.out.println("[1] == " + new RouteBetweenNodes().findPath(edges, 1, 1));
        System.out.println("[13, 14] == " + new RouteBetweenNodes().findPath(edges, 13, 14));
        System.out.println("[] == " + new RouteBetweenNodes().findPath(edges, 1, 14));
    }
}