import java.util.ArrayList;
import java.util.List;

/**
 * Problem #131
 * Time complexity: O(2 ^ N * N) — 2 ^ N vertices * N to check if palindrome
 * Space complexity: O(N)
 **/
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        final List<List<String>> result = new ArrayList<>();
        partition(s, new ArrayList<>(), result, 0);
        return result;
    }

    private static void partition(String s, List<String> temp, List<List<String>> result, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int end = start; end < s.length(); end++) {
                if (isPalindrome(s, start, end)) {
                    temp.add(s.substring(start, end + 1));
                    partition(s, temp, result, end + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(final String[] args) {
        System.out.println("[[aa, b], [a, a, b]]== " + new PalindromePartitioning().partition("aab"));
    }
}