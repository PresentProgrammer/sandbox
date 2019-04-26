/**
 * Problem #62
 * Time complexity: O(m - n)
 * Space complexity: O(1)
 * See mathematical explanation: https://leetcode.com/problems/unique-paths/discuss/22958/Math-solution-O(1)-space
 **/
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m < n) {
            m ^= n;
            n ^= m;
            m ^= n;
        }
        long res = 1;
        for (int i = m, j = 1; i <= m + n - 2; i++, j++) {
            res *= i;
            res /= j;
        }
        return (int) res;
    }
    
    public static void main(final String[] args) {
        System.out.println("3 == " + new UniquePaths().uniquePaths(3, 2));
        System.out.println("28 == " + new UniquePaths().uniquePaths(3, 7));
	}
}