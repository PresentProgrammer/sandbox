/**
 * Problem #50
 * Time complexity: O(log n)
 * Space complexity: O(1)
 **/
public class Pow {

    public double myPow(final double x, final int pow) {
        double res = 1, currPow2 = x;
        int n = pow;
        while (n != 0) {
            if (n % 2 != 0) {
                res *= currPow2;
            }
            n /= 2;
            currPow2 *= currPow2;
        }
        return pow >= 0 ? res : 1.0 / res;
    }

    public static void main(final String[] args) {
        System.out.println("1024.0 == " + new Pow().myPow(2.0, 10));
        System.out.println("9.26100 == " + new Pow().myPow(2.10000, 3));
        System.out.println("0.25000 == " + new Pow().myPow(2.00000, -2));
    }
}