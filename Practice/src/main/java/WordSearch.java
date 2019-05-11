/**
 * Problem #79
 * Time complexity: O(2^m * n)
 * Space complexity: O(n + m), where n is board size, and m is word length.
 **/
public class WordSearch {

    private static final int[][] DIRECTIONS = new int[][]{ {1, 0}, {-1, 0}, {0, 1}, {0, -1}, };

    private char[][] board;
    private String word;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
		    return false;
        }
		this.board = board;
		this.word = word;
		this.visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (exist(0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(final int pos, final int i, final int j) {
        if (pos == word.length()) {
            return true;
        } else if (0 <= i && i < board.length && 0 <= j && j < board[i].length && !visited[i][j] && word.charAt(pos) == board[i][j]) {
            visited[i][j] = true;
            for (final int[] dir : DIRECTIONS) {
                if (exist(pos + 1, i + dir[0], j + dir[1])) {
                    return true;
                }
            }
            visited[i][j] = false;
            return false;
        } else {
            return false;
        }
    }
}