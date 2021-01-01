import java.util.HashMap;
import java.util.Map;

/**
 * Problem #359
 * Time complexity: O(n * m)
 * Space complexity: O(n * m)
 **/
public class LoggerRateLimiter {

    private final Trie trie = new Trie();

    public boolean shouldPrintMessage(int timestamp, String message) {
        return trie.shouldPrintMessage(timestamp, message);
    }

    private static class Trie {

        private static final int LIMIT = 10;

        private Integer savedTimestamp;
        private final Map<Character, Trie> children = new HashMap<>();

        boolean shouldPrintMessage(final int timestamp, final String message) {
            return shouldPrintMessage(timestamp, message, 0);
        }

        private boolean shouldPrintMessage(final int timestamp, final String message, final int index) {
            if (index == message.length()) {
                return resolveTimestamps(timestamp);
            } else {
                return children.computeIfAbsent(message.charAt(index), unused -> new Trie())
                        .shouldPrintMessage(timestamp, message, index + 1);
            }
        }

        private boolean resolveTimestamps(final int timestamp) {
            if (savedTimestamp == null || timestamp - savedTimestamp >= LIMIT) {
                savedTimestamp = timestamp;
                return true;
            } else {
                return false;
            }
        }
    }
}