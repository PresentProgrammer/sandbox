import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class RegularExpressionMatching_LeetCode {

    public boolean isMatch(final String input, final String pattern) {
        return false;
    }

    /**
     * Problem #11
     * Time complexity: O(?)
     * Space complexity: O(n + m)
     **/
    public boolean isMatch_recursively(final String input, final String pattern) {
        return isMatch_recursively(asCharacterList(input), asCharacterList(pattern));
    }

    private static List<Character> asCharacterList(final String str) {
        final List<Character> list = new ArrayList<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i));
        }
        return list;
    }

    private static boolean isMatch_recursively(final List<Character> input, final List<Character> pattern) {
        if (pattern.isEmpty()) {
            return input.isEmpty();
        }
        boolean firstMatch = !input.isEmpty() && (input.get(0) == pattern.get(0) || pattern.get(0) == '.');
        if (pattern.size() > 1 && pattern.get(1) == '*') {
            return isMatch_recursively(input, pattern.subList(2, pattern.size())) ||
                    firstMatch && isMatch_recursively(input.subList(1, input.size()), pattern);
        } else {
            return firstMatch && isMatch_recursively(input.subList(1, input.size()), pattern.subList(1, pattern.size()));
        }
    }

    public static void main(final String[] args) {
        System.out.println("false - " + new RegularExpressionMatching_LeetCode().isMatch("aa", "a"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode().isMatch("aa", "a*"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode().isMatch("ab", ".*"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode().isMatch("aab", "c*a*b"));
        System.out.println("false - " + new RegularExpressionMatching_LeetCode().isMatch("mississippi", "mis*is*p*."));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode().isMatch("xfooxxxxxxfoo", ".*foo"));
    }
}