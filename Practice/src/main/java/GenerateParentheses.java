import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem #22
 * Time complexity: O(?)
 * Space complexity: O(n)
 **/
public class GenerateParentheses {

    private List<String> result;
    private int[] combination;

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }
		result = new ArrayList<>();
		this.combination = new int[n];
		generateCombinations(n, 0);
		return result;
    }

    private void generateCombinations(final int rem, final int i) {
        if (i == combination.length - 1) {
            combination[i] = rem;
            result.add(generateString());
        } else {
            for (int curr = rem; curr >= 0; curr--) {
                combination[i] = curr;
                if (isCombinationValidSoFar(i)) {
                    generateCombinations(rem - curr, i + 1);
                } else {
                    break;
                }
            }
        }
    }

    private String generateString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final int openParenthesesCount : combination) {
            for (int i = 0; i < openParenthesesCount; i++) {
                stringBuilder.append('(');
            }
            stringBuilder.append(')');
        }
        return stringBuilder.toString();
    }

    private boolean isCombinationValidSoFar(final int right) {
        int sum = 0;
        for (int i = 0; i <= right; i++) {
            sum += combination[i];
            if (sum <= i) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(final String[] args) {
        System.out.println(new GenerateParentheses().generateParenthesis(0));
        System.out.println(new GenerateParentheses().generateParenthesis(1));
        System.out.println(new GenerateParentheses().generateParenthesis(2));
        System.out.println(new GenerateParentheses().generateParenthesis(3));
        System.out.println(new GenerateParentheses().generateParenthesis(4));
	}
}