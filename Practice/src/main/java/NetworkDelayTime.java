import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Problem #743
 * Time complexity: O(V log V)
 * Space complexity: O(N + V)
 **/
public class NetworkDelayTime {

    @SuppressWarnings("unchecked")
    public int networkDelayTime(int[][] times, int N, int K) {
        final List<Pair>[] incList = Stream
                .generate(ArrayList::new)
                .limit(N)
                .toArray(List[]::new);
        for (final int[] time : times) {
            incList[time[0] - 1].add(new Pair(time[1] - 1, time[2]));
        }

        final int[] dist = IntStream
                .generate(() -> Integer.MAX_VALUE)
                .limit(N)
                .toArray();
        dist[K - 1] = 0;
        final boolean[] visited = new boolean[N];
        int visitedCount = 0;
        final PriorityQueue<Integer> pq = new PriorityQueue<>(
                Comparator.comparingInt((Integer u) -> dist[u]));
        pq.offer(K - 1);

        while (!pq.isEmpty()) {
            final int u = pq.poll();
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            visitedCount++;
            if (visitedCount == N) {
                break;
            }

            for (final Pair vw : incList[u]) {
                if (!visited[vw.getKey()]) {
                    final int distToV = dist[u] + vw.getValue();
                    if (dist[vw.getKey()] > distToV) {
                        dist[vw.getKey()] = distToV;
                        pq.offer(vw.getKey());
                    }
                }
            }
        }
        return visitedCount == N ? IntStream.of(dist).max().orElse(-1) : -1;
    }

    private static class Pair {

        final int key;
        final int value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        int getKey() {
            return key;
        }

        int getValue() {
            return value;
        }
    }
}