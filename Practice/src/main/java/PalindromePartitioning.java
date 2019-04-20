import javafx.util.Pair;

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
        partition(s, new ArrayList<>(s.length()), 0, s.length());
        return result;
    }

    private void partition(final String s, final List<Pair<Integer, Integer>> temp, final int start, final int endExcl) {
        if (start == endExcl) {
            final List<String> resultElem = new ArrayList<>();
            for (final Pair<Integer, Integer> t : temp) {
                resultElem.add(s.substring(t.getKey(), t.getValue()));
            }
            result.add(resultElem);
        } else {
            for (int midExcl = endExcl; midExcl > start; midExcl--) {
                if (isPalindrome(s, start, midExcl)) {
                    temp.add(new Pair<>(start, midExcl));
                    partition(s, temp, midExcl, endExcl);
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