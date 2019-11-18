package present.programmer.algorithms.sandbox.collection.queue.eightpuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    private final int[][] tiles;

    public Board(int[][] tiles) {
        if (tiles == null || tiles.length < 2) {
            throw new IllegalArgumentException();
        }
        this.tiles = copyTiles(tiles);
    }

    @Override
    public String toString() {
        final StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(tiles.length).append('\n');
        for (final int[] row : tiles) {
            for (final int tile : row) {
                strBuilder.append(tile).append(' ');
            }
            strBuilder.append('\n');
        }
        return strBuilder.toString();
    }

    /**
     * board dimension n
     */
    public int dimension() {
        return tiles.length;
    }

    /**
     * number of tiles out of place
     */
    public int hamming() {
        int outOfPlace = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] != expectedTile(i, j) && tiles[i][j] != 0) {
                    outOfPlace++;
                }
            }
        }
        return outOfPlace;
    }

    /**
     * sum of Manhattan distances between tiles and goal
     */
    public int manhattan() {
        int manhattanSum = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                manhattanSum += manhattan(i, j);
            }
        }
        return manhattanSum;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    @Override
    public boolean equals(Object y) {
        if (y == null || this.getClass() != y.getClass()) {
            return false;
        }
        final Board that = (Board) y;
        if (this.tiles.length != that.tiles.length) {
            return false;
        }
        for (int i = 0; i < this.tiles.length; i++) {
            if (this.tiles[i].length != that.tiles[i].length) {
                return false;
            }
            for (int j = 0; j < this.tiles[i].length; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * all neighboring boards
     */
    public Iterable<Board> neighbors() {
        int i = 0;
        int j = 0;
        searchFor0:
        for (i = 0; i < tiles.length; i++) {
            for (j = 0; j < tiles.length; j++) {
                if (tiles[i][j] == 0) {
                    break searchFor0;
                }
            }
        }

        final List<Board> neighbors = new ArrayList<>();
        for (final int[] dir : DIRECTIONS) {
            final int di = i + dir[0];
            final int dj = j + dir[1];
            if (0 <= di && di < tiles.length && 0 <= dj && dj < tiles.length) {
                final int[][] neighborTiles = copyTiles(tiles);
                neighborTiles[i][j] = tiles[di][dj];
                neighborTiles[di][dj] = tiles[i][j];
                neighbors.add(new Board(neighborTiles));
            }
        }
        return neighbors;
    }

    /**
     * a board that is obtained by exchanging any pair of tiles
     */
    public Board twin() {
        final int[][] copiedTiles = copyTiles(tiles);
        final int i1 = 0;
        final int j1 = tiles[i1][0] == 0 ? 1 : 0;
        final int i2 = 1;
        final int j2 = tiles[i2][0] == 0 ? 1 : 0;
        swap(copiedTiles, i1, j1, i2, j2);
        return new Board(copiedTiles);
    }

    public static void main(String[] args) {
        final Board board = new Board(new int[][] {
                { 8, 1, 3 },
                { 4, 0, 2 },
                { 7, 6, 5 }
        });
        System.out.println(board);
        System.out.println(board.hamming());
        System.out.println(board.manhattan());
        System.out.println(board.equals(new Board(new int[][] {
                { 8, 1, 3 },
                { 4, 0, 2 },
                { 7, 6, 5 }
        })));

        System.out.println();
        System.out.println("Neighbors:");
        for (final Board neighbor : board.neighbors()) {
            System.out.println(neighbor);
        }

        System.out.println();
        final Board board2 = new Board(new int[][] {
                { 1, 0, 3 },
                { 4, 2, 5 },
                { 7, 8, 6 },
        });
        System.out.println("Neighbors of " + board2);
        for (final Board neighbor : board2.neighbors()) {
            System.out.println(neighbor);
        }

        System.out.println();
        System.out.println("Twin of\n" + board + "--is--\n" + board.twin());
    }

    private static int[][] copyTiles(int[][] tiles) {
        return Arrays.stream(tiles)
                .map(int[]::clone)
                .toArray(int[][]::new);
    }

    private static void swap(int[][] m, int i1, int j1, int i2, int j2) {
        final int temp = m[i1][j1];
        m[i1][j1] = m[i2][j2];
        m[i2][j2] = temp;
    }

    private int expectedTile(int i, int j) {
        return i * tiles.length + j + 1;
    }

    private int manhattan(int i, int j) {
        if (tiles[i][j] == 0) {
            return 0;
        }
        final int correctI = (tiles[i][j] - 1) / tiles.length;
        final int correctJ = (tiles[i][j] - 1) % tiles.length;
        return Math.abs(i - correctI) + Math.abs(j - correctJ);
    }
}