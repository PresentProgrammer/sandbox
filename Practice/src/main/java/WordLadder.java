import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static java.util.Arrays.asList;

/**
 * Problem #127
 * Time complexity: O(n * k), where n — number of words, k — length of words.
 * Space complexity: O(n * k)
 **/
public class WordLadder {

	private static Map<String, String[]> GENERIC_WORDS = new HashMap<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    	if (!wordList.contains(endWord)) {
    		return 0;
		}

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

		final Map<String, Integer> beginVisited = new HashMap<>();
		beginVisited.put(beginWord, 1);
		final Map<String, Integer> endVisited = new HashMap<>();
		endVisited.put(endWord, 1);
		final Queue<Pair<String, Integer>> beginQueue = new ArrayDeque<>();
		final Queue<Pair<String, Integer>> endQueue = new ArrayDeque<>();
		beginQueue.add(new Pair<>(beginWord, 1));
		endQueue.add(new Pair<>(endWord, 1));
		Map<String, Integer> activeVisited = endVisited;
		Queue<Pair<String, Integer>> activeQueue = endQueue;
		while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
			activeVisited = activeVisited == beginVisited ? endVisited : beginVisited;
			activeQueue = activeQueue == beginQueue ? endQueue : beginQueue;
			final Pair<String, Integer> curr = activeQueue.poll();
			final String currWord = curr.getKey();
			final Integer currLength = curr.getValue();
			for (final String currGenericWord : genericWordsOf(currWord)) {
				for (final String nextWord : allComboDict.getOrDefault(currGenericWord, Collections.emptyList())) {
					if (!activeVisited.containsKey(nextWord)) {
						final Map<String, Integer> inactiveVisited = activeVisited == beginVisited ? endVisited : beginVisited;
						if ((inactiveVisited).containsKey(nextWord)) {
							return currLength + inactiveVisited.get(nextWord);
						} else {
							activeVisited.put(nextWord, currLength + 1);
							activeQueue.offer(new Pair<>(nextWord, currLength + 1));
						}
					}
				}
			}
		}
		return 0;
    }

    private static String[] genericWordsOf(final String word) {
    	return GENERIC_WORDS.computeIfAbsent(word, (w) -> {
			final String[] genericWords = new String[w.length()];
			for (int i = 0; i < w.length(); i++) {
				genericWords[i] = w.substring(0, i) + "*" + w.substring(i + 1);
			}
			return genericWords;
		});
    }

    public static void main(final String[] args) {
        System.out.println("5 == " + new WordLadder().ladderLength(
                "hit", "cog", asList("hot","dot","dog","lot","log","cog")));
        System.out.println("0 == " + new WordLadder().ladderLength(
                "hit", "cog", asList("hot","dot","dog","lot","log")));
	}
}