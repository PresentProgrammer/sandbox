import java.util.Set;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    
    public int lengthOfLongestSubstring(String s) {
        final Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int maxSetSize = 0;
        while (right < s.length()) {
            if (set.contains(s.charAt(right))) {
                maxSetSize = Math.max(maxSetSize, set.size());
                while (s.charAt(left) != s.charAt(right)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                left++;                
            } else {
                set.add(s.charAt(right));                
            }
            right++;
        }
        return Math.max(maxSetSize, set.size());
    }
    
    public static void main(final String args[]) {
        final LongestSubstringWithoutRepeatingCharacters testedObject = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println("For \"abcabcbb\": " + testedObject.lengthOfLongestSubstring("abcabcbb"));
        System.out.println("For \"bbbbb\": " + testedObject.lengthOfLongestSubstring("bbbbb"));
        System.out.println("For \"pwwkew\": " + testedObject.lengthOfLongestSubstring("pwwkew"));
    }
}