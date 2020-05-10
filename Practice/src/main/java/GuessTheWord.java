import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

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
        boolean[] currWords = new boolean[words.length];
        Arrays.fill(currWords, true);
        while (depth < MAX_DEPTH) {
            final int chosen = chooseWord(currWords, depth);
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

    private int chooseWord(boolean[] currWords, int depth) {
        for (int chosen = 0; chosen < currWords.length; chosen++) {
            if (currWords[chosen]) {
                if (goodToGo(currWords, chosen, depth + 1)) {
                    return chosen;
                }
            }
        }
        return -1;
    }

    private boolean goodToGo(boolean[] currWords, int chosen, int depth) {
        if (depth > MAX_DEPTH) {
            return false;
        } else {
            for (int match = 0; match < STR_LENGTH; match++) {
                final boolean[][] matchToFilteredWords = filterByMatches(currWords, chosen);
                for (final boolean[] filteredWords : matchToFilteredWords) {
                    if (hasWords(filteredWords)) {
                        if (chooseWord(filteredWords, depth) == -1) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    private boolean[][] filterByMatches(boolean[] currWords, int chosen) {
        final boolean[][] filtered = new boolean[STR_LENGTH][words.length];
        for (int word = 0; word < words.length; word++) {
            if (currWords[word] && word != chosen) {
                filtered[matches[word][chosen]][word] = true;
            }
        }
        return filtered;
    }

    private boolean[] filterByMatches(boolean[] currWords, int chosen, int match) {
        final boolean[] filtered = new boolean[words.length];
        for (int word = 0; word < words.length; word++) {
            if (currWords[word] && word != chosen) {
                filtered[word] = matches[word][chosen] == match;
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

    private static boolean hasWords(boolean[] currWords) {
        for (final boolean word : currWords) {
            if (word) {
                return true;
            }
        }
        return false;
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