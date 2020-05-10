import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem #843
 * Time complexity: O()
 * Space complexity: O()
 **/
public class GuessTheWord {

    private static final int STR_LENGTH = 6;
    private static final int MAX_DEPTH = 10;

    private String[] words;
    private int[][] matches;

    public void findSecretWord(String[] wordList, Master master) {
        init(wordList);
        int depth = 0;
        List<Integer> currWords = allWords();
        while (depth < MAX_DEPTH) {
            final Integer chosen = chooseWord(currWords, depth);
            final int matches = master.guess(words[chosen]);
            if (matches == 6) {
                return;
            } else {
                currWords = filterByMatches(currWords, chosen, matches);
                depth++;
            }
        }
    }

    private void init(String[] words) {
        this.words = words;
        this.matches = new int[words.length][words.length];
        for (int i = 0; i < words.length; i++) {
            this.matches[i][i] = STR_LENGTH;
            for (int j = i + 1; j < words.length; j++) {
                final int ijMatches = countMatches(words[i], words[j]);
                this.matches[i][j] = ijMatches;
                this.matches[j][i] = ijMatches;
            }
        }
    }

    private List<Integer> allWords() {
        final List<Integer> currWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            currWords.add(i);
        }
        return currWords;
    }

    private Integer chooseWord(List<Integer> words, int depth) {
        for (final Integer word : words) {
            if (goodToGo(words, word, depth + 1)) {
                return word;
            }
        }
        return null;
    }

    private boolean goodToGo(List<Integer> currWords, Integer chosen, int depth) {
        if (depth > MAX_DEPTH) {
            return false;
        } else {
            for (int match = 0; match < STR_LENGTH; match++) {
                final List<Integer> filteredWords = filterByMatches(currWords, chosen, match);
                if (!filteredWords.isEmpty()) {
                    final Integer nextChosen = chooseWord(filteredWords, depth);
                    if (nextChosen == null) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private List<Integer> filterByMatches(List<Integer> currWords, Integer chosen, int match) {
        final List<Integer> filtered = new ArrayList<>();
        for (final Integer word : currWords) {
            if (matches[word][chosen] == match) {
                filtered.add(word);
            }
        }
        return filtered;
    }

    private static int countMatches(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < STR_LENGTH; i++) {
            count += s1.charAt(i) == s2.charAt(i) ? 1 : 0;
        }
        return count;
    }

    public static void main(final String[] args) {
        final String[] words = { "gaxckt","trlccr","jxwhkz","ycbfps","peayuf","yiejjw","ldzccp","nqsjoa","qrjasy","pcldos","acrtag","buyeia","ubmtpj","drtclz","zqderp","snywek","caoztp","ibpghw","evtkhl","bhpfla","ymqhxk","qkvipb","tvmued","rvbass","axeasm","qolsjg","roswcb","vdjgxx","bugbyv","zipjpc","tamszl","osdifo","dvxlxm","iwmyfb","wmnwhe","hslnop","nkrfwn","puvgve","rqsqpq","jwoswl","tittgf","evqsqe","aishiv","pmwovj","sorbte","hbaczn","coifed","hrctvp","vkytbw","dizcxz","arabol","uywurk","ppywdo","resfls","tmoliy","etriev","oanvlx","wcsnzy","loufkw","onnwcy","novblw","mtxgwe","rgrdbt","ckolob","kxnflb","phonmg","egcdab","cykndr","lkzobv","ifwmwp","jqmbib","mypnvf","lnrgnj","clijwa","kiioqr","syzebr","rqsmhg","sczjmz","hsdjfp","mjcgvm","ajotcx","olgnfv","mjyjxj","wzgbmg","lpcnbj","yjjlwn","blrogv","bdplzs","oxblph","twejel","rupapy","euwrrz","apiqzu","ydcroj","ldvzgq","zailgu","xgqpsr","wxdyho","alrplq","brklfk" };
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