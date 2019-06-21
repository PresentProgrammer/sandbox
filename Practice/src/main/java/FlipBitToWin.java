/**
 * Problem #5.3
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class FlipBitToWin {
    
    public int maxSequenceOf1(final int n) {
		int curr = 0;
		int prev = 0;
		int max = 0;
		for (int bit = 1; bit != 0; bit = bit << 1) {
		    if ((bit & n) == 0) {
		        max = Math.max(max, prev + curr);
		        prev = curr + 1;
		        curr = 0;
            } else {
		        curr++;
            }
        }
		return Math.max(max, prev + curr);
    }
    
    public static void main(final String[] args) {
        System.out.println("8 == " + new FlipBitToWin().maxSequenceOf1(0B11011101111));
        System.out.println("32 == " + new FlipBitToWin().maxSequenceOf1(-1));
	}
}