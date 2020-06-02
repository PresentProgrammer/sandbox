/**
 * Problem #72
 * Time complexity: O(N * M)
 * Space complexity: O(N * M)
 **/
public class EditDistance {

    public int minDistance(String source, String target) {
        final int[][] d = initMatrix(source, target);
        for (int i = 1; i <= source.length(); i++) {
            for (int j = 1; j <= target.length(); j++) {
                d[i][j] = 1 + min(
                        d[i - 1][j],
                        d[i][j - 1],
                        d[i - 1][j - 1] - (source.charAt(i - 1) == target.charAt(j - 1) ? 1 : 0)
                );
            }
        }
        return d[source.length()][target.length()];
    }

    private static int[][] initMatrix(String source, String target) {
        final int[][] d = new int[source.length() + 1][target.length() + 1];
        for (int i = 0; i < d.length; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j < d[0].length; j++) {
            d[0][j] = j;
        }
        return d;
    }

    private static int min(int first, int second, int third) {
        return Math.min(first, Math.min(second, third));
    }

    public static void main(final String[] args) {
        System.out.println("3 == " + new EditDistance().minDistance("horse", "ros"));
        System.out.println("5 == " + new EditDistance().minDistance("intention", "execution"));
        System.out.println("2 == " + new EditDistance().minDistance("sea", "eat"));
        System.out.println("6 == " + new EditDistance().minDistance("dinitrophenylhydrazine", "acetylphenylhydrazine"));
	}
}