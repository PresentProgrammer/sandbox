import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem #378
 * Time complexity: O(k)
 * Space complexity: O(n ^ 2 + k)
 **/
public class KthSmallestElementInSortedMatrix {

    private static final int[][] FROM_BEGINNING_DIRECTIONS = new int[][]{{1, 0},{0, 1}};
    private static final int[][] FROM_ENDING_DIRECTIONS = new int[][]{{-1, 0}, {0, -1}};

    public int kthSmallest(final int[][] matrix, final int k) {
        final int N = matrix.length;
        final boolean fromBeginning = k <= N * N / 2;
        final Comparator<Pair<Integer, Integer>> comparator = fromBeginning
                ? new Comparator<Pair<Integer, Integer>>(){
                    @Override
                    public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                        return matrix[p1.getKey()][p1.getValue()] - matrix[p2.getKey()][p2.getValue()];
                    }
                }
                : new Comparator<Pair<Integer, Integer>>(){
                    @Override
                    public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                        return matrix[p2.getKey()][p2.getValue()] - matrix[p1.getKey()][p1.getValue()];
                    }
                };
        final int relativeK = fromBeginning ? k : N * N - k + 1;
        final int[][] directions = fromBeginning ? FROM_BEGINNING_DIRECTIONS : FROM_ENDING_DIRECTIONS;
        final boolean[][] visited = new boolean[N][matrix[0].length];
        final PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<>(comparator);
        q.offer(fromBeginning ? new Pair<>(0, 0) : new Pair<>(N - 1, N - 1));
        visited[q.peek().getKey()][q.peek().getValue()] = true;
        int count = 1;
        while (count < relativeK) {
            final Pair<Integer, Integer> curr = q.poll();
            for (final int[] dir : directions) {
                final int i = curr.getKey() + dir[0];
                final int j = curr.getValue() + dir[1];
                if (0 <= i && i < N && 0 <= j && j < N && !visited[i][j]) {
                    q.offer(new Pair<>(i, j));
                    visited[i][j] = true;
                }
            }
            count++;
        }
        return matrix[q.peek().getKey()][q.peek().getValue()];
    }
    
    public static void main(final String[] args) {
	}
}