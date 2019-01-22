/**
 * Problem #6
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ZigZagConversion {
    
    public String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		final int length = s.length();
		final StringBuilder result = new StringBuilder();
        for (int j = 0; j < length; j += (numRows - 1) * 2) {
			result.append(s.charAt(j));
		}
		for (int i = 1; i < numRows - 1; i++) {
			int j = i;
			while (j < length) {
				result.append(s.charAt(j));
				j += (numRows - 1 - i) * 2;
				if (j < length) {
					result.append(s.charAt(j));
					j += i * 2;
				}
			}
		}
		for (int j = numRows - 1; j < length; j += (numRows - 1) * 2) {
			result.append(s.charAt(j));
		}
		return result.toString();
    }
    
    public static void main(final String[] args) {
        System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
		System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 4).equals("PINALSIGYAHRPI"));
		System.out.println(new ZigZagConversion().convert("A", 1).equals("A"));
    }
}