/**
 * Problem #62
 * Time complexity: O(n * m)
 * Space complexity: O(n * m)
 **/
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
		final int[][] board = new int[n][m];
		for (int i = 0; i < n; i++) {
		    board[i][0] = 1;
        }
		for (int j = 1; j < m; j++) {
		    board[0][j] = 1;
        }
		for (int i = 1; i < n; i++) {
		    for (int j = 1; j < m; j++) {
		        board[i][j] = board[i - 1][j] + board[i][j - 1];
            }
        }
		return board[n - 1][m - 1];
    }
    
    public static void main(final String[] args) {
        System.out.println("3 == " + new UniquePaths().uniquePaths(3, 2));
        System.out.println("28 == " + new UniquePaths().uniquePaths(7, 3));
	}
}