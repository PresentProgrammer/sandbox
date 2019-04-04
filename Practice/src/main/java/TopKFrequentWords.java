import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem #692
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 **/
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
		final Map<String, Integer> map = new HashMap<>();
		for (final String word : words) {
		    map.compute(word, (unusedKey, v) -> v == null ? 1 : v + 1);
        }
		final Set<WordCount> wordCounts = new TreeSet<>();
		for (final Map.Entry<String, Integer> entry : map.entrySet()) {
		    wordCounts.add(new WordCount(entry.getKey(), entry.getValue()));
        }
		return wordCounts.stream()
                .limit(k)
                .map(WordCount::getWord)
                .collect(Collectors.toList());
    }

    private static class WordCount implements Comparable<WordCount> {

        private String word;
        private int count;

        private WordCount(String word, int count) {
            this.word = word;
            this.count = count;
        }

        private String getWord() {
            return this.word;
        }

        @Override
        public int compareTo(final WordCount other) {
            if (this.count > other.count) {
                return -1;
            } else if (this.count < other.count) {
                return 1;
            } else {
                return this.word.compareTo(other.word);
            }
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("[\"i\", \"love\"] == " + new TopKFrequentWords().topKFrequent(
                new String[] { "i", "love", "leetcode", "i", "love", "coding" }, 2));
        System.out.println("[\"the\", \"is\", \"sunny\", \"day\"] == " + new TopKFrequentWords().topKFrequent(
                new String[] { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" }, 4));
	}
}