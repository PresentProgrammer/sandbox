import java.util.Arrays;

/**
 * Problem #316
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class RemoveDuplicateLetters {

    private static final int FIRST_LETTER_INDEX = 97;
    private static final int SIZE = 123;

    public String removeDuplicateLetters(final String input) {
    	final int[] counts = new int[SIZE];

    	final StringBuilder resultBuilder = new StringBuilder();
    	String s = input;
    	while (!s.isEmpty()) {
    	    Arrays.fill(counts, FIRST_LETTER_INDEX, SIZE, 0);
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
            resultBuilder.append(s.charAt(minInd));
            s = removeAll(s.substring(minInd + 1), s.charAt(minInd));
        }
    	return resultBuilder.toString();
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