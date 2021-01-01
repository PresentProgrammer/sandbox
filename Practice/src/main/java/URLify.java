/**
 * Problem #1.3
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class URLify {

    public void urlify(final char[] s, final int inSize) {
        int outSize = inSize;
        for (int i = 0; i < inSize; i++) {
            if (s[i] == ' ') {
                outSize += 2;
            }
        }
        int inP = inSize - 1, outP = outSize - 1;
        while (inP >= 0) {
            final char inChar = s[inP--];
            if (inChar == ' ') {
                s[outP--] = '0';
                s[outP--] = '2';
                s[outP--] = '%';
            } else {
                s[outP--] = inChar;
            }
        }
    }

    public static void main(final String[] args) {
        final char[] s = "Mr John Smith    ".toCharArray();
        new URLify().urlify(s, 13);
        System.out.println(new String(s));
    }
}