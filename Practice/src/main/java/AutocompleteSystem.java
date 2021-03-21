import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem #642
 * Time complexity: O(N + M + K * log(K)) - see method complexity for details
 * Space complexity: O(N + L)
 **/
public class AutocompleteSystem {

    private static final int K = 3;

    private final Trie root;
    private Trie curr;
    private StringBuilder strBuilder;

    /**
     * Time complexity: O(N), where N - total length of all sentences
     * Space complexity: O(N)
     */
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            this.root.insert(sentences[i], times[i]);
        }
        this.curr = this.root;
        this.strBuilder = new StringBuilder();
    }

    /**
     * Time complexity: O(N + M + K * log(K)),
     *     where N - total length of all sentences,
     *     M - number of recorded sentences to quick select from
     *     K = 3
     * Space complexity: O(N + L), where L - length of longest sentence, for recursive stack.
     */
    public List<String> input(char ch) {
        if (ch == '#') {
            curr.record(strBuilder.toString());
            curr = root;
            strBuilder = new StringBuilder();
            return List.of();
        } else {
            curr = curr.getChild(ch);
            strBuilder.append(ch);
            final List<Pair> candidates = curr.getChildSentences();
            if (candidates.size() > K) {
                quickSelect(candidates);
            }
            return candidates.stream()
                    .sorted()
                    .map(Pair::getSentence)
                    .limit(K)
                    .collect(Collectors.toUnmodifiableList());
        }
    }

    private static void quickSelect(List<Pair> list) {
        Collections.shuffle(list);
        int lo = 0;
        int hi = list.size() - 1;
        while (true) {
            final int j = partition(list, lo, hi);
            if (j == K - 1) {
                return;
            } else if (j < K - 1) {
                lo = j + 1;
            } else {
                hi = j - 1;
            }
        }
    }

    private static int partition(List<Pair> list, int lo, int hi) {
        int i = lo + 1;
        int j = hi;
        final Pair loElem = list.get(lo);
        while (true) {
            while (i <= hi && less(list.get(i), loElem)) {
                i++;
            }
            while (less(loElem, list.get(j))) {
                j--;
            }
            if (i < j) {
                Collections.swap(list, i++, j--);
            } else {
                Collections.swap(list, lo, j);
                return j;
            }
        }
    }

    private static boolean less(Pair first, Pair second) {
        return first.compareTo(second) < 0;
    }

    private static class Trie {

        private final Trie[] children = new Trie['z' - 'a' + 2];
        private String sentence;
        private int times;

        Trie getChild(char ch) {
            final int childIndex = toIndex(ch);
            if (children[childIndex] == null) {
                children[childIndex] = new Trie();
            }
            return children[childIndex];
        }

        void insert(String sentence, int times) {
            Trie curr = this;
            for (int i = 0; i < sentence.length(); i++) {
                final int childIndex = toIndex(sentence.charAt(i));
                if (curr.children[childIndex] == null) {
                    curr.children[childIndex] = new Trie();
                }
                curr = curr.children[childIndex];
            }
            curr.sentence = sentence;
            curr.times = times;
        }

        List<Pair> getChildSentences() {
            final List<Pair> result = new ArrayList<>();
            collectChildSentences(result);
            return result;
        }

        void record(String sentence) {
            this.sentence = sentence;
            this.times++;
        }

        private void collectChildSentences(List<Pair> result) {
            if (sentence != null) {
                result.add(new Pair(sentence, times));
            }
            for (final Trie child : children) {
                if (child != null) {
                    child.collectChildSentences(result);
                }
            }
        }

        private static int toIndex(char ch) {
            return ch == ' ' ? 0 : ch - 'a' + 1;
        }
    }

    private static class Pair implements Comparable<Pair> {

        private final String sentence;
        private final int count;

        Pair(String sentence, int count) {
            this.sentence = sentence;
            this.count = count;
        }

        String getSentence() {
            return sentence;
        }

        @Override
        public int compareTo(Pair other) {
            final int countCmp = Integer.compare(other.count, this.count);
            return countCmp == 0 ? this.sentence.compareTo(other.sentence) : countCmp;
        }
    }

    public static void main(String[] args) {
        final AutocompleteSystem as = new AutocompleteSystem(
                new String[]{"i love you", "island", "iroman", "i love leetcode"},
                new int[]{5, 3, 2, 2}
        );
        System.out.println(as.input('i'));
    }
}