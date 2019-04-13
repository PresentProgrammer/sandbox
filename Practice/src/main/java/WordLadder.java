import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Problem #127
 * Time complexity: O(n * k), where n — number of words, k — length of words.
 * Space complexity: O(n * k)
 **/
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		final Map<String, List<String>> allComboDict = new HashMap<>();
		for (final String word : wordList) {
		    for (final String genericWord : genericWordsOf(word)) {
		        allComboDict.compute(genericWord, (k, v) -> {
		            final List<String> matchingWords = v == null ? new ArrayList<>() : v;
		            matchingWords.add(word);
		            return matchingWords;
                });
            }
        }

		final Set<String> visited = new HashSet<>();
		final Queue<Pair<String, Integer>> queue = new ArrayDeque<>();
		queue.offer(new Pair<>(beginWord, 1));
		while (!queue.isEmpty()) {
		    final Pair<String, Integer> curr = queue.poll();
		    final String currWord = curr.getKey();
		    final Integer currDepth = curr.getValue();
		    for (final String currGenericWord : genericWordsOf(currWord)) {
		        if (!visited.contains(currGenericWord)) {
		            visited.add(currGenericWord);
		            for (final String nextWord : allComboDict.getOrDefault(currGenericWord, Collections.emptyList())) {
		                if (nextWord.equals(endWord)) {
		                    return currDepth + 1;
                        } else {
		                    queue.offer(new Pair<>(nextWord, currDepth + 1));
                        }
                    }
                }
            }
        }
		return 0;
    }

    private static String[] genericWordsOf(final String word) {
        final String[] genericWords = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            genericWords[i] = word.substring(0, i) + "*" + word.substring(i + 1);
        }
        return genericWords;
    }

    public static void main(final String[] args) {
        System.out.println("5 == " + new WordLadder().ladderLength(
                "hit", "cog", asList("hot","dot","dog","lot","log","cog")));
        System.out.println("0 == " + new WordLadder().ladderLength(
                "hit", "cog", asList("hot","dot","dog","lot","log")));
	}
}