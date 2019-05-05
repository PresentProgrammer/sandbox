import java.util.List;

import static java.util.Arrays.asList;

/**
 * Problem #139
 * Time complexity: O(n * m), where n — string length, m — dictionary size.
 * Space complexity: O(n)
 **/
public class WordBreak {

    private static final int UNKNOWN = 0;
    private static final int TRUE = 1;
    private static final int FALSE = 2;

    private int[] fromOffset;
    private String s;
    private List<String> wordDict;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.fromOffset = new int[s.length() + 1];
        this.s = s;
        this.wordDict = wordDict;
        return wordBreak(0);
    }

    private boolean cachedWordBreak(int offset) {
        if (fromOffset[offset] == UNKNOWN) {
            final boolean result = wordBreak(offset);
            fromOffset[offset] = result ? TRUE : FALSE;
            return result;
        } else {
            return fromOffset[offset] == TRUE;
        }
    }

    private boolean wordBreak(int offset) {
        if (offset == s.length()) {
            return true;
        } else {
            for (final String word : wordDict) {
                if (s.startsWith(word, offset) && cachedWordBreak(offset + word.length())) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new WordBreak().wordBreak("leetcode", asList("leet", "code")));
        System.out.println("true == " + new WordBreak().wordBreak("applepenapple", asList("apple", "pen")));
        System.out.println("false == " + new WordBreak().wordBreak("catsandog", asList("cats", "dog", "sand", "and", "cat")));
        System.out.println("false == " + new WordBreak().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa")));
    }
}