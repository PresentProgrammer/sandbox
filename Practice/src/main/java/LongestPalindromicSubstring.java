import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Time complexity: O(n^2)
 * Space complexity: O(1).
 **/
public class LongestPalindromicSubstring {

    /**
     * Expand around center solution.
     */
    public String longestPalindrome(String s) {
        final int midIndex = (s.length() - 1);
        final int maxIndex = (s.length() - 1) * 2;
        String result = getLongestPalindrome(s, midIndex);
        int leftIndex = midIndex - 1;
        int rightIndex = midIndex + 1;
        while (leftIndex >= 0 && rightIndex <= maxIndex && result.length() < leftIndex + 1) {
            final String leftLongestPalindrome = getLongestPalindrome(s, leftIndex--);
            final String rightLongestPalindrome = getLongestPalindrome(s, rightIndex++);
            result = result.length() < leftLongestPalindrome.length() ? leftLongestPalindrome : result;
            result = result.length() < rightLongestPalindrome.length() ? rightLongestPalindrome : result;
        }
        return result;
    }

    private static String getLongestPalindrome(final String s, final int midIndex) {
        int leftRealIndex = midIndex % 2 == 0 ? midIndex / 2 - 1 : midIndex / 2;
        int rightRealIndex = midIndex / 2 + 1;
        while (leftRealIndex >= 0 && rightRealIndex < s.length() && s.charAt(leftRealIndex) == s.charAt(rightRealIndex)) {
            leftRealIndex--;
            rightRealIndex++;
        }
        return rightRealIndex - leftRealIndex == 1 ? "" : s.substring(leftRealIndex + 1, rightRealIndex);
    }

    // ========================== //

    /**
     * Try to optimize with Map, but not faster for given test cases on LeetCode.
     */
    public String longestPalindromeV2(String s) {
        final char[] chars = s.toCharArray();
        final Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            map.computeIfAbsent(chars[i], unused -> new ArrayList<>())
                    .add(i);
        }

        String longest = "";
        for (int left = 0; left < chars.length - longest.length(); left++) {
            for (final int right : map.get(chars[left])) {
                if (right - left + 1 < longest.length()) {
                    break;
                } else if (isPalindrome(chars, left, right)) {
                    longest = new String(chars, left, right - left + 1);
                }
            }
        }
        return longest;
    }

    private static boolean isPalindrome(char[] chars, int left, int rightIncl) {
        while (left < rightIncl) {
            if (chars[left++] != chars[rightIncl--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(final String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd"));
    }
}