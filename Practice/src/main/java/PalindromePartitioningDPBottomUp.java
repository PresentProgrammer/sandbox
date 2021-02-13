import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem #131, DP solution
 * Time complexity: O(N ^ 3 + 2 ^ N) — N ^ 3 for palindrome checking, 2 ^ N for creation of lists
 * Space complexity: O(N * 2 ^ N)
 **/
public class PalindromePartitioningDPBottomUp {

    private static final List<List<String>> DP_BORDER = List.of(List.of());

    public List<List<String>> partition(String str) {
        final char[] s = str.toCharArray();
        final List<List<List<String>>> dp = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            final List<List<String>> subResult = new ArrayList<>();
            for (int start = i; start >= 0; start--) {
                if (isPalindrome(s, start, i)) {
                    final String curr = new String(s, start, i - start + 1);
                    for (List<String> dpElem : getFromDp(dp, start - 1)) {
                        subResult.add(withAddedElement(dpElem, curr));
                    }
                }
            }
            dp.add(Collections.unmodifiableList(subResult));
        }
        return getFromDp(dp, s.length - 1);
    }

    private static List<List<String>> getFromDp(List<List<List<String>>> dp, int i) {
        return i >= 0 ? dp.get(i) : DP_BORDER;
    }

    private static List<String> withAddedElement(List<String> list, String elem) {
        final List<String> newList = new ArrayList<>(list);
        newList.add(elem);
        return Collections.unmodifiableList(newList);
    }

    private static boolean isPalindrome(char[] s, int start, int end) {
        int left = start;
        int right = end;
        while (left < right) {
            if (s[left++] != s[right--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(final String[] args) {
        System.out.println("[[a, a, b], [aa, b]] == " + new PalindromePartitioningDPBottomUp().partition("aab"));
    }
}