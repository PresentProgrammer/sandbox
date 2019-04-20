import java.util.ArrayList;
import java.util.List;

/**
 * Problem #131
 * Time complexity: O(n^3)
 * Space complexity: O(n)
 **/
public class PalindromePartitioning {

    private List<List<String>> result;

    public List<List<String>> partition(final String s) {
        this.result = new ArrayList<>();
        partition(s, new ArrayList<>(s.length()), 0);
        return result;
    }

    private void partition(final String s, final List<String> temp, final int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int midExcl = s.length(); midExcl > start; midExcl--) {
                if (isPalindrome(s, start, midExcl)) {
                    temp.add(s.substring(start, midExcl));
                    partition(s, temp, midExcl);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    private static boolean isPalindrome(final String s, final int start, final int endExcl) {
        int left = start, right = endExcl - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        return left >= right;
    }

    public static void main(final String[] args) {
        System.out.println("[\"aa\",\"b\"], " + "[\"a\",\"a\",\"b\"] == " + new PalindromePartitioning().partition("aab"));
    }
}