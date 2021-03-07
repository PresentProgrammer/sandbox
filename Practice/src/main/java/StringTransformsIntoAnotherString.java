import java.util.Arrays;

/**
 * Problem #1153
 * Time complexity: O(N)
 * Space complexity: O(1)
 **/
public class StringTransformsIntoAnotherString {

    private static final int EMPTY = -1;
    private static final int ABC_LENGTH = 'z' - 'a' + 1;

    public boolean canConvert(String str1, String str2) {
        final int[] map = new int[ABC_LENGTH];
        Arrays.fill(map, EMPTY);
        for (int i = 0; i < str1.length(); i++) {
            final int char1 = toInt(str1.charAt(i));
            final int char2 = toInt(str2.charAt(i));
            if (map[char1] >= 0 && map[char1] != char2) {
                return false;
            } else {
                map[char1] = char2;
            }
        }
        return isFreeCharacterInValues(map) || allMatch(map);
    }

    private static boolean isFreeCharacterInValues(int[] map) {
        final boolean[] used = new boolean[ABC_LENGTH];
        for (final int value : map) {
            if (value == EMPTY || used[value]) {
                return true;
            } else {
                used[value] = true;
            }
        }
        return false;
    }

    private static boolean allMatch(int[] map) {
        for (int i = 0; i < map.length; i++) {
            if (map[i] != i) {
                return false;
            }
        }
        return true;
    }

    private static int toInt(char ch) {
        return ch - 'a';
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new StringTransformsIntoAnotherString().canConvert("aabcc", "ccdee"));
        System.out.println("false == " + new StringTransformsIntoAnotherString().canConvert("leetcode", "codeleet"));
    }
}