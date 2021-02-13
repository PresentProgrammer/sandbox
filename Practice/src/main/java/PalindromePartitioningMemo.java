import java.util.ArrayList;
import java.util.List;

/**
 * Problem #131
 * Time complexity: O(2 ^ N + N ^ 2) — 2 ^ N vertices, N ^ 2 to build palindrome memo
 * Space complexity: O(N)
 **/
public class PalindromePartitioningMemo {

    public List<List<String>> partition(String s) {
        final List<List<String>> result = new ArrayList<>();
        partition(s, new ArrayList<>(), result, 0, buildPalindromeMemo(s));
        return result;
    }

    private static void partition(String s, List<String> temp, List<List<String>> result, int start, boolean[][] memo) {
        if (start == s.length()) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int end = start; end < s.length(); end++) {
                if (memo[start][end]) {
                    temp.add(s.substring(start, end + 1));
                    partition(s, temp, result, end + 1, memo);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    private static boolean[][] buildPalindromeMemo(String str) {
        final char[] s = str.toCharArray();
        final boolean[][] memo = new boolean[s.length][s.length];
        int leftMid = 0;
        int rightMid = 0;
        while (rightMid < s.length) {
            int left = leftMid;
            int right = rightMid;
            while (0 <= left && right < s.length && s[left] == s[right]) {
                memo[left][right] = true;
                left--;
                right++;
            }

            if (leftMid == rightMid) {
                rightMid++;
            } else {
                leftMid++;
            }
        }
        return memo;
    }

    public static void main(final String[] args) {
        System.out.println("[[aa, b], [a, a, b]]== " + new PalindromePartitioningMemo().partition("aab"));
    }
}