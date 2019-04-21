/**
 * Problem #125
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
		int left = 0, right = s.length() - 1;
		while (left < right) {
		    char leftChar = s.charAt(left);
		    while (!Character.isLetterOrDigit(leftChar) && left + 1 < s.length()) {
		        left++;
		        leftChar = s.charAt(left);
            }
		    if (left >= right) {
		        return true;
            }
            char rightChar = s.charAt(right);
            while (!Character.isLetterOrDigit(rightChar) && right - 1 >= 0) {
                right--;
                rightChar = s.charAt(right);
            }
            if (left >= right) {
                return true;
            }
            if (Character.toLowerCase(leftChar) == Character.toLowerCase(rightChar)) {
		        left++;
		        right--;
            } else {
                return false;
            }
        }
		return true;
    }
    
    public static void main(final String[] args) {
        System.out.println("true == " + new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("false == " + new ValidPalindrome().isPalindrome("race a car"));
	}
}