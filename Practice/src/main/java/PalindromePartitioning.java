import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem #131
 * Time complexity: O(n ^ 3)
 * Space complexity: O(n)
 **/
public class PalindromePartitioning {

    private List<List<String>> result;
    private Map<Pair<Integer, Integer>, Boolean> isPalindromeCache;

    public List<List<String>> partition(final String s) {
        this.isPalindromeCache = new HashMap<>();
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

    private boolean isPalindrome(final String s, final int start, final int endExcl) {
        if (endExcl - start <= 1) {
            return true;
        } else {
            final Pair<Integer, Integer> beginEnd = new Pair<>(start, endExcl);
            final Boolean cachedIsPalindrome = isPalindromeCache.get(beginEnd);
            if (cachedIsPalindrome == null) {
                final boolean calculatedPalindrome =isPalindrome(s, beginEnd);
                isPalindromeCache.put(beginEnd, calculatedPalindrome);
                return calculatedPalindrome;
            } else {
                return cachedIsPalindrome;
            }
        }
    }

    private static boolean isPalindrome(final String s, final Pair<Integer, Integer> beginEnd) {
        int left = beginEnd.getKey(), right = beginEnd.getValue() - 1;
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