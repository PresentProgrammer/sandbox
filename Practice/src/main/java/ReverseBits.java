/**
 * Problem #190
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ReverseBits {

    public int reverseBits(int n) {
		int result = 0;
		int left = 0B10000000000000000000000000000000;
		while (n != 0) {
		    result |= (n & 0B1) > 0 ? left : 0B0;
		    left >>>= 1;
		    n >>>= 1;
        }
		return result;
    }
    
    public static void main(final String[] args) {
        assert new ReverseBits().reverseBits(0B00000010100101000001111010011100) == 0B00111001011110000010100101000000;
        assert new ReverseBits().reverseBits(0B11111111111111111111111111111101) == 0B10111111111111111111111111111111;
	}
}