/**
 * Problem #242
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        final int[] count = new int[26];
		for (int i = 0; i < s.length(); i++) {
		    count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i) - 'a']--;
        }
        for (final int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(final String[] args) {
        System.out.println("true == " + new ValidAnagram().isAnagram("anagram","nagaram"));
        System.out.println("false == " + new ValidAnagram().isAnagram("rat","car"));
	}
}