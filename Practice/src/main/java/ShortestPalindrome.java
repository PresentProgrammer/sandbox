/**
 * Problem #214
 * Time complexity: O(N) - average, O(N ^ 2) - worst
 * Space complexity: O(1)
 **/
public class ShortestPalindrome {

    public String shortestPalindrome(String s) {
        final int rightBorder = new PalindromeSearch(s).getPalindromeRightBorder();
        return new StringBuilder()
                .append(s.substring(rightBorder + 1))
                .reverse()
                .append(s)
                .toString();
    }

    private static class PalindromeSearch {

        private final int[] left = new int['z' - 'a' + 1];
        private final int[] right = new int['z' - 'a' + 1];
        private final char[] s;

        private int rightBorder;

        PalindromeSearch(String s) {
            this.s = s.toCharArray();
            final int mid = this.s.length / 2;
            for (int i = 0; i < mid; i++) {
                this.left[this.s[i] - 'a']++;
            }
            for (int i = mid + (this.s.length % 2); i < this.s.length; i++) {
                this.right[this.s[i] - 'a']++;
            }
            this.rightBorder = this.s.length - 1;
        }

        int getPalindromeRightBorder() {
            while (!isCurrentPalindrome()) {
                decrementRightBorder();
            }
            return rightBorder;
        }

        private boolean isCurrentPalindrome() {
            return isWorthChecking() && isPalindrome();
        }

        private boolean isWorthChecking() {
            for (int i = 0; i < left.length; i++) {
                if (left[i] != right[i]) {
                    return false;
                }
            }
            return true;
        }

        private boolean isPalindrome() {
            int left = 0;
            int right = rightBorder;
            while (left < right) {
                if (s[left++] != s[right--]) {
                    return false;
                }
            }
            return true;
        }

        private void decrementRightBorder() {
            right[s[rightBorder] - 'a']--;

            final int mid = rightBorder / 2;
            if (rightBorder % 2 == 0) {
                right[s[mid] - 'a']++;
            } else {
                left[s[mid] - 'a']--;
            }

            rightBorder--;
        }
    }

    public static void main(final String[] args) {
        System.out.println(" == " + new ShortestPalindrome().shortestPalindrome(""));
        System.out.println("dcbabcd == " + new ShortestPalindrome().shortestPalindrome("abcd"));
        System.out.println("aaacecaaa == " + new ShortestPalindrome().shortestPalindrome("aacecaaa"));
    }
}