import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    
    public int lengthOfLongestSubstring(String s) {
        final int length = s.length();
        if (length < 2) {
            return length;
        }
        int i = 0;
        int j = 0;
        int max = 0;
        final int ASCII_SYMBOL_NUMBER = 128;
        final int[] lastIndices = new int[ASCII_SYMBOL_NUMBER];
        Arrays.fill(lastIndices, -1);
        while (j < length) {
            final char jthChar = s.charAt(j);
            final int charLastIndex = lastIndices[jthChar];
            if (charLastIndex >= i) {
                max = Math.max(max, j - i);
                i = charLastIndex + 1;
            }
            lastIndices[jthChar] = j;
            j++;
        }
        return Math.max(max, j - i);
    }
    
    public int lengthOfLongestSubstring_usingHashMap(String s) {
        final int length = s.length();
        if (length < 2) {
            return length;
        }
        int i = 0;
        int j = 0;
        int max = 0;        
        final Map<Character, Integer> map = new HashMap<>(32);
        while (j < length) {
            final char jthChar = s.charAt(j);
            final Integer charLastIndex = map.get(jthChar);
            if (charLastIndex != null && charLastIndex >= i) {
                max = Math.max(max, j - i);
                i = charLastIndex + 1;
            }
            map.put(jthChar, j);
            j++;
        }
        return Math.max(max, j - i);
    }
    
    public static void main(final String args[]) {
        final LongestSubstringWithoutRepeatingCharacters testedObject = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println("For \"abcabcbb\": " + testedObject.lengthOfLongestSubstring("abcabcbb"));
        System.out.println("For \"bbbbb\": " + testedObject.lengthOfLongestSubstring("bbbbb"));
        System.out.println("For \"pwwkew\": " + testedObject.lengthOfLongestSubstring("pwwkew"));
    }
}