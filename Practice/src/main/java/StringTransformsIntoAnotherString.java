import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Problem #1153
 * Time complexity: O(N)
 * Space complexity: O(1)
 **/
public class StringTransformsIntoAnotherString {

    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) {
            return true;
        }

        final int[] map = new int['z' - 'a' + 1];
        Arrays.fill(map, -1);
        for (int i = 0; i < str1.length(); i++) {
            final int char1 = str1.charAt(i) - 'a';
            final int char2 = str2.charAt(i) - 'a';
            if (map[char1] >= 0 && map[char1] != char2) {
                return false;
            } else {
                map[char1] = char2;
            }
        }
        return isFreeCharacterInValues(map);
    }

    private static boolean isFreeCharacterInValues(int[] map) {
        return IntStream.of(map).sum() != arithmeticProgressionSum();
    }

    private static int arithmeticProgressionSum() {
        return ('z' - 'a') * ('z' - 'a' + 1) / 2;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new StringTransformsIntoAnotherString().canConvert("aabcc", "ccdee"));
        System.out.println("false == " + new StringTransformsIntoAnotherString().canConvert("leetcode", "codeleet"));
    }
}