/**
 * Problem #29
 * Time complexity: O(log(divisor) dividend)
 * Space complexity: O(log(divisor) dividend)
 **/
public class DivideTwoIntegers {

    private int result;
    private boolean isPositive;
    private int rem;
    private int absDivisor;

    public int divide(int dividend, int divisor) {
        if (initializeState(dividend, divisor)) {
            return result;
        }
        calculateResult(this.absDivisor, 1);
        return isPositive ? result : -result;
    }

    /**
     * return true if should return result immediately, without applying `isPositive`.
     */
    private boolean initializeState(int dividend, int divisor) {
        if (divisor == Integer.MIN_VALUE) {
            this.result = dividend == Integer.MIN_VALUE ? 1 : 0;
            return true;
        } else if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                this.result = Integer.MAX_VALUE;
                return true;
            } else {
                this.result++;
                this.rem = Math.abs(dividend + Math.abs(divisor));
            }
        } else {
            this.rem = Math.abs(dividend);
        }
        this.absDivisor = Math.abs(divisor);
        this.isPositive = (dividend < 0 && divisor < 0) || (dividend >= 0 && divisor > 0);
        return false;
    }

    private void calculateResult(int currDivisor, int multiple) {
        if (currDivisor > 0 && currDivisor <= rem) {
            calculateResult(currDivisor + currDivisor, multiple + multiple);
            if (currDivisor <= rem) {
                result += multiple;
                rem -= currDivisor;
            }
        }
    }
}