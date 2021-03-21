/**
 * Problem #211
 **/
@SuppressWarnings("DuplicatedCode")
public class WordDictionary {

    private final Trie trie = new Trie();

    /**
     * Time complexity: O(N)
     * Space complexity: O(N)
     */
    public void addWord(String word) {
        trie.insert(word);
    }

    /**
     * Time complexity: O(L * 26 ^ D), where L number of normal letters in the word, and D — number of '.'.
     * Space complexity: O(N), for recursive stack.
     */
    public boolean search(String word) {
        return trie.search(word);
    }

    private static class Trie {

        private static final char ANY = '.';

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
            return search(word, 0);
        }

        private boolean search(String word, int pos) {
            if (pos == word.length()) {
                return isKey;
            } else if (word.charAt(pos) == ANY) {
                for (final Trie child : children) {
                    if (child != null && child.search(word, pos + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                final int childIndex = toIndex(word.charAt(pos));
                return children[childIndex] != null && children[childIndex].search(word, pos + 1);
            }
        }

        private static int toIndex(char ch) {
            return ch - 'a';
        }
    }
}