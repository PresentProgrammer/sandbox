/**
 * Problem #8
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class StringToInteger {

    private static class StringParser {

        private final String str;
        private int sign;
        private int index = 0;

        private StringParser(final String str) {
            this.str = str;
        }

        private int parseToInt() {
            findFirstNonWhitespaceIndex();
            determineSign();
            return getResult();
        }

        private void findFirstNonWhitespaceIndex() {
            while (index < str.length() && str.charAt(index) == ' ') {
                index++;
            }
        }

        private void determineSign() {
            if (index < str.length()) {
                if (str.charAt(index) == '-') {
                    sign = -1;
                    index++;
                } else if (str.charAt(index) == '+') {
                    sign = 1;
                    index++;
                } else {
                    sign = 1;
                }
            }
        }

        private int getResult() {
            try {
                int result = 0;
                while (index < str.length() && Character.isDigit(str.charAt(index))) {
                    result = Math.addExact(Math.multiplyExact(result, 10), Character.getNumericValue(str.charAt(index)));
                    index++;
                }
                return result * sign;
            } catch (ArithmeticException overflowHappened) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }
    }

    public int myAtoi(final String str) {
        return new StringParser(str).parseToInt();
    }

    public static void main(final String[] args) {
        System.out.println(new StringToInteger().myAtoi("42") + " should be 42");
        System.out.println(new StringToInteger().myAtoi("   -42") + " should be -42");
        System.out.println(new StringToInteger().myAtoi("4193 with words") + " should be 4193");
        System.out.println(new StringToInteger().myAtoi("words and 987") + " should be 0");
        System.out.println(new StringToInteger().myAtoi("-91283472332") + " should be -2147483648");
    }
}