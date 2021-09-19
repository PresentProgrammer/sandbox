import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem #244
 * Time complexity: O(N + R * S) - where N is number of words in the dictionary
 *                                       R is max count of repetitions
 *                                       S is number of calls to `shortest`
 * Space complexity: O(N + min(S, M^2)) - where N is number of words in the dictionary
 *                                              M is number of distinct words in dictionary (for memo)
 *                                              S is number of calls to `shortest`
 **/
public class ShortestWordDistanceII {

    private final Map<String, List<Integer>> map = new HashMap<>();
    private final Map<String, Integer> memo = new HashMap<>();

    public ShortestWordDistanceII(String[] wordsDict) {
        for (int i = 0; i < wordsDict.length; i++) {
            final String word = wordsDict[i];
            map.computeIfAbsent(word, unused -> new ArrayList<>())
                    .add(i);
        }
    }

    public int shortest(String word1, String word2) {
        if (word1.compareTo(word2) < 0) {
            return shortest(word2, word1);
        }
        return memo.computeIfAbsent(word1 + "," + word2, unused -> shortest0(word1, word2));
    }

    private int shortest0(String word1, String word2) {
        final List<Integer> l1 = map.get(word1);
        final List<Integer> l2 = map.get(word2);
        int p1 = 0;
        int p2 = 0;
        int shortest = Integer.MAX_VALUE;
        while (p1 < l1.size() && p2 < l2.size() && shortest > 1) {
            final int elem1 = l1.get(p1);
            final int elem2 = l2.get(p2);
            shortest = Math.min(shortest, Math.abs(elem1 - elem2));
            if (elem1 < elem2) {
                p1++;
            } else {
                p2++;
            }
        }
        return shortest;
    }

    public static void main(final String[] args) {
        final ShortestWordDistanceII swd = new ShortestWordDistanceII(new String[]{ "this", "is", "a", "long", "run",
                "sentence", "is", "fun", "day", "today", "sunny", "weather", "is", "a", "day", "tuesday", "this",
                "sentence", "running", "rainy"
        });
        final List<Integer> actual = List.of(
                swd.shortest("this", "is"),
                swd.shortest("sentence", "a"),
                swd.shortest("is", "a"),
                swd.shortest("this", "sentence"),
                swd.shortest("weather", "long"),
                swd.shortest("day", "sentence"),
                swd.shortest("rainy", "tuesday"),
                swd.shortest("this", "rainy"),
                swd.shortest("running", "run")
        );
        System.out.println("[1, 3, 1, 1, 8, 3, 4, 3, 14] == " + actual);
    }
}