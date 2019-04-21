/**
 * Problem #345
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ReverseVowelsOfString {

    private static final boolean[] IS_VOVEL;

    static {
        IS_VOVEL = new boolean[123];
        IS_VOVEL['a'] = true;
        IS_VOVEL['e'] = true;
        IS_VOVEL['i'] = true;
        IS_VOVEL['o'] = true;
        IS_VOVEL['u'] = true;
        IS_VOVEL['A'] = true;
        IS_VOVEL['E'] = true;
        IS_VOVEL['I'] = true;
        IS_VOVEL['O'] = true;
        IS_VOVEL['U'] = true;
    }

    public String reverseVowels(String s) {
		final StringBuilder builder = new StringBuilder(s.length());
		int right = s.length() - 1;
		for (int i = 0; i < s.length(); i++) {
            final char currChar = s.charAt(i);
            if (IS_VOVEL[currChar]) {
                while (!IS_VOVEL[s.charAt(right)]) {
                    right--;
                }
                builder.append(s.charAt(right));
                right--;
            } else {
                builder.append(currChar);
            }
        }
		return builder.toString();
    }
    
    public static void main(final String[] args) {
        System.out.println("hello == " + new ReverseVowelsOfString().reverseVowels("holle"));
        System.out.println("leetcode == " + new ReverseVowelsOfString().reverseVowels("leotcede"));
	}
}