import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Problem #1197
 * Time complexity: O(x * y)
 * Space complexity: O(x * y)
 **/
class MinimumKnightMoves {

    private static final int[][] DIRS = {
            {-2, 1}, {-1, 2}, {1, 2}, {2, 1},
            {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
    };

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        final boolean[][] visited = new boolean[304][304];
        visited[1][1] = true;
        final int targetX = Math.abs(x) + 1;
        final int targetY = Math.abs(y) + 1;
        final Queue<State> q = new ArrayDeque<>();
        q.offer(new State(1, 1, 0));

        while (!q.isEmpty()) {
            final State s = q.poll();
            if (s.x == targetX && s.y == targetY) {
                return s.depth;
            } else {
                for (final int[] dir : DIRS) {
                    final int newX = s.x + dir[0];
                    final int newY = s.y + dir[1];
                    if (0 <= newX && newX < visited.length && 0 <= newY && newY < visited.length
                            && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        q.offer(new State(newX, newY, s.depth + 1));
                    }
                }
            }
        }
        return -1;
    }

    private static class State {

        final int x;
        final int y;
        final int depth;

        State(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MinimumKnightMoves().minKnightMoves(2, 30));
    }
}