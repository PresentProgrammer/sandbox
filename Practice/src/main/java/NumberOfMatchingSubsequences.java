import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

// TODO: Analize complexity
// TODO: Go through alternatives: 1) Solution, 2) Binary search

/**
 * Problem #792
 * Time complexity: O()
 * Space complexity: O()
 **/
public class NumberOfMatchingSubsequences {

	public int numMatchingSubseq(String s, String[] words) {
		int result = 0;
		final Set<TrieNode> candidates = new HashSet<>();
		candidates.add(buildTrie(words));
		for (int i = 0; i < s.length(); i++) {
			final char currChar = s.charAt(i);
			final Set<TrieNode> newCandidates = new HashSet<>();
			for (final TrieNode candidate : candidates) {
				final Optional<TrieNode> optionalChild = candidate.pollChild(currChar);
				if (optionalChild.isPresent()) {
					final TrieNode child = optionalChild.get();
					result += child.keyCount;
					newCandidates.add(child);
				}
			}
			candidates.addAll(newCandidates);
			candidates.removeIf(TrieNode::noChildren);
		}
		return result;
	}

	private static TrieNode buildTrie(String[] words) {
		final TrieNode root = new TrieNode();
		for (final String word : words) {
			root.insert(word);
		}
		return root;
	}

	private static class TrieNode {

		private final TrieNode[] children = new TrieNode['z' - 'a' + 1];
		int keyCount;
		int childCount;

		void insert(String word) {
			TrieNode curr = this;
			for (int i = 0; i < word.length(); i++) {
				final int childIndex = toChildIndex(word.charAt(i));
				if (curr.children[childIndex] == null) {
					curr.children[childIndex] = new TrieNode();
					curr.childCount++;
				}
				curr = curr.children[childIndex];
			}
			curr.keyCount++;
		}

		Optional<TrieNode> pollChild(char ch) {
			final int childIndex = toChildIndex(ch);
			final Optional<TrieNode> child = Optional.ofNullable(children[childIndex]);
			if (child.isPresent()) {
				children[childIndex] = null;
				childCount--;
			}
			return child;
		}

		boolean noChildren() {
			return childCount <= 0;
		}

		private static int toChildIndex(char ch) {
			return ch - 'a';
		}
	}

	public static void main(final String[] args) {
		System.out.println("3 == " + new NumberOfMatchingSubsequences().numMatchingSubseq(
				"abcde", new String[] { "a", "bb", "acd", "ace" }));
		System.out.println("2 == " + new NumberOfMatchingSubsequences().numMatchingSubseq(
				"dsahjpjauf", new String[] { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" }));
		System.out.println("2 == " + new NumberOfMatchingSubsequences().numMatchingSubseq(
				"qlhxagxdqh", new String[] { "qlhxagxdq", "qlhxagxdq", "lhyiftwtut", "yfzwraahab" }));
	}
}