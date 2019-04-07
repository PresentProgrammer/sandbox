/**
 * Problem #191
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class NumberOf1Bits {

    /**
     * See https://leetcode.com/articles/number-1-bits/, approach #2
     */
    public int hammingWeight(int n) {
		int result = 0;
        while (n != 0) {
            result++;
            n &= (n - 1);
        }
		return result;
    }
    
    public static void main(final String[] args) {
        System.out.println("3 == " + new NumberOf1Bits().hammingWeight(0B00000000000000000000000000001011));
        System.out.println("1 == " + new NumberOf1Bits().hammingWeight(0B00000000000000000000000010000000));
        System.out.println("1 == " + new NumberOf1Bits().hammingWeight(0B10000000000000000000000000000000));
        System.out.println("31 == " + new NumberOf1Bits().hammingWeight(0B11111111111111111111111111111101));
	}
}