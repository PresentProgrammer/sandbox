/**
 * Problem #5.1
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class Insertion {
    
    public int insert(final int n, final int m, final int i, final int j) {
		final int mask = (-1 << (j + 1)) | ~(-1 << i);
		return n & mask | m << i;
    }
    
    public static void main(final String[] args) {
        System.out.println("10001001100 == " + Integer.toBinaryString(new Insertion().insert(
                0B10000000000, 0B10011, 2, 6)));
	}
}