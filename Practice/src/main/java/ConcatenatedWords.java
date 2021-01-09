import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Problem #472
 * Time complexity: O()
 * Space complexity: O()
 **/
public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        final List<String> sortedWords = Arrays.stream(words)
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        final Trie trie = new Trie();
        final List<String> result = new ArrayList<>();
        for (final String word : sortedWords) {
            if (isConcatenated(word, trie)) {
                result.add(word);
            }
            trie.insert(word);
        }
        return result;
    }

    private static boolean isConcatenated(String word, Trie trie) {
        final boolean[] visited = new boolean[word.length()];
        final Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        while (!q.isEmpty()) {
            final List<Integer> nextIndexes = trie.indexesAfterEnding(word, q.poll());
            for (final Integer nextIndex : nextIndexes) {
                if (nextIndex == word.length()) {
                    return true;
                } else if (!visited[nextIndex]) {
                    visited[nextIndex] = true;
                    q.offer(nextIndex);
                }
            }
        }
        return false;
    }

    private static class Trie {
        boolean isEnding = false;
        Trie[] children = new Trie['z' - 'a' + 1];

        void insert(String word) {
            insert(word, 0);
        }

        List<Integer> indexesAfterEnding(String word, int startPos) {
            return gatherIndexesAfterEnding(word, startPos, new ArrayList<>());
        }

        private List<Integer> gatherIndexesAfterEnding(String word, int pos, List<Integer> indexes) {
            if (isEnding) {
                indexes.add(pos);
            }
            if (pos < word.length()) {
                final Trie child = children[toChildInd(word, pos)];
                if (child != null) {
                    child.gatherIndexesAfterEnding(word, pos + 1, indexes);
                }
            }
            return indexes;
        }

        private void insert(String word, int pos) {
            if (pos < word.length()) {
                final int childInd = toChildInd(word, pos);
                if (children[childInd] == null) {
                    children[childInd] = new Trie();
                }
                children[childInd].insert(word, pos + 1);
            } else {
                this.isEnding = true;
            }
        }

        private static int toChildInd(String word, int pos) {
            return word.charAt(pos) - 'a';
        }
    }

    public static void main(final String[] args) {
        System.out.println("[dogcatsdog, catsdogcats, ratcatdogcat] == " +
                new ConcatenatedWords().findAllConcatenatedWordsInADict(new String[]{
                        "cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"
                })
        );
    }
}