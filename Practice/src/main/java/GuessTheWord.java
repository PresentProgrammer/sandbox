import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Problem #843
 * Time complexity: O(N ^ 2 * log(N))
 * Space complexity: O(N ^ 2)
 **/
public class GuessTheWord {

    private static final int WORD_LENGTH = 6;

    public void findSecretWord(String[] wordList, Master master) {
        List<String> candidates = Stream.of(wordList).collect(Collectors.toUnmodifiableList());
        while (candidates.size() > 1) {
            final Map<String, Map<Integer, List<String>>> map = buildMap(candidates);
            final String bestChoice = bestChoice(map);
            final int guessResult = master.guess(bestChoice);
            if (guessResult == WORD_LENGTH) {
                return;
            } else {
                candidates = map.get(bestChoice).getOrDefault(guessResult, Collections.emptyList());
            }
        }
        if (candidates.size() == 1) {
            master.guess(candidates.get(0));
        }
    }

    private static Map<String, Map<Integer, List<String>>> buildMap(List<String> words) {
        final Map<String, Map<Integer, List<String>>> map = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            final String left = words.get(i);
            for (int j = i + 1; j < words.size(); j++) {
                final String right = words.get(j);
                final int matches = countMatches(left, right);
                addToMap(map, left, matches, right);
                addToMap(map, right, matches, left);
            }
        }
        return map;
    }

    private static int countMatches(String left, String right) {
        int count = 0;
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (left.charAt(i) == right.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    private static void addToMap(Map<String, Map<Integer, List<String>>> map, String key, int matches, String value) {
        map.computeIfAbsent(key, ignored -> new HashMap<>())
                .computeIfAbsent(matches, ignored -> new ArrayList<>())
                .add(value);
    }

    private static String bestChoice(Map<String, Map<Integer, List<String>>> map) {
        String best = null;
        int maxSize = Integer.MAX_VALUE;
        for (final String candidate : map.keySet()) {
            int maxListSize = Integer.MIN_VALUE;
            for (final List<String> list : map.get(candidate).values()) {
                maxListSize = Math.max(maxListSize, list.size());
            }
            if (maxListSize < maxSize) {
                maxSize = maxListSize;
                best = candidate;
            }
        }
        return best;
    }

    public static void main(final String[] args) {
        final String[] words = {"gaxckt", "trlccr", "jxwhkz", "ycbfps", "peayuf", "yiejjw", "ldzccp", "nqsjoa", "qrjasy", "pcldos", "acrtag", "buyeia", "ubmtpj", "drtclz", "zqderp", "snywek", "caoztp", "ibpghw", "evtkhl", "bhpfla", "ymqhxk", "qkvipb", "tvmued", "rvbass", "axeasm", "qolsjg", "roswcb", "vdjgxx", "bugbyv", "zipjpc", "tamszl", "osdifo", "dvxlxm", "iwmyfb", "wmnwhe", "hslnop", "nkrfwn", "puvgve", "rqsqpq", "jwoswl", "tittgf", "evqsqe", "aishiv", "pmwovj", "sorbte", "hbaczn", "coifed", "hrctvp", "vkytbw", "dizcxz", "arabol", "uywurk", "ppywdo", "resfls", "tmoliy", "etriev", "oanvlx", "wcsnzy", "loufkw", "onnwcy", "novblw", "mtxgwe", "rgrdbt", "ckolob", "kxnflb", "phonmg", "egcdab", "cykndr", "lkzobv", "ifwmwp", "jqmbib", "mypnvf", "lnrgnj", "clijwa", "kiioqr", "syzebr", "rqsmhg", "sczjmz", "hsdjfp", "mjcgvm", "ajotcx", "olgnfv", "mjyjxj", "wzgbmg", "lpcnbj", "yjjlwn", "blrogv", "bdplzs", "oxblph", "twejel", "rupapy", "euwrrz", "apiqzu", "ydcroj", "ldvzgq", "zailgu", "xgqpsr", "wxdyho", "alrplq", "brklfk"};
        final Master master = new Master("hbaczn");
        final Instant start = Instant.now();
        new GuessTheWord().findSecretWord(words, master);
        System.out.println("Time taken: " + Duration.between(start, Instant.now()));
        System.out.println("Guesses: " + master.guesses);
        System.out.println("Guessed: " + master.guessed);
    }

    private static class Master {

        private final String secret;
        private boolean guessed = false;
        private int guesses = 0;

        Master(String secret) {
            this.secret = secret;
        }

        public int guess(String word) {
            if (secret.equals(word)) {
                guessed = true;
            }
            guesses++;
            if (guesses > 10) {
                throw new RuntimeException("Too many guesses");
            }
            return countMatches(secret, word);
        }
    }
}