/**
 * Problem #70
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ClimbingStairs {

    public int climbStairs(int n) {
		return fibonacci(n + 1);
    }

    private static int fibonacci(final int n) {
        int left = 0, right = 1;
        for (int i = 2; i <= n; i++) {
            final int temp = right;
            right += left;
            left = temp;
        }
        return right;
    }
    
    public static void main(final String[] args) {
        System.out.println("1 == " + new ClimbingStairs().climbStairs(1));
        System.out.println("2 == " + new ClimbingStairs().climbStairs(2));
        System.out.println("3 == " + new ClimbingStairs().climbStairs(3));
        System.out.println("5 == " + new ClimbingStairs().climbStairs(4));
        System.out.println("8 == " + new ClimbingStairs().climbStairs(5));
	}
}