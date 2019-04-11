/**
 * Problem #130
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class SurroundedRegions {

    private char[][] board;
    private boolean[][] visited;

    public void solve(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        this.board = board;
		this.visited = new boolean[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {
		    saveRegion(i, 0);
		    saveRegion(i, board[i].length - 1);
        }
		for (int j = 1; j < board[0].length - 1; j++) {
		    saveRegion(0, j);
		    saveRegion(board.length - 1, j);
        }

		for (int i = 0; i < board.length; i++) {
		    for (int j = 0; j < board[i].length; j++) {
		        if (!visited[i][j]) {
		            board[i][j] = 'X';
                }
            }
        }
    }

    private void saveRegion(int i, int j) {
        if (0 <= i && i < board.length && 0 <= j && j < board[i].length && !visited[i][j]) {
            visited[i][j] = true;
            if (board[i][j] == 'O') {
                saveRegion(i - 1, j);
                saveRegion(i + 1, j);
                saveRegion(i, j - 1);
                saveRegion(i, j + 1);
            }
        }
    }

    public static void main(final String[] args) {
	}
}