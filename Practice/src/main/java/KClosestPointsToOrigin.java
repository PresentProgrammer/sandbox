import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Problem #973
 * Time complexity: O(N)
 * Space complexity: O(N)
 **/
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        final int[] distances = Arrays.stream(points)
                .mapToInt(p -> p[0] * p[0] + p[1] * p[1])
                .toArray();
        final int[] indexes = IntStream.range(0, distances.length).toArray();
        quickSelect(indexes, distances, K - 1);
        return IntStream.of(indexes)
                .limit(K)
                .mapToObj(i -> points[i])
                .toArray(int[][]::new);
    }

    private static void quickSelect(int[] indexes, int[] distances, int k) {
        shuffle(indexes);
        int lo = 0;
        int hi = indexes.length - 1;
        int j = hi;
        while (j != k) {
            j = partition(indexes, distances, lo, hi);
            if (j > k) {
                hi = j - 1;
            } else if (j < k) {
                lo = j + 1;
            }
        }
    }

    private static int partition(int[] indexes, int[] distances, int lo, int hi) {
        int i = lo + 1;
        int j = hi;
        while (true) {
            while (i <= hi && distances[indexes[i]] < distances[indexes[lo]]) {
                i++;
            }
            while (distances[indexes[j]] > distances[indexes[lo]]) {
                j--;
            }
            if (i < j) {
                swap(indexes, i++, j--);
            } else {
                swap(indexes, lo, j);
                return j;
            }
        }
    }

    private static void shuffle(int[] arr) {
        final Random rand = new Random();
        for (int i = 1; i < arr.length; i++) {
            swap(arr, i, rand.nextInt(i + 1));
        }
    }

    private static void swap(int[] arr, int a, int b) {
        final int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(final String[] args) {
        final int[][] points = {{1, 3}, {-2, -2}};
        System.out.println("[[-2, -2]] == " + Arrays.deepToString(new KClosestPointsToOrigin().kClosest(points, 1)));
    }
}