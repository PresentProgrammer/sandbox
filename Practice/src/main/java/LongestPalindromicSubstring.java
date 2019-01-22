/**
 * Time complexity: O(n^2)
 * Space complexity: O(1).
 **/
public class LongestPalindromicSubstring {
    
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
    
    public static void main(final String[] args) {
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("babad"));
        System.out.println(new LongestPalindromicSubstring().longestPalindrome("cbbd"));
    }
}