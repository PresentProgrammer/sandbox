import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Problem #388
 * Time complexity: O(n)
 * Space complexity: O(n) where n is input length.
 **/
public class LongestAbsoluteFilePath {

    private static final int SEPARATOR_LENGTH = "/".length();

    public int lengthLongestPath(final String input) {
        int maxLength = 0;
        final Deque<Integer> lengthStack = new ArrayDeque<>();
        for (final Pair<String, Integer> node : nodesAndLevels(input)) {
            while (lengthStack.size() > node.getValue()) {
                lengthStack.pop();
            }
            final int lengthSoFar = node.getKey().length() + (lengthStack.peek() == null ? 0 : lengthStack.peek());
            if (node.getKey().contains(".")) {
                maxLength = Math.max(maxLength, lengthSoFar);
            } else {
                lengthStack.push(lengthSoFar + SEPARATOR_LENGTH);
            }
        }
        return maxLength;
    }

    private static List<Pair<String, Integer>> nodesAndLevels(final String input) {
        final List<Pair<String, Integer>> pairs = new ArrayList<>();
        for (final String token : input.split("\\n")) {
            int level = 0;
            while (level < token.length() && token.charAt(level) == '\t') {
                level++;
            }
            pairs.add(new Pair<>(token.substring(level), level));
        }
        return pairs;
    }

    private static class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("20 == " + new LongestAbsoluteFilePath().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println("32 == " + new LongestAbsoluteFilePath().lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
	}
}