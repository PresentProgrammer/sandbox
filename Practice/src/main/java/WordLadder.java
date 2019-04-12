import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import static java.util.Arrays.asList;

/**
 * Problem #127
 * Time complexity: O(n^2 * k), where n — number of words, k — length of words.
 * Space complexity: O(n * k)
 **/
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		final boolean[] inQueue = new boolean[wordList.size()];
		final Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(beginWord, 1));
		while (!queue.isEmpty()) {
		    final Node curr = queue.poll();
		    for (int i = 0; i < wordList.size(); i++) {
		        if (!inQueue[i] && isTransformable(curr.word, wordList.get(i))) {
                    if (wordList.get(i).equals(endWord)) {
                        return curr.pathLength + 1;
                    } else {
                        inQueue[i] = true;
                        queue.offer(new Node(wordList.get(i), curr.pathLength + 1));
                    }
                }
            }
        }
		return 0;
    }

    private static boolean isTransformable(final String left, final String right) {
        int differentCharCount = 0;
        for (int i = 0; i < left.length(); i++) {
            if (left.charAt(i) != right.charAt(i)) {
                differentCharCount++;
                if (differentCharCount > 1) {
                    break;
                }
            }
        }
        return differentCharCount == 1;
    }

    private static class Node {
        private String word;
        private int pathLength;

        private Node(String word, int pathLength) {
            this.word = word;
            this.pathLength = pathLength;
        }
    }
    
    public static void main(final String[] args) {
        System.out.println("5 == " + new WordLadder().ladderLength(
                "hit", "cog", asList("hot","dot","dog","lot","log","cog")));
        System.out.println("0 == " + new WordLadder().ladderLength(
                "hit", "cog", asList("hot","dot","dog","lot","log")));
	}
}