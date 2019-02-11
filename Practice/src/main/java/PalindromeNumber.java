import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #9
 **/
public class PalindromeNumber {

    /**
     * Time complexity: O(lg(n))
     * Space complexity: O(1)
     */
    public boolean isPalindrome(final int original) {
        if (original == 0) {
			return true;
		} else if (original < 0 || original % 10 == 0) {
			return false;
		}
		int x = original;
		int reversed = 0;
		while (x > reversed) {
			reversed = reversed * 10 + x % 10;
			x /= 10;
		}
		return reversed == x || reversed / 10 == x;
    }

    /**
     * Time complexity: O(lg(n))
     * Space complexity: O(1)
     */
    public boolean isPalindrome_extendingToLongToAvoidOverflow(final int original) {
		if (original < 0) {
			return false;
		}
		long x = original;
		long reverse = 0;
		while (x != 0) {
			reverse = reverse * 10 + x % 10;
			x /= 10;
		}
		return reverse == original;
	}

    /**
     * Time complexity: O(lg(n))
     * Space complexity: O(log10(n))
     */
    public boolean isPalindrome_withDeque(int x) {
		if (x < 0) {
			return false;
		} else {
			final Deque<Integer> deque = new ArrayDeque<>();
			while (x != 0) {
				deque.addLast(x % 10);
				x /= 10;
			}
			while (!deque.isEmpty()) {
				final int left = deque.pollFirst();
				if (!deque.isEmpty()) {
					final int right = deque.pollLast();
					if (left != right) {
						return false;
					}
				}
			}
			return true;
		}
    }
    
    public static void main(final String[] args) {
        System.out.println("should be true: " + new PalindromeNumber().isPalindrome(121));
        System.out.println("should be false: " + new PalindromeNumber().isPalindrome(-121));
        System.out.println("should be false: " + new PalindromeNumber().isPalindrome(10));
	}
}