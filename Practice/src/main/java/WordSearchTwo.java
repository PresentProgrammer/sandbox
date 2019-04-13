import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Problem #212
 * Time complexity: O()
 * Space complexity: O()
 **/
public class WordSearchTwo {

    public List<String> findWords(char[][] board, String[] words) {
        final Map<Character, Set<String>> firstCharWords = new HashMap<>();
        for (final String word : words) {
            final Character firstChar = word.charAt(0);
            firstCharWords.put(firstChar, withElement(firstCharWords.getOrDefault(firstChar, new HashSet<>()), word));
        }
        final List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (firstCharWords.containsKey(board[i][j])) {
                    final Iterator<String> wordIter = firstCharWords.get(board[i][j]).iterator();
                    while (wordIter.hasNext()) {
                        final String currWord = wordIter.next();
                        if (findWord(currWord, 0, board, new Pair<>(i, j), new HashSet<>())) {
                            wordIter.remove();
                            result.add(currWord);
                        }
                    }
                }
            }
        }
        return result;
    }

    private static boolean findWord(String word, int charIndex, char[][] board, Pair<Integer, Integer> cell,
            Set<Pair<Integer, Integer>> visited) {
        final int i = cell.getKey();
        final int j = cell.getValue();
        if (charIndex == word.length()) {
            return true;
        } else if (0 <= i && i < board.length && 0 <= j && j < board[i].length
                && board[i][j] == word.charAt(charIndex) && !visited.contains(cell)) {
            return findWord(word, charIndex + 1, board, new Pair<>(i + 1, j), withElement(new HashSet<>(visited), cell))
                    || findWord(word, charIndex + 1, board, new Pair<>(i - 1, j), withElement(new HashSet<>(visited), cell))
                    || findWord(word, charIndex + 1, board, new Pair<>(i, j + 1), withElement(new HashSet<>(visited), cell))
                    || findWord(word, charIndex + 1, board, new Pair<>(i, j - 1), withElement(new HashSet<>(visited), cell));
        } else {
            return false;
        }
    }

    private static <T> Set<T> withElement(final Set<T> set, final T elem) {
        set.add(elem);
        return set;
    }

    public static void main(final String[] args) {
        System.out.println("[\"eat\",\"oath\"] == " + new WordSearchTwo().findWords(
                new char[][]{
                        {'o', 'a', 'a', 'n'},
                        {'e', 't', 'a', 'e'},
                        {'i', 'h', 'k', 'r'},
                        {'i', 'f', 'l', 'v'}
                },
                new String[] { "oath","pea","eat","rain" }
        ));
        System.out.println("[] == " + new WordSearchTwo().findWords(
                new char[][]{
                        {'a', 'a'},
                },
                new String[] { "aaa" }
        ));
        System.out.println("[\"abcdefg\",\"befa\",\"eaabcdgfa\",\"gfedcbaaa\"] == " + new WordSearchTwo().findWords(
                new char[][]{
                        {'a', 'b', 'c'},
                        {'a', 'e', 'd'},
                        {'a', 'f', 'g'},
                },
                new String[] { "abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade" }
        ));
    }
}