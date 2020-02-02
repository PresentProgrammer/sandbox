import java.util.ArrayList;
import java.util.List;

/**
 * Problem #271
 * Time complexity: O(N + M), where N is sum of str.length(), M is number of strs.
 * Space complexity: O(N + M)
 **/
public class EncodeAndDecodeStrings {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        final StringBuilder builder = new StringBuilder();
        for (final String str : strs) {
            builder
                    .append(formatNum(str.length()))
                    .append(str);
        }
        return builder.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        final List<String> res = new ArrayList<>(parseNum(s, 0));
        int currStart = 2;
        while (currStart < s.length()) {
            final int currStrLength = parseNum(s, currStart);
            currStart += 2;
            res.add(s.substring(currStart, currStart + currStrLength));
            currStart += currStrLength;
        }
        return res;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    private static String formatNum(int num) {
        return new StringBuilder()
                .append((char) (num >> 16))
                .append((char) (num))
                .toString();
    }

    private static int parseNum(String str, int start) {
        final char[] chars = str.substring(start, start + 2).toCharArray();
        return chars[0] << 16 | (chars[1] & 0xFFFF);
    }

    public static void main(final String[] args) {
        System.out.println(formatNum(10).length());
        System.out.println(formatNum(1000).length());
        System.out.println(formatNum(Integer.MAX_VALUE).length());
        System.out.println(parseNum(formatNum(10), 0));
        System.out.println(parseNum(formatNum(1000), 0));
        System.out.println(parseNum(formatNum(Integer.MAX_VALUE), 0));
	}
}