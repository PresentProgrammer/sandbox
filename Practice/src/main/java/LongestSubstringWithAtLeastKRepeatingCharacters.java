/**
 * Problem #395
 * Time complexity: O()
 * Space complexity: O()
 **/
public class LongestSubstringWithAtLeastKRepeatingCharacters {

    private String s;
    private int k;

    public int longestSubstring(final String s, final int k) {
        this.s = s;
        this.k = k;
        return longestSubstring(0, s.length());
    }

    private int longestSubstring(final int left, final int rightExcl) {
        final int[] counts = new int[26];
        for (int i = left; i < rightExcl; i++) {
            counts[s.charAt(i) - 'a']++;
        }

        int max = 0;
        boolean began = false;
        int beginIndex = -1;
        int i = left;
        while (i < rightExcl) {
            final int iCount = counts[s.charAt(i) - 'a'];
            if (iCount >= k && !began) {
                began = true;
                beginIndex = i;
            } else if (iCount < k && began) {
                max = Math.max(max, longestSubstring(beginIndex, i));
                began = false;
            }
            i++;
        }
        if (began) {
            if (beginIndex == left) {
                return rightExcl - left;
            } else {
                max = Math.max(max, longestSubstring(beginIndex, i));
            }
        }
        return max;
    }

    public static void main(final String[] args) {
        System.out.println("3 == " + new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring("aaabb", 3));
        System.out.println("5 == " + new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring("ababbc", 2));
    }
}