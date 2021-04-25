import java.util.Arrays;

/**
 * Problem #407
 * Time complexity: O(N * M)
 * Space complexity: O(N * M)
 **/
public class TrappingRainWaterII {

    private static final int[][] DIRS = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

    public int trapRainWater(int[][] heights) {
        final int[][][] dps = buildDps(heights);
        int result = 0;
        for (int i = 1; i < heights.length; i++) {
            for (int j = 1; j < heights[i].length; j++) {
                result += findMin(dps, i, j) - heights[i][j];
            }
        }
        return result;
    }

    private static int[][][] buildDps(int[][] heights) {
        final int[][][] dps = new int[DIRS.length][][];
        for (int i = 0; i < DIRS.length; i++) {
            dps[i] = buildDp(heights, DIRS[i][0], DIRS[i][1]);
        }
        return dps;
    }

    private static int[][] buildDp(int[][] heights, int iDir, int jDir) {
        final int[][] dp = copyOf(heights);
        for (int i = iDir > 0 ? 1 : dp.length - 2; 1 <= i && i < dp.length - 1; i += iDir) {
            for (int j = jDir > 0 ? 1 : dp[i].length - 2; 1 <= j && j < dp[i].length - 1; j += jDir) {
                dp[i][j] = Math.max(dp[i][j], Math.min(dp[i - iDir][j], dp[i][j - jDir]));
            }
        }
        return dp;
    }

    private static int[][] copyOf(int[][] heights) {
        final int[][] copy = new int[heights.length][];
        for (int i = 0; i < heights.length; i++) {
            copy[i] = Arrays.copyOf(heights[i], heights[i].length);
        }
        return copy;
    }

    private static int findMin(int[][][] dps, int i, int j) {
        int min = Integer.MAX_VALUE;
        for (final int[][] dp : dps) {
            min = Math.min(min, dp[i][j]);
        }
        return min;
    }

    public static void main(final String[] args) {
        System.out.println("4 == " + new TrappingRainWaterII().trapRainWater(new int[][]{
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        }));
        System.out.println("10 == " + new TrappingRainWaterII().trapRainWater(new int[][]{
                {3,3,3,3,3},
                {3,2,2,2,3},
                {3,2,1,2,3},
                {3,2,2,2,3},
                {3,3,3,3,3},
        }));
        System.out.println("79058 == " + new TrappingRainWaterII().trapRainWater(new int[][]{{19383,10886,12777,16915,17793,18335,15386,10492,16649,11421},{12362,27,8690,59,7763,3926,540,3426,9172,5736},{15211,5368,2567,6429,5782,1530,2862,5123,4067,3135},{13929,9802,4022,3058,3069,8167,1393,8456,5011,8042},{16229,7373,4421,4919,3784,8537,5198,4324,8315,4370},{16413,3526,6091,8980,9956,1873,6862,9170,6996,7281},{12305,925,7084,6327,336,6505,846,1729,1313,5857},{16124,3895,9582,545,8814,3367,5434,364,4043,3750},{11087,6808,7276,7178,5788,3584,5403,2651,2754,2399},{19932,5060,9676,3368,7739,12,6226,8586,8094,7539}}));
    }
}