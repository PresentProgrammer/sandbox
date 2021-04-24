import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem #792
 * Time complexity: O(words.length + S.length + Sum(words[i].length))
 *                  words.length - to build heads;
 *                  S.length - because we go through whole S one time
 *                  Sum(words[i].length) because we visit each letter in words once at most.
 * Space complexity: O(words.length)
 **/
public class NumberOfMatchingSubsequencesCharacterIter {

	public int numMatchingSubseq(String s, String[] words) {
		final List<CharacterIterator>[] heads = buildHeads(words);
		int result = 0;
		for (final char ch : s.toCharArray()) {
			final List<CharacterIterator> iters = heads[ch];
			heads[ch] = new ArrayList<>();
			for (final CharacterIterator iter : iters) {
				final char next = iter.next();
				if (next == CharacterIterator.DONE) {
					result++;
				} else {
					heads[next].add(iter);
				}
			}
		}
		return result;
	}

	private static List<CharacterIterator>[] buildHeads(String[] words) {
		@SuppressWarnings("unchecked")
		final List<CharacterIterator>[] heads = new List['z' + 1];
		for (char ch = 'a'; ch <= 'z'; ch++) {
			heads[ch] = new ArrayList<>();
		}
		for (final String word : words) {
			heads[word.charAt(0)].add(new StringCharacterIterator(word));
		}
		return heads;
	}

	public static void main(final String[] args) {
		System.out.println("3 == " + new NumberOfMatchingSubsequencesCharacterIter().numMatchingSubseq(
				"abcde", new String[] { "a", "bb", "acd", "ace" }));
		System.out.println("2 == " + new NumberOfMatchingSubsequencesCharacterIter().numMatchingSubseq(
				"dsahjpjauf", new String[] { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" }));
		System.out.println("2 == " + new NumberOfMatchingSubsequencesCharacterIter().numMatchingSubseq(
				"qlhxagxdqh", new String[] { "qlhxagxdq", "qlhxagxdq", "lhyiftwtut", "yfzwraahab" }));
	}
}