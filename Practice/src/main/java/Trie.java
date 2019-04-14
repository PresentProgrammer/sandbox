/**
 * Problem #208
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class Trie {

    private boolean key;
    private final Trie[] children;

    public Trie() {
        this.key = false;
        this.children = new Trie[26];
    }

    public void insert(String word) {
        Trie curr = this;
        for (int i = 0; i < word.length(); i++) {
            final int letterInd = word.charAt(i) - 'a';
            if (curr.children[letterInd] == null) {
                curr.children[letterInd] = new Trie();
            }
            curr = curr.children[letterInd];
        }
        curr.key = true;
    }

    public boolean search(String word) {
        final Trie prefixTrie = getPrefixTrie(word);
        return prefixTrie != null && prefixTrie.key;
    }

    public boolean startsWith(String prefix) {
        return getPrefixTrie(prefix) != null;
    }

    private Trie getPrefixTrie(final String prefix) {
        Trie curr = this;
        for (int i = 0; i < prefix.length(); i++) {
            final int letterInd = prefix.charAt(i) - 'a';
            if (curr.children[letterInd] == null) {
                return null;
            } else {
                curr = curr.children[letterInd];
            }
        }
        return curr;
    }
    
    public static void main(final String[] args) {
	}
}