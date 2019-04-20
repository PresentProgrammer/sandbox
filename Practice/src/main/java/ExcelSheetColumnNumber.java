/**
 * Problem #171
 * Time complexity: O(n), where n == s.length()
 * Space complexity: O(1)
 **/
public class ExcelSheetColumnNumber {

    private static final int BASE = 26;

    public int titleToNumber(String s) {
        int result = 0;
		for (int i = 0; i < s.length(); i++) {
		    result = result * BASE + (s.charAt(i) - 'A' + 1);
        }
		return result;
    }

    public static void main(final String[] args) {
        System.out.println("1 == " + new ExcelSheetColumnNumber().titleToNumber("A"));
        System.out.println("28 == " + new ExcelSheetColumnNumber().titleToNumber("AB"));
        System.out.println("701 == " + new ExcelSheetColumnNumber().titleToNumber("ZY"));
	}
}