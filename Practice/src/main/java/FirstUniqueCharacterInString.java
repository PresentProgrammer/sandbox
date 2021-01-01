/**
 * Problem #387
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class FirstUniqueCharacterInString {

    public int firstUniqChar(String s) {
        final int[] uniqueInd = new int[26];
        for (int i = 0; i < s.length(); i++) {
            final int curr = s.charAt(i) - 'a';
            if (uniqueInd[curr] == 0) {
                uniqueInd[curr] = i + 1;
            } else {
                uniqueInd[curr] = -1;
            }
        }
        int result = Integer.MAX_VALUE;
        for (final int ind : uniqueInd) {
            if (ind > 0) {
                result = Math.min(result, ind);
            }
        }
        return result < Integer.MAX_VALUE ? result - 1 : -1;
    }

    public static void main(final String[] args) {
        System.out.println("0 == " + new FirstUniqueCharacterInString().firstUniqChar("leetcode"));
        System.out.println("2 == " + new FirstUniqueCharacterInString().firstUniqChar("loveleetcode"));
    }
}