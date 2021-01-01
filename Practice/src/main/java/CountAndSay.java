/**
 * Problem #38
 * Time complexity: O(?)
 * Space complexity: O(?)
 **/
public class CountAndSay {

    public String countAndSay(int n) {
        return n == 1 ? "1" : transform(countAndSay(n - 1));
    }

    private static String transform(final String n) {
        final StringBuilder stringBuilder = new StringBuilder();
        int pos = 0;
        while (pos < n.length()) {
            final char number = n.charAt(pos);
            pos++;
            int count = 1;
            while (pos < n.length() && n.charAt(pos) == number) {
                count++;
                pos++;
            }
            stringBuilder
                    .append(count)
                    .append(number);
        }
        return stringBuilder.toString();
    }

    public static void main(final String[] args) {
        System.out.println("1 == " + new CountAndSay().countAndSay(1));
        System.out.println("11 == " + new CountAndSay().countAndSay(2));
        System.out.println("21 == " + new CountAndSay().countAndSay(3));
        System.out.println("1211 == " + new CountAndSay().countAndSay(4));
        System.out.println("? == " + new CountAndSay().countAndSay(30));
    }
}