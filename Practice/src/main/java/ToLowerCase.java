/**
 * Problem #709
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ToLowerCase {

    private static final char[] CHAR_MAP;

    static {
        CHAR_MAP = new char[256];
        for (char i = 0; i < 256; i++) {
            CHAR_MAP[i] = i;
        }
        for (int i = 65; i < 91; i++) {
            CHAR_MAP[i] = (char) (i + 32);
        }
    }

    public String toLowerCase(String str) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            builder.append(CHAR_MAP[str.charAt(i)]);
        }
        return builder.toString();
    }

    public static void main(final String[] args) {
        System.out.println("hello == " + new ToLowerCase().toLowerCase("Hello"));
        System.out.println("here == " + new ToLowerCase().toLowerCase("here"));
        System.out.println("LOVELY == " + new ToLowerCase().toLowerCase("LOVELY"));
    }
}