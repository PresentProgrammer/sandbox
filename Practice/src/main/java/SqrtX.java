/**
 * Problem #69
 * Time complexity: O(log x)
 * Space complexity: O(1)
 **/
public class SqrtX {

    public int mySqrt(int x) {
        if (x == 0) {
            return x;
        }
        int left = 1, right = x;
        while (left <= right) {
            final int mid = (left + right) / 2;
            final int divRes = x / mid;
            if (divRes == mid || divRes == mid - 1) {
                return divRes;
            } else if (divRes > mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(final String[] args) {
    }
}