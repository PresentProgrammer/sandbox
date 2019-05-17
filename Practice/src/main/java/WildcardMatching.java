/**
 * Problem #44
 * Time complexity: O(n * k)
 * Space complexity: O(n * k)
 **/
public class WildcardMatching {

    private String str;
    private String pattern;
    private Boolean[][] memo;

    public boolean isMatch(String str, String pattern) {
		this.str = str;
		this.pattern = pattern;
		this.memo = new Boolean[str.length() + 1][pattern.length()];
		return isMatch(0, 0);
    }

    private boolean isMatch(final int i, final int j) {
        if (j == pattern.length()) {
            return i == str.length();
        } else if (memo[i][j] != null) {
            return memo[i][j];
        } else {
            final boolean isMatch;
            if (pattern.charAt(j) == '*') {
                int k = str.length();
                while (k >= i && !isMatch(k, j + 1)) {
                    k--;
                }
                isMatch = k >= i;
            } else if (i == str.length()) {
                isMatch = false;
            } else if (pattern.charAt(j) == '?' || pattern.charAt(j) == str.charAt(i)) {
                isMatch = isMatch(i + 1, j + 1);
            } else {
                isMatch = false;
            }
            memo[i][j] = isMatch;
            return isMatch;
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("false == " + new WildcardMatching().isMatch("aa", "a"));
        System.out.println("true == " + new WildcardMatching().isMatch("aa", "*"));
        System.out.println("false == " + new WildcardMatching().isMatch("cb", "*a"));
        System.out.println("true == " + new WildcardMatching().isMatch("adceb", "a*b"));
        System.out.println("false == " + new WildcardMatching().isMatch("acdcb", "a*c?b"));
        System.out.println("true == " + new WildcardMatching().isMatch("", "*"));
	}
}