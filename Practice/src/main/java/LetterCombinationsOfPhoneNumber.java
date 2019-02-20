import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem #17
 * Time complexity: O(2^n), where n is input length
 * Space complexity: O(2^n), where n is input length
 **/
public class LetterCombinationsOfPhoneNumber {

    private static final Map<Character, List<Character>> DIGIT_TO_CHARS =
            Collections.unmodifiableMap(new HashMap<Character, List<Character>>() {
                {
                    put('2', Arrays.asList('a', 'b', 'c'));
                    put('3', Arrays.asList('d', 'e', 'f'));
                    put('4', Arrays.asList('g', 'h', 'i'));
                    put('5', Arrays.asList('j', 'k', 'l'));
                    put('6', Arrays.asList('m', 'n', 'o'));
                    put('7', Arrays.asList('p', 'q', 'r', 's'));
                    put('8', Arrays.asList('t', 'u', 'v'));
                    put('9', Arrays.asList('w', 'x', 'y', 'z'));
                }
            });
    
    public List<String> letterCombinations(final String digits) {
        return digits.isEmpty() ? Collections.emptyList() : solve(digits, 0, "");
    }

    private static List<String> solve(final String digits, final int currIndex, final String resultSoFar) {
        if (currIndex < digits.length()) {
            return DIGIT_TO_CHARS.get(digits.charAt(currIndex)).stream()
                    .map(currChar -> solve(digits, currIndex + 1, resultSoFar + currChar))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        } else {
            return Collections.singletonList(resultSoFar);
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("[ad, ae, af, bd, be, bf, cd, ce, cf] == " + new LetterCombinationsOfPhoneNumber().letterCombinations("23"));
	}
}