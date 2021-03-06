import java.util.HashMap;
import java.util.Map;

/**
 * Problem #76
 * Time complexity: O(n + k), where n — s.length(), k — unique character count in t
 * Space complexity: O(k)
 **/
public class MinimumWindowSubstring {

    public String minWindow(final String s, final String t) {
        final Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            counts.put(t.charAt(i), counts.getOrDefault(t.charAt(i), 0) - 1);
        }
        int missingCounts = counts.size();

        int left = 0, right = 0;
        while (right < s.length() && missingCounts > 0) {
            final char charAtRight = s.charAt(right);
            final Integer charAtRightCount = counts.get(charAtRight);
            right++;
            if (charAtRightCount != null) {
                counts.put(charAtRight, charAtRightCount + 1);
                if (charAtRightCount + 1 == 0) {
                    missingCounts--;
                }
            }
        }
        if (missingCounts > 0) {
            return "";
        }

        int resultLeft = 0, resultRight = right;
        while (true) {
            final char charAtLeft = s.charAt(left);
            final Integer charAtLeftCount = counts.get(charAtLeft);
            if (charAtLeftCount == null || charAtLeftCount > 0) {
                if (charAtLeftCount != null) {
                    counts.put(charAtLeft, charAtLeftCount - 1);
                }
                left++;
                if (right - left < resultRight - resultLeft) {
                    resultLeft = left;
                    resultRight = right;
                }
            } else if (right < s.length()) {
                final char charAtRight = s.charAt(right);
                final Integer charAtRightCount = counts.get(charAtRight);
                if (charAtRightCount != null) {
                    counts.put(charAtRight, charAtRightCount + 1);
                }
                right++;
            } else {
                break;
            }
        }
        return s.substring(resultLeft, resultRight);
    }

    public static void main(final String[] args) {
        System.out.println("BANC == " + new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC"));
        System.out.println("BECODEBA == " + new MinimumWindowSubstring().minWindow("ADXOBECODEBANC", "ABCB"));
        System.out.println("(empty) == " + new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ZZ"));
    }
}