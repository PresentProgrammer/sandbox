/**
 * Problem #316
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class RemoveDuplicateLetters {

    private static final int SIZE = 128;

    public String removeDuplicateLetters(final String s) {
    	if (s.isEmpty()) {
    		return "";
		}
    	final int[] counts = new int[SIZE];
    	for (int i = 0; i < s.length(); i++) {
    		counts[s.charAt(i)]++;
		}
    	int minInd = 0;
    	for (int i = 0; i < s.length(); i++) {
    		if (s.charAt(i) < s.charAt(minInd)) {
    			minInd = i;
			}
    		if (--counts[s.charAt(i)] == 0) {
    			break;
			}
		}
		return s.isEmpty() ? "" : s.charAt(minInd) + removeDuplicateLetters(removeAll(s.substring(minInd + 1), s.charAt(minInd)));
	}

	private static String removeAll(final String s, final char removed) {
    	final StringBuilder builder = new StringBuilder();
    	for (final char ch : s.toCharArray()) {
    		if (ch != removed) {
    			builder.append(ch);
			}
		}
    	return builder.toString();
	}

    public static void main(final String[] args) {
        System.out.println("dcbax == " + new RemoveDuplicateLetters().removeDuplicateLetters("dcbaxabcd"));
        System.out.println("abc == " + new RemoveDuplicateLetters().removeDuplicateLetters("bcabc"));
        System.out.println("acdb == " + new RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
        System.out.println("cadb == " + new RemoveDuplicateLetters().removeDuplicateLetters("cbaddabaa"));
        System.out.println("hesitxyplovdqfkz == " + new RemoveDuplicateLetters().removeDuplicateLetters("thesqtitxyetpxloeevdeqifkz"));
        System.out.println("bhcsdikworfltuzjxaympev == " + new RemoveDuplicateLetters().removeDuplicateLetters("bxshkpdwcsjdbikywvioxrypfzfbppydfilfxxtouzzjxaymjpmdoevv"));
	}
}