import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Problem #279
 * Time complexity: O(sqrt(n))
 * Space complexity: O(n)
 **/
public class PerfectSquares {

    public int numSquares(final int n) {
        final List<Integer> squareList = new ArrayList<>();
        int square;
        for (int i = 1; (square = i * i) <= n; i++) {
            squareList.add(square);
        }
        final int[] squares = squareList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        final int[] lengths = new int[n + 1];

        final Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            final int curr = queue.poll();
            for (int i = 0; i < squares.length && squares[i] <= curr; i++) {
                final int next = curr - squares[i];
                if (lengths[next] == 0) {
                    lengths[next] = lengths[curr] + 1;
                    if (next == 0) {
                        return lengths[next];
                    } else {
                        queue.offer(next);
                    }
                }
            }
        }
        return 0;
    }
    
    public static void main(final String[] args) {
	}
}