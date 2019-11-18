package present.programmer.algorithms.sandbox.collection.queue.eightpuzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Solver {

    private final List<Board> solution;

    /**
     * find a solution to the initial board (using the A* algorithm)
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        final MinPQ<Node> mainPQ = new MinPQ<>(new Node[]{ new Node(initial, null) });
        final MinPQ<Node> twinPQ = new MinPQ<>(new Node[]{ new Node(initial.twin(), null) });
        while (!mainPQ.min().isGoal() && !twinPQ.min().isGoal()) {
            goDeeper(mainPQ);
            goDeeper(twinPQ);
        }
        this.solution = mainPQ.min().isGoal() ? mainPQ.min().getPath() : null;
    }

    /**
     * is the initial board solvable? (see below)
     */
    public boolean isSolvable() {
        return solution != null;
    }

    /**
     * min number of moves to solve initial board
     */
    public int moves() {
        return isSolvable() ? solution.size() - 1 : -1;
    }

    /**
     * sequence of boards in a shortest solution
     */
    public Iterable<Board> solution() {
        return solution;
    }

    private static void goDeeper(final MinPQ<Node> pq) {
        final Node min = pq.delMin();
        for (final Node neighbor : min.neighbors()) {
            pq.insert(neighbor);
        }
    }

    private static class Node implements Comparable<Node> {

        private final Board board;
        private final Node prev;
        private final int depth;
        private final int cost;

        Node(final Board board, final Node prev) {
            this.board = board;
            this.prev = prev;
            this.depth = prev == null ? 0 : prev.depth + 1;
            this.cost = this.depth + board.manhattan();
        }

        boolean isGoal() {
            return board.isGoal();
        }

        Iterable<Node> neighbors() {
            return StreamSupport.stream(board.neighbors().spliterator(), false)
                         .filter(neighborBoard -> prev == null || !prev.board.equals(neighborBoard))
                         .map(neighborBoard -> new Node(neighborBoard, this))
                         .collect(Collectors.toList());
        }

        List<Board> getPath() {
            final List<Board> path = new ArrayList<>();
            Node curr = this;
            while (curr != null) {
                path.add(curr.board);
                curr = curr.prev;
            }
            Collections.reverse(path);
            return path;
        }

        @Override
        public int compareTo(final Node that) {
            return this.cost - that.cost;
        }
    }
}