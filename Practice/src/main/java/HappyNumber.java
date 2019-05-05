import java.util.HashSet;
import java.util.Set;

/**
 * Problem #202
 * Time complexity: O(?)
 * Space complexity: O(?)
 **/
public class HappyNumber {

    public boolean isHappy(int n) {
        if (n < 1) {
            return false;
        }
		final Set<Integer> seen = new HashSet<>();
		while (n != 1) {
		    if (seen.contains(n)) {
		        return false;
            } else {
		        seen.add(n);
            }
		    int digitSquareSum = 0;
		    int rem = n;
		    while (rem > 0) {
		        final int digit = rem % 10;
		        digitSquareSum += digit * digit;
		        rem /= 10;
            }
		    n = digitSquareSum;
        }
		return true;
    }
    
    public static void main(final String[] args) {
        System.out.println("true == " + new HappyNumber().isHappy(19));
	}
}