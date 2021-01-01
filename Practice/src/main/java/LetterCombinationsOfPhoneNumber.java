import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Problem #17
 * Time complexity: O(2^n), where n is input length
 * Space complexity: O(2^n), where n is input length
 **/
public class LetterCombinationsOfPhoneNumber {

    private static final String[] DIGIT_TO_CHARS = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(final String digits) {
        if (digits.isEmpty()) {
            return Collections.emptyList();
        }
        final Deque<String> deque = new ArrayDeque<>((int) Math.pow(4, digits.length()));
        deque.add("");
        for (int i = 0; i < digits.length(); i++) {
            while (deque.peekFirst().length() == i) {
                final String first = deque.removeFirst();
                for (final char ch : DIGIT_TO_CHARS[Character.getNumericValue(digits.charAt(i))].toCharArray()) {
                    deque.addLast(first + ch);
                }
            }
        }
        return new ArrayList<>(deque);
    }

    public static void main(final String[] args) {
        System.out.println("[ad, ae, af, bd, be, bf, cd, ce, cf] == " + new LetterCombinationsOfPhoneNumber().letterCombinations("23"));
    }
}