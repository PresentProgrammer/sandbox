import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Problem #140
 * Time complexity: O(n * m), where n — string length, m — dictionary size.
 * Space complexity: O(n * m)
 **/
public class WordBreakII {

    private List<List<String>>[] fromOffset;
    private String s;
    private List<String> wordDict;

    @SuppressWarnings("unchecked")
    public List<String> wordBreak(String s, List<String> wordDict) {
        this.fromOffset = new List[s.length() + 1];
        this.s = s;
        this.wordDict = wordDict;
        final List<List<String>> sentences = wordBreak(0);
        final List<String> result = new ArrayList<>();
        for (final List<String> sentence : sentences) {
            final StringBuilder builder = new StringBuilder();
            for (final String word : sentence) {
                builder.append(word).append(' ');
            }
            builder.deleteCharAt(builder.length() - 1);
            result.add(builder.toString());
        }
        return result;
    }

    private List<List<String>> cachedWordBreak(int offset) {
        if (fromOffset[offset] == null) {
            final List<List<String>> sentences = wordBreak(offset);
            fromOffset[offset] = sentences.isEmpty() ? emptyList() : sentences;
            return sentences;
        } else {
            return fromOffset[offset];
        }
    }

    private List<List<String>> wordBreak(int offset) {
        if (offset == s.length()) {
            return singletonList(emptyList());
        } else {
            final List<List<String>> sentences = new ArrayList<>();
            for (final String word : wordDict) {
                if (s.startsWith(word, offset)) {
                    for (final List<String> sentenceAfterOffset : cachedWordBreak(offset + word.length())) {
                        final List<String> newSentence = new ArrayList<>();
                        newSentence.add(word);
                        newSentence.addAll(sentenceAfterOffset);
                        sentences.add(newSentence);
                    }
                }
            }
            return sentences;
        }
    }

    public static void main(final String[] args) {
        System.out.println(new WordBreakII().wordBreak("leetcode", asList("leet", "code")));
        System.out.println(new WordBreakII().wordBreak("applepenapple", asList("apple", "pen")));
        System.out.println(new WordBreakII().wordBreak("catsandog", asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(new WordBreakII().wordBreak("catsanddog", asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(new WordBreakII().wordBreak("pineapplepenapple", asList("apple", "pen", "applepen", "pine", "pineapple")));
    }
}