/**
 * Problem #91
 * Time complexity: O(2 ^ n)
 * Space complexity: O(n)
 **/
public class DecodeWays {

    private int result;
    private String s;

    public int numDecodings(final String s) {
		this.result = 0;
		this.s = s;
		numDecodings(0);
		return this.result;
    }

    private void numDecodings(final int left) {
        if (left == s.length()) {
            result++;
        } else {
            if (left + 1 < s.length() && (s.charAt(left) == '1'
                    || s.charAt(left) == '2' && '0' <= s.charAt(left + 1) && s.charAt(left + 1) <= '6')) {
                numDecodings(left + 2);
            }
            if (s.charAt(left) != '0') {
                numDecodings(left + 1);
            }
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("2 == " + new DecodeWays().numDecodings("12"));
        System.out.println("3 == " + new DecodeWays().numDecodings("226"));
        System.out.println("5 == " + new DecodeWays().numDecodings("2222"));
        System.out.println("0 == " + new DecodeWays().numDecodings("0"));
        System.out.println("0 == " + new DecodeWays().numDecodings("300"));
        System.out.println("1 == " + new DecodeWays().numDecodings("20"));
	}
}