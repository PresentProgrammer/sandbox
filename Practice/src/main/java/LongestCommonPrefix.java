/**
 * Problem #14
 * Time complexity: O(n*m), where n — length of shortest string, and m — number of strings.
 * Space complexity: O(1)
 **/
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
		    return "";
        }
		int n = 0;
		scanningSymbols:
		while (true) {
		    final char currChar;
		    if (n < strs[0].length()) {
		        currChar = strs[0].charAt(n);
            } else {
		        break scanningSymbols;
            }
		    for (int i = 1; i < strs.length; i++) {
		        if (n >= strs[i].length() || strs[i].charAt(n) != currChar) {
		            break scanningSymbols;
                }
            }
		    n++;
        }
		return strs[0].substring(0, n);
    }
    
    public static void main(final String[] args) {
        System.out.println("fl == " + new LongestCommonPrefix().longestCommonPrefix(new String[] { "flower", "flow", "flight" }));
        System.out.println(" == " + new LongestCommonPrefix().longestCommonPrefix(new String[] { "dog", "racecar", "car" }));
	}
}