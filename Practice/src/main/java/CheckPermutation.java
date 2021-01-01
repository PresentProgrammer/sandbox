import java.util.HashMap;
import java.util.Map;

/**
 * Problem #1.2
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class CheckPermutation {

    public boolean isPerm(final String a, final String b) {
        if (a.length() != b.length()) {
            return false;
        }
        final Map<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            counts.put(a.charAt(i), counts.getOrDefault(a.charAt(i), 0) + 1);
        }
        for (int i = 0; i < b.length(); i++) {
            final int count = counts.get(b.charAt(i));
            if (count == 0) {
                return false;
            } else {
                counts.put(b.charAt(i), count - 1);
            }
        }
        return true;
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new CheckPermutation().isPerm("aaab", "aaba"));
        System.out.println("false == " + new CheckPermutation().isPerm("aaab", "aaaa"));
    }
}