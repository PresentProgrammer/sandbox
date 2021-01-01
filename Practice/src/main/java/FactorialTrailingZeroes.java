/**
 * Problem #172
 * Time complexity: O(log n)
 * Space complexity: O(1)
 **/
public class FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        int result = 0;
        while (n > 0) {
            n /= 5;
            result += n;
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println("7 == " + new FactorialTrailingZeroes().trailingZeroes(30));
    }
}