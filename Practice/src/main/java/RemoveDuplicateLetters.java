import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem #316
 * Time complexity: O(?)
 * Space complexity: O(?)
 **/
public class RemoveDuplicateLetters {

	private static final String INITIAL_STRING = "{";
    private static final int ALPHABER_SIZE = 26;

    private String input;
    private String result;
    private List<Integer>[] charPositions;

    @SuppressWarnings("unchecked")
    public String removeDuplicateLetters(final String s) {
    	input = s;
    	result = INITIAL_STRING;
		charPositions = new List[ALPHABER_SIZE];
		for (int i = 0; i < ALPHABER_SIZE; i++) {
		    charPositions[i] = new ArrayList<>();
        }
		for (int i = 0; i < s.length(); i++) {
		    charPositions[s.charAt(i) - 'a'].add(i);
        }
		permute(0, new ArrayList<>());
		return result.equals(INITIAL_STRING) ? "" : result;
    }

    private void permute(final int i, final List<Integer> positions) {
    	if (i == ALPHABER_SIZE) {
    		final List<Integer> copiedPositions = new ArrayList<>(positions);
    		Collections.sort(copiedPositions);
    		final StringBuilder strBuilder = new StringBuilder();
    		for (final Integer pos : copiedPositions) {
    			strBuilder.append(input.charAt(pos));
			}
    		final String currString = strBuilder.toString();
    		if (currString.compareTo(result) < 0) {
    			result = currString;
			}
		} else if (charPositions[i].isEmpty()) {
			permute(i + 1, positions);
		} else {
			for (final Integer charPosition : charPositions[i]) {
				positions.add(charPosition);
				permute(i + 1, positions);
				positions.remove(positions.size() - 1);
			}
		}
	}

    public static void main(final String[] args) {
        System.out.println("abc == " + new RemoveDuplicateLetters().removeDuplicateLetters("bcabc"));
        System.out.println("acdb == " + new RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
        System.out.println("cadb == " + new RemoveDuplicateLetters().removeDuplicateLetters("cbaddabaa"));
	}
}