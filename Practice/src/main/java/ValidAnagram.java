/**
 * Problem #242
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        final int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            final int index = t.charAt(i) - 'a';
            count[index]--;
            if (count[index] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new ValidAnagram().isAnagram("anagram", "nagaram"));
        System.out.println("false == " + new ValidAnagram().isAnagram("rat", "car"));
    }
}