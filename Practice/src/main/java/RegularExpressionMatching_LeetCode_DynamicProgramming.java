/**
 * Problem #11
 * Time complexity: O(nm)
 * Space complexity: O(nm)
 **/
@SuppressWarnings("Duplicates")
public class RegularExpressionMatching_LeetCode_DynamicProgramming {

    private Boolean[][] memo;
    private String input;
    private String pattern;

    public boolean isMatch(final String input, final String pattern) {
        this.memo = new Boolean[input.length() + 1][pattern.length() + 1];
        this.input = input;
        this.pattern = pattern;
        return cachedIsMatch(0, 0);
    }

    private boolean cachedIsMatch(final int i, final int j) {
        if (memo[i][j] == null) {
            memo[i][j] = isMatch(i, j);
        }
        return memo[i][j];
    }

    private boolean isMatch(final int i, final int j) {
        if (j == pattern.length()) {
            return i == input.length();
        }
        boolean firstMatch = i < input.length() && (pattern.charAt(j) == input.charAt(i) || pattern.charAt(j) == '.');
        if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
            return cachedIsMatch(i, j + 2) || firstMatch && cachedIsMatch(i + 1, j);
        } else {
            return firstMatch && cachedIsMatch(i + 1, j + 1);
        }
    }

    public static void main(final String[] args) {
        System.out.println("false - " + new RegularExpressionMatching_LeetCode_DynamicProgramming().isMatch("aa", "a"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode_DynamicProgramming().isMatch("aa", "a*"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode_DynamicProgramming().isMatch("ab", ".*"));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode_DynamicProgramming().isMatch("aab", "c*a*b"));
        System.out.println("false - " + new RegularExpressionMatching_LeetCode_DynamicProgramming().isMatch("mississippi", "mis*is*p*."));
        System.out.println("true - " + new RegularExpressionMatching_LeetCode_DynamicProgramming().isMatch("xfooxxxxxxfoo", ".*foo"));
    }
}