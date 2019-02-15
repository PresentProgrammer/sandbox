import java.util.ArrayList;
import java.util.List;

/**
 * Problem #11
 * Time complexity: O(?)
 * Space complexity: O(n + m)
 **/
@SuppressWarnings("Duplicates")
public class RegularExpressionMatching_LeetCode_Recursively {

    public boolean isMatch(final String input, final String pattern) {
        return isMatch(asCharacterList(input), asCharacterList(pattern));
    }

    private static List<Character> asCharacterList(final String str) {
        final List<Character> list = new ArrayList<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i));
        }
        return list;
    }

    private static boolean isMatch(final List<Character> input, final List<Character> pattern) {
        if (pattern.isEmpty()) {
            return input.isEmpty();
        }
        boolean firstMatch = !input.isEmpty() && (input.get(0) == pattern.get(0) || pattern.get(0) == '.');
        if (pattern.size() > 1 && pattern.get(1) == '*') {
            return isMatch(input, pattern.subList(2, pattern.size())) ||
                    firstMatch && isMatch(input.subList(1, input.size()), pattern);
        } else {
            return firstMatch && isMatch(input.subList(1, input.size()), pattern.subList(1, pattern.size()));
        }
    }

    public static void main(final String[] args) {
        System.out.println("false - " + new RegularExpressionMatching_LeetCode_Recursively().isMatch("aa", "a"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode_Recursively().isMatch("aa", "a*"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode_Recursively().isMatch("ab", ".*"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode_Recursively().isMatch("aab", "c*a*b"));
        System.out.println("false - " + new RegularExpressionMatching_LeetCode_Recursively().isMatch("mississippi", "mis*is*p*."));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode_Recursively().isMatch("xfooxxxxxxfoo", ".*foo"));
    }
}