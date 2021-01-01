import java.util.Arrays;

/**
 * Problem #91
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class DecodeWays {

    private String s;
    private int[] waysFrom;

    public int numDecodings(final String s) {
        this.s = s;
        this.waysFrom = new int[s.length()];
        Arrays.fill(this.waysFrom, -1);
        return numDecodings(0);
    }

    private int numDecodings(final int left) {
        if (left == s.length()) {
            return 1;
        } else if (waysFrom[left] != -1) {
            return waysFrom[left];
        } else {
            int ways = 0;
            if (left + 1 < s.length() && (s.charAt(left) == '1'
                    || s.charAt(left) == '2' && '0' <= s.charAt(left + 1) && s.charAt(left + 1) <= '6')) {
                ways += numDecodings(left + 2);
            }
            if (s.charAt(left) != '0') {
                ways += numDecodings(left + 1);
            }
            waysFrom[left] = ways;
            return ways;
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