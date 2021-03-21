import java.util.Optional;

/**
 * Problem #208
 * Time complexity: O(N) for each operation, where N is length of word
 * Space complexity: O(N), where N is sum of lengths, but less, if words share prefixes.
 **/
@SuppressWarnings("Duplicates")
public class Trie {

    private final Trie[] children = new Trie['z' - 'a' + 1];
    private boolean isKey;

    public void insert(String word) {
        Trie curr = this;
        for (int i = 0; i < word.length(); i++) {
            final int childIndex = toIndex(word.charAt(i));
            if (curr.children[childIndex] == null) {
                curr.children[childIndex] = new Trie();
            }
            curr = curr.children[childIndex];
        }
        curr.isKey = true;
    }

    public boolean search(String word) {
        return getPrefixTrie(word)
                .map(prefix -> prefix.isKey)
                .orElse(false);
    }

    public boolean startsWith(String prefix) {
        return getPrefixTrie(prefix).isPresent();
    }

    private Optional<Trie> getPrefixTrie(String word) {
        Trie curr = this;
        for (int i = 0; i < word.length(); i++) {
            final int childIndex = toIndex(word.charAt(i));
            if (curr.children[childIndex] == null) {
                return Optional.empty();
            } else {
                curr = curr.children[childIndex];
            }
        }
        return Optional.of(curr);
    }

    private static int toIndex(char ch) {
        return ch - 'a';
    }

    public static void main(final String[] args) {
    }
}