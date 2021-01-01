import java.util.HashMap;
import java.util.Map;

/**
 * Problem #166
 * Time complexity: O(denominator)
 * Space complexity: O(denominator)
 **/
public class FractionToRecurringDecimal {

    public String fractionToDecimal(int signedNumerator, int signedDenominator) {
        final StringBuilder builder = new StringBuilder();
        final Map<Long, Integer> remSeen = new HashMap<>();
        if (signedNumerator > 0 && signedDenominator < 0 || signedNumerator < 0 && signedDenominator > 0) {
            builder.append('-');
        }
        final long numerator = Math.abs((long) signedNumerator);
        final long denominator = Math.abs((long) signedDenominator);
        builder.append(numerator / denominator);
        long rem = numerator % denominator;
        if (rem != 0) {
            builder.append('.');
        }
        while (rem != 0 && !remSeen.containsKey(rem)) {
            remSeen.put(rem, builder.length());
            final long nextNumerator = rem * 10;
            builder.append(nextNumerator / denominator);
            rem = nextNumerator % denominator;
        }
        if (rem != 0) {
            builder.insert(remSeen.get(rem).intValue(), '(');
            builder.append(')');
        }
        return builder.toString();
    }

    public static void main(final String[] args) {
        System.out.println("0.5 == " + new FractionToRecurringDecimal().fractionToDecimal(1, 2));
        System.out.println("2 == " + new FractionToRecurringDecimal().fractionToDecimal(2, 1));
        System.out.println("0.(6) == " + new FractionToRecurringDecimal().fractionToDecimal(2, 3));
        System.out.println("1.(1764705882352941) == " + new FractionToRecurringDecimal().fractionToDecimal(20, 17));
        System.out.println("-6.25 == " + new FractionToRecurringDecimal().fractionToDecimal(-50, 8));
        System.out.println("-0.58(3) == " + new FractionToRecurringDecimal().fractionToDecimal(7, -12));
        System.out.println("0.0000000004656612873077392578125 == " + new FractionToRecurringDecimal().fractionToDecimal(-1, -2147483648));
        System.out.println("5.8(144) == " + new FractionToRecurringDecimal().fractionToDecimal(3227, 555));
    }
}