import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #316
 * Time complexity: O(?)
 * Space complexity: O(?)
 **/
public class RemoveDuplicateLetters {

    private static final int ALPHABER_SIZE = 26;

    @SuppressWarnings("unchecked")
    public String removeDuplicateLetters(final String s) {
		final List<Integer>[] charPositions = new List[ALPHABER_SIZE];
		for (int i = 0; i < ALPHABER_SIZE; i++) {
		    charPositions[i] = new ArrayList<>();
        }
		for (int i = 0; i < s.length(); i++) {
		    charPositions[s.charAt(i) - 'a'].add(i);
        }

		final int[] selectedIndexes = selectIndexes(
				new int[ALPHABER_SIZE], charPositions, s.length(), ALPHABER_SIZE);
		final int[] selectedPositions = new int[ALPHABER_SIZE];
		for (int i = 0; i < ALPHABER_SIZE; i++) {
			selectedPositions[i] = selectedIndexes[i] == -1 ? -1 : charPositions[i].get(selectedIndexes[i]);
		}
		Arrays.sort(selectedPositions);
		final StringBuilder strBuilder = new StringBuilder();
		for (final int pos : selectedPositions) {
		    if (pos >= 0) {
                strBuilder.append(s.charAt(pos));
            }
        }
		return strBuilder.toString();
    }

    private static int[] selectIndexes(final int[] selectedIndexes, final List<Integer>[] charPositions,
			final int rightBorder, final int rightBorderChar) {
    	int leftBorder = -1;
    	int leftAfterRightBorder = rightBorder;
    	for (int i = 0; i < rightBorderChar; i++) {
			final List<Integer> positions = charPositions[i];
			if (positions.isEmpty()) {
				selectedIndexes[i] = -1;
			} else {
				int j = 0;
				while (j < positions.size() && positions.get(j) <= leftBorder) {
					j++;
				}
				if (j < positions.size() && positions.get(j) < rightBorder) {
					leftBorder = positions.get(j);
					selectedIndexes[i] = j;
				} else {
					while (j < positions.size() && positions.get(j) <= leftAfterRightBorder) {
						j++;
					}
					if (j < positions.size()) {
						leftAfterRightBorder = positions.get(j);
						selectedIndexes[i] = j;
					} else {
						j--;
						selectIndexes(selectedIndexes, charPositions, positions.get(j), i);
						selectedIndexes[i] = j;
					}
				}
			}
		}
    	return selectedIndexes;
	}
    
    public static void main(final String[] args) {
        System.out.println("abc == " + new RemoveDuplicateLetters().removeDuplicateLetters("bcabc"));
        System.out.println("acdb == " + new RemoveDuplicateLetters().removeDuplicateLetters("cbacdcbc"));
        System.out.println("cadb == " + new RemoveDuplicateLetters().removeDuplicateLetters("cbaddabaa"));
	}
}