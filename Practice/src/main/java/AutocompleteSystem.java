import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem #642
 * Time complexity: O(N * K * log(K)) - where N is total length of sentences and input
 * because each non-# input takes O(K * log(K))
 * each # input takes O(sentenceBefore#.length() * K * log(K))
 * Space complexity: O(N)
 */
public class AutocompleteSystem {

    private static final int K = 3;

    private final TrieNode root = new TrieNode();
    private final StringBuilder strBuilder = new StringBuilder();
    private TrieNode curr = root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            root.insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char input) {
        if (input == '#') {
            root.insert(strBuilder.toString());
            strBuilder.setLength(0);
            curr = root;
            return List.of();
        } else {
            strBuilder.append(input);
            if (curr == null) {
                return List.of();
            } else {
                curr = curr.getChild(input);
                return curr == null ? List.of() : curr.getHottest();
            }
        }
    }

    private static class TrieNode implements Comparable<TrieNode> {

        private String sentence;
        private int times;
        private final TrieNode[] children = new TrieNode['z' - 'a' + 2];
        private final List<TrieNode> hottest = new ArrayList<>(K + 1);

        void insert(String sentence) {
            insert(sentence, 1);
        }

        void insert(String sentence, int times) {
            final Deque<TrieNode> stack = new ArrayDeque<>(sentence.length() + 1);
            TrieNode curr = this;
            for (int i = 0; i < sentence.length(); i++) {
                final int index = toIndex(sentence.charAt(i));
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
                stack.push(curr);
            }
            curr.sentence = sentence;
            curr.times += times;

            for (final TrieNode node : stack) {
                node.updateHottest(curr);
            }
        }

        List<String> getHottest() {
            return hottest.stream()
                    .map(tn -> tn.sentence)
                    .collect(Collectors.toUnmodifiableList());
        }

        TrieNode getChild(char ch) {
            return children[toIndex(ch)];
        }

        @Override
        public int compareTo(TrieNode other) {
            final int timesCmp = Integer.compare(other.times, this.times);
            return timesCmp != 0 ? timesCmp : this.sentence.compareTo(other.sentence);
        }

        private void updateHottest(TrieNode node) {
            if (!hottest.contains(node)) {
                hottest.add(node);
            }
            Collections.sort(hottest);
            if (hottest.size() > K) {
                hottest.remove(K);
            }
        }

        private static int toIndex(char ch) {
            return ch == ' ' ? 0 : ch - 'a' + 1;
        }
    }
}