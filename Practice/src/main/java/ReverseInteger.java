/**
 * Problem #7
 * Time complexity: O(log10(n))
 * Space complexity: O(1)
 **/
public class ReverseInteger {
    
    public int reverse(int x) {
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