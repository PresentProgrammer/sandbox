/**
 * Problem #7
 * Time complexity: O(log10(n))
 * Space complexity: O(1)
 **/
public class ReverseInteger {

    private static final int MIN_OVERFLOW_BORDER = Integer.MIN_VALUE / 10;
    private static final int MAX_OVERFLOW_BORDER = Integer.MAX_VALUE / 10;

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int pop = x % 10;
            if (result < MIN_OVERFLOW_BORDER || result == MIN_OVERFLOW_BORDER && pop < -8
                    || result > MAX_OVERFLOW_BORDER || result == MAX_OVERFLOW_BORDER && pop > 7) {
                return 0;
            }
            result = result * 10 + pop;
            x /= 10;
        }
        return result;
    }

    public int reverse_usingJava8ExactAddAndMultiply(int x) {
        try {
            int result = 0;
            while (x != 0) {
                result = Math.addExact(Math.multiplyExact(result, 10), x % 10);
                x /= 10;
            }
            return result;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    public int reverse_checkOverflowByDoingReverseOperations(int x) {
        int result = 0;
        while (x != 0) {
            int nextDigit = x % 10;
            int nextResult = result * 10 + nextDigit;
            if ((nextResult - nextDigit) / 10 == result) {
                result = nextResult;
                x /= 10;
            } else {
                return 0;
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println(new ReverseInteger().reverse(123));
        System.out.println(new ReverseInteger().reverse(-123));
        System.out.println(new ReverseInteger().reverse(120));
        System.out.println(new ReverseInteger().reverse(Integer.MAX_VALUE - 10));
        System.out.println(new ReverseInteger().reverse(Integer.MIN_VALUE + 10));
        System.out.println(new ReverseInteger().reverse(1534236469));
    }
}