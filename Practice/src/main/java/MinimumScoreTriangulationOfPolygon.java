/**
 * Problem #1039
 * Time complexity: O(n ^ 2)
 * Space complexity: O(n ^ 2)
 **/
public class MinimumScoreTriangulationOfPolygon {

    public int minScoreTriangulation(int[] p) {
        final int[][] dp = new int[p.length][p.length];
        for (int d = 2; d < p.length; d++) {
            for (int i = 0, j = i + d; j < p.length; i++, j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + p[i] * p[k] * p[j]);
                }
            }
        }
        return dp[0][p.length - 1];
    }

    public static void main(final String[] args) {
        System.out.println("6 == " + new MinimumScoreTriangulationOfPolygon().minScoreTriangulation(new int[]{1,2,3}));
        System.out.println("144 == " + new MinimumScoreTriangulationOfPolygon().minScoreTriangulation(new int[]{3,7,4,5}));
        System.out.println("13 == " + new MinimumScoreTriangulationOfPolygon().minScoreTriangulation(new int[]{1,3,1,4,1,5}));
        System.out.println("140295 == " + new MinimumScoreTriangulationOfPolygon()
                .minScoreTriangulation(new int[]{35,73,90,27,71,80,21,33,33,13,48,12,68,70,80,36,66,3,70,58}));
	}
}