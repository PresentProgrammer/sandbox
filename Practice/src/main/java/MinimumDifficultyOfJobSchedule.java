import java.util.Arrays;

/**
 * Problem #1335
 * Time complexity: O(N * (N + D)), where N = difficulty.length, D = d.
 * Space complexity: O(N * (N + D))
 **/
public class MinimumDifficultyOfJobSchedule {

    public int minDifficulty(int[] difficulty, int d) {
        if (difficulty.length < d) {
            return -1;
        }

        final int[][] periodDifficulty = new int[difficulty.length][difficulty.length];
        for (int i = 0; i < difficulty.length; i++) {
            periodDifficulty[i][i] = difficulty[i];
            for (int j = i + 1; j < difficulty.length; j++) {
                periodDifficulty[i][j] = Math.max(periodDifficulty[i][j - 1], difficulty[j]);
            }
        }

        final int[][] memo = new int[periodDifficulty.length][d + 1];
        for (final int[] memoRow : memo) {
            Arrays.fill(memoRow, -1);
        }

        return minDifficulty(periodDifficulty, 0, d, memo);
    }

    private static int minDifficulty(int[][] periodDifficulty, int firstDay, int daysRemaining, int[][] memo) {
        if (memo[firstDay][daysRemaining] == -1) {
            if (daysRemaining == 1) {
                memo[firstDay][daysRemaining] = periodDifficulty[firstDay][periodDifficulty.length - 1];
            } else {
                int min = Integer.MAX_VALUE;
                for (int lastDay = firstDay; lastDay < periodDifficulty.length - (daysRemaining - 1); lastDay++) {
                    final int candidate = periodDifficulty[firstDay][lastDay] + minDifficulty(periodDifficulty, lastDay + 1, daysRemaining - 1, memo);
                    min = Math.min(min, candidate);
                }
                memo[firstDay][daysRemaining] = min;
            }
        }
        return memo[firstDay][daysRemaining];
    }

    public static void main(final String[] args) {
        System.out.println("7 == " + new MinimumDifficultyOfJobSchedule()
                .minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));
    }
}