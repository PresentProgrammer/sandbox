import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Problem #973
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        final int[] distances = Arrays.stream(points)
                .mapToInt(p -> p[0] * p[0] + p[1] * p[1])
                .toArray();
        return IntStream.range(0, points.length)
                .boxed()
                .sorted(Comparator.comparingInt(i -> distances[i]))
                .limit(K)
                .map(i -> points[i])
                .toArray(int[][]::new);
    }

    public static void main(final String[] args) {
        final int[][] points = {{1, 3}, {-2, -2}};
        System.out.println("[[-2, -2]] == " + Arrays.deepToString(new KClosestPointsToOrigin().kClosest(points, 1)));
    }
}