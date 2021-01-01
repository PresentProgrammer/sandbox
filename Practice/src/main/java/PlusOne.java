/**
 * Problem #66
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class PlusOne {

    public int[] plusOne(int[] digits) {
        int currDigit = digits.length - 1;
        boolean nextSign;
        do {
            if (digits[currDigit] == 9) {
                digits[currDigit] = 0;
                nextSign = true;
                currDigit--;
            } else {
                digits[currDigit] += 1;
                nextSign = false;
            }
        } while (currDigit >= 0 && nextSign);
        return nextSign ? oneMoreDigitNumber(digits) : digits;
    }

    private static int[] oneMoreDigitNumber(final int[] digits) {
        final int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public static void main(final String[] args) {
    }
}