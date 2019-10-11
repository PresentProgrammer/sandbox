import java.util.ArrayList;
import java.util.List;

/**
 * Problem #838
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class PushDominoes {

    private static final char RIGHT = 'R';
    private static final char LEFT = 'L';
    private static final char STANDING = '.';

    public String pushDominoes(String dominoes) {
        final char[] dominoesArr = dominoes.toCharArray();
        List<Integer> rights = new ArrayList<>();
        List<Integer> lefts = new ArrayList<>();
        for (int i = 0; i < dominoesArr.length; i++) {
            if (dominoesArr[i] == RIGHT) {
                rights.add(i);
            } else if (dominoesArr[i] == LEFT) {
                lefts.add(i);
            }
        }

        while (!rights.isEmpty() || !lefts.isEmpty()) {
            for (final int right : rights) {
                dominoesArr[right] = RIGHT;
            }
            for (final int left : lefts) {
                dominoesArr[left] = LEFT;
            }

            final List<Integer> newRights = new ArrayList<>();
            for (final int right : rights) {
                if (right + 1 < dominoesArr.length && dominoesArr[right + 1] == STANDING) {
                    if (right + 2 == dominoesArr.length || dominoesArr[right + 2] != LEFT) {
                        newRights.add(right + 1);
                    }
                }
            }
            rights = newRights;

            final List<Integer> newLefts = new ArrayList<>();
            for (final int left : lefts) {
                if (left - 1 >= 0 && dominoesArr[left - 1] == STANDING) {
                    if (left - 2 == -1 || dominoesArr[left - 2] != RIGHT) {
                        newLefts.add(left - 1);
                    }
                }
            }
            lefts = newLefts;
        }
        return new String(dominoesArr);
    }
}