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
public class NumberOfMatchingSubsequencesNextLetterPointers {

	public int numMatchingSubseq(String s, String[] words) {
		final List<Node>[] heads = buildHeads(words);
		int result = 0;
		for (final char curr: s.toCharArray()) {
			final int index = toIndex(curr);
			final List<Node> nodes = heads[index];
			heads[index] = new ArrayList<>();
			for (final Node node : nodes) {
				if (node.incrementPosEndReached()) {
					result++;
				} else {
					heads[node.currCharIndex()].add(node);
				}
			}
		}
		return result;
	}

	private static List<Node>[] buildHeads(String[] words) {
		@SuppressWarnings("unchecked")
		final List<Node>[] heads = new List['z' - 'a' + 1];
		for (int i = 0; i <= 'z' - 'a'; i++) {
			heads[i] = new ArrayList<>();
		}
		for (final String word : words) {
			final Node node = new Node(word);
			heads[node.currCharIndex()].add(node);
		}
		return heads;
	}

	private static class Node {

		private final String word;
		private int pos;

		Node(String word) {
			this.word = word;
		}

		boolean incrementPosEndReached() {
			pos++;
			return endReached();
		}

		int currCharIndex() {
			return toIndex(word.charAt(pos));
		}

		private boolean endReached() {
			return pos == word.length();
		}
	}

	private static int toIndex(char ch) {
		return 'z' - ch;
	}

	public static void main(final String[] args) {
		System.out.println("3 == " + new NumberOfMatchingSubsequencesNextLetterPointers().numMatchingSubseq(
				"abcde", new String[] { "a", "bb", "acd", "ace" }));
		System.out.println("2 == " + new NumberOfMatchingSubsequencesNextLetterPointers().numMatchingSubseq(
				"dsahjpjauf", new String[] { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" }));
		System.out.println("2 == " + new NumberOfMatchingSubsequencesNextLetterPointers().numMatchingSubseq(
				"qlhxagxdqh", new String[] { "qlhxagxdq", "qlhxagxdq", "lhyiftwtut", "yfzwraahab" }));
	}
}