/**
 * Problem #1.6
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class StringCompression {
    
    public String compressIfWorth(final String s) {
		final StringBuilder strBuilder = new StringBuilder();
		int counter = 0;
		for (int i = 0; i < s.length(); i++) {
		    counter++;
		    if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
		        strBuilder.append(s.charAt(i));
		        strBuilder.append(counter);
		        counter = 0;
            }
        }
		return strBuilder.length() < s.length() ? strBuilder.toString() : s;
    }
    
    public static void main(final String[] args) {
        System.out.println("a2b1c5a3 == " + new StringCompression().compressIfWorth("aabcccccaaa"));
        System.out.println("abcaa == " + new StringCompression().compressIfWorth("abcaa"));
	}
}