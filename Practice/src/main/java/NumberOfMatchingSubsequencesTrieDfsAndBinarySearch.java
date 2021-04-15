import java.util.ArrayList;
import java.util.List;

/**
 * Problem #792
 * Time complexity: O()
 * Space complexity: O()
 **/
public class NumberOfMatchingSubsequencesTrieDfsAndBinarySearch {

	public int numMatchingSubseq(String s, String[] words) {
		return dfs(buildMap(s), buildTrie(words), 0);
	}

	private static int dfs(List<List<Integer>> map, TrieNode curr, int pos) {
		int result = curr.keyCount;
		for (int childIndex = 0; childIndex <= 'z' - 'a'; childIndex++) {
			if (curr.children[childIndex] != null) {
				final int nextPos = nextPos(map, pos, childIndex);
				if (nextPos >= 0) {
					result += dfs(map, curr.children[childIndex], nextPos);
				}
			}
		}
		return result;
	}

	private static int nextPos(List<List<Integer>> map, int currPos, int nextIndex) {
		final List<Integer> positions = map.get(nextIndex);
		final int binSearch = customBinarySearch(positions, currPos);
		if (binSearch == positions.size()) {
			return -1;
		} else {
			return positions.get(binSearch) + 1;
		}
	}

	private static int customBinarySearch(List<Integer> list, int key) {
		int lo = 0;
		int hi = list.size() - 1;
		while (lo <= hi) {
			final int mid = (lo + hi) >>> 1;
			final int midElem = list.get(mid);
			if (midElem < key) {
				lo = mid + 1;
			} else if (midElem > key) {
				hi = mid - 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	private static TrieNode buildTrie(String[] words) {
		final TrieNode root = new TrieNode();
		for (final String word : words) {
			root.insert(word);
		}
		return root;
	}

	private static List<List<Integer>> buildMap(String s) {
		final List<List<Integer>> map = new ArrayList<>();
		for (int i = 0; i <= 'z' - 'a'; i++) {
			map.add(new ArrayList<>());
		}
		for (int pos = 0; pos < s.length(); pos++) {
			map.get(toIndex(s.charAt(pos))).add(pos);
		}
		return map;
	}

	private static class TrieNode {

		final TrieNode[] children = new TrieNode['z' - 'a' + 1];
		int keyCount;

		void insert(String word) {
			TrieNode curr = this;
			for (int i = 0; i < word.length(); i++) {
				final int childIndex = toIndex(word.charAt(i));
				if (curr.children[childIndex] == null) {
					curr.children[childIndex] = new TrieNode();
				}
				curr = curr.children[childIndex];
			}
			curr.keyCount++;
		}
	}

	private static int toIndex(char ch) {
		return ch - 'a';
	}

	public static void main(final String[] args) {
		System.out.println("3 == " + new NumberOfMatchingSubsequencesTrieDfsAndBinarySearch().numMatchingSubseq(
				"abcde", new String[] { "a", "bb", "acd", "ace" }));
		System.out.println("2 == " + new NumberOfMatchingSubsequencesTrieDfsAndBinarySearch().numMatchingSubseq(
				"dsahjpjauf", new String[] { "ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax" }));
		System.out.println("2 == " + new NumberOfMatchingSubsequencesTrieDfsAndBinarySearch().numMatchingSubseq(
				"qlhxagxdqh", new String[] { "qlhxagxdq", "qlhxagxdq", "lhyiftwtut", "yfzwraahab" }));
	}
}