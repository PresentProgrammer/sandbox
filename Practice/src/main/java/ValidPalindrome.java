import java.util.ArrayList;
import java.util.List;

/**
 * Problem #125
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
		final List<Character> chars = new ArrayList<>(s.length());
		for (int i = 0; i < s.length(); i++) {
            final char curr = s.charAt(i);
            if (Character.isAlphabetic(curr) || Character.isDigit(curr)) {
                chars.add(Character.toLowerCase(curr));
            }
        }

		int left = 0, right = chars.size() - 1;
		while (left < right && chars.get(left) == chars.get(right)) {
		    left++;
		    right--;
        }
		return left >= right;
    }
    
    public static void main(final String[] args) {
        System.out.println("true == " + new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("false == " + new ValidPalindrome().isPalindrome("race a car"));
	}
}