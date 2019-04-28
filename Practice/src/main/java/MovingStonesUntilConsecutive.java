import java.util.Arrays;

/**
 * Problem: Weekly Contest 134: #1033
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class MovingStonesUntilConsecutive {

    public int[] numMovesStones(int a, int b, int c) {
        int min, mid, max;
        if (a < b) {
            if (a < c) {
                min = a;
                if (b < c) {
                    mid = b;
                    max = c;
                } else {
                    mid = c;
                    max = b;
                }
            } else {
                min = c;
                mid = a;
                max = b;
            }
        } else {
            if (b > c) {
                min = c;
                mid = b;
                max = a;
            } else {
                min = b;
                if (a > c) {
                    mid = c;
                    max = a;
                } else {
                    mid = a;
                    max = c;
                }
            }
        }

        if (mid - min == 2 || max - mid == 2) {
            return new int[] {1, max - min - 2};
        } else {
            int minMoves = 0;
            if (mid - min > 1) {
                minMoves++;
            }
            if (max - mid > 1) {
                minMoves++;
            }
            return new int[] { minMoves, max - min - 2 };
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("[1, 2] == " + Arrays.toString(new MovingStonesUntilConsecutive().numMovesStones(1, 2, 5)));
        System.out.println("[0, 0] == " + Arrays.toString(new MovingStonesUntilConsecutive().numMovesStones(2, 3, 4)));
        System.out.println("[1, 6] == " + Arrays.toString(new MovingStonesUntilConsecutive().numMovesStones(2, 10, 4)));
	}
}