import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem #973
 * Time complexity: O(N log K)
 * Space complexity: O(K)
 **/
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        final MaxSizePQ pq = new MaxSizePQ(K);
        for (final int[] point : points) {
            pq.offer(new Point(point));
        }
        return pq.stream()
                .map(p -> p.coordinates)
                .toArray(int[][]::new);
    }

    private static class Point {
        final int[] coordinates;
        final int distance;

        Point(int[] coordinates) {
            this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
            this.distance = coordinates[0] * coordinates[0] + coordinates[1] * coordinates[1];
        }
    }

    private static class MaxSizePQ extends PriorityQueue<Point> {

        private final int maxSize;

        MaxSizePQ(int maxSize) {
            super(maxSize + 1, Comparator.comparingInt((Point p) -> p.distance).reversed());
            this.maxSize = maxSize;
        }

        @Override
        public boolean offer(Point p) {
            super.offer(p);
            if (size() > maxSize) {
                poll();
            }
            return true;
        }
    }

    public static void main(final String[] args) {
        final int[][] points = {{1, 3}, {-2, -2}};
        System.out.println("[[-2, -2]] == " + Arrays.deepToString(new KClosestPointsToOrigin().kClosest(points, 1)));
    }
}