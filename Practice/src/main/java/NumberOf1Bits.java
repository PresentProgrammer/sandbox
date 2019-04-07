/**
 * Problem #191
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class NumberOf1Bits {

    public int hammingWeight(int n) {
		int check = 0B1, result = 0;
		while (check != 0) {
		    if ((n & check) != 0) {
		        result++;
            }
		    check <<= 1;
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