import java.util.HashMap;
import java.util.Map;

/**
 * Problem #471
 * Time complexity: O(N ^ 4) — N ^ 2 possible string to consider * N ^ 2 times each one is processed.
 * Space complexity: O(N ^ 3) — N ^ 2 possible keys in memo * N length of key.
 **/
public class EncodeStringWithShortestLength {

    private static final int MIN_LENGTH_TO_TRANSFORM = 5;

    public String encode(String s) {
        return encode(s, new HashMap<>());
    }

    public String encode(String s, Map<String, String> memo) {
        if (s.length() < MIN_LENGTH_TO_TRANSFORM) {
            return s;
        } else if (!memo.containsKey(s)) {
            String minStr = s.charAt(0) + encode(s.substring(1), memo);

            stepLabel:
            for (int step = 1; step <= s.length() / 2; step++) {
                for (int times = 1; times < s.length() / step; times++) {
                    for (int i = 0; i < step; i++) {
                        if (s.charAt(i) != s.charAt(step * times + i)) {
                            continue stepLabel;
                        }
                    }
                    final String candidate = (times + 1) + "[" + encode(s.substring(0, step), memo) + "]"
                            + encode(s.substring(step * (times + 1)), memo);
                    if (candidate.length() < minStr.length()) {
                        minStr = candidate;
                    }
                }
            }
            memo.put(s, minStr);
        }
        return memo.get(s);
    }

    public static void main(final String[] args) {
        System.out.println("? == " + new EncodeStringWithShortestLength()
                .encode("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }
}