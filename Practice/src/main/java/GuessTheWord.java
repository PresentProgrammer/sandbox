import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #843
 * Time complexity: O()
 * Space complexity: O()
 **/
public class GuessTheWord {

    private static final int STR_LENGTH = 6;
    private static final int MAX_DEPTH = 10;

    public void findSecretWord(String[] wordList, Master master) {
        int depth = 0;
        List<String> currWords = Arrays.asList(wordList);
        for (int i = 0; i < 0; i++) {
            final String chosen = currWords.get(0);
            final int matches = master.guess(chosen);
            if (matches == 6) {
                return;
            } else {
                currWords = filterByMatch(currWords, chosen, matches);
                depth++;
            }
        }

        while (depth < MAX_DEPTH) {
            final String chosen = chooseWord(currWords, depth);
            final int matches = master.guess(chosen);
            if (matches == 6) {
                return;
            } else {
                currWords = filterByMatch(currWords, chosen, matches);
                depth++;
            }
        }
    }

    private static String chooseWord(List<String> words, int depth) {
        for (final String word : words) {
            if (goodToGo(words, word, depth + 1)) {
                return word;
            }
        }
        return null;
    }

    private static boolean goodToGo(List<String> words, String chosen, int depth) {
        if (depth > MAX_DEPTH) {
            return false;
        } else {
            for (int matches = 0; matches < STR_LENGTH; matches++) {
                final List<String> filteredWords = filterByMatch(words, chosen, matches);
                if (!filteredWords.isEmpty()) {
                    final String nextChosen = chooseWord(filteredWords, depth);
                    if (nextChosen == null) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private static List<String> filterByMatch(List<String> words, String chosen, int matches) {
        final List<String> filtered = new ArrayList<>();
        for (final String word : words) {
            if (countMatches(word, chosen) == matches) {
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
        final String[] words = { "wichbx","oahwep","tpulot","eqznzs","vvmplb","eywinm","dqefpt","kmjmxr","ihkovg","trbzyb","xqulhc","bcsbfw","rwzslk","abpjhw","mpubps","viyzbc","kodlta","ckfzjh","phuepp","rokoro","nxcwmo","awvqlr","uooeon","hhfuzz","sajxgr","oxgaix","fnugyu","lkxwru","mhtrvb","xxonmg","tqxlbr","euxtzg","tjwvad","uslult","rtjosi","hsygda","vyuica","mbnagm","uinqur","pikenp","szgupv","qpxmsw","vunxdn","jahhfn","kmbeok","biywow","yvgwho","hwzodo","loffxk","xavzqd","vwzpfe","uairjw","itufkt","kaklud","jjinfa","kqbttl","zocgux","ucwjig","meesxb","uysfyc","kdfvtw","vizxrv","rpbdjh","wynohw","lhqxvx","kaadty","dxxwut","vjtskm","yrdswc","byzjxm","jeomdc","saevda","himevi","ydltnu","wrrpoc","khuopg","ooxarg","vcvfry","thaawc","bssybb","ccoyyo","ajcwbj","arwfnl","nafmtm","xoaumd","vbejda","kaefne","swcrkh","reeyhj","vmcwaf","chxitv","qkwjna","vklpkp","xfnayl","ktgmfn","xrmzzm","fgtuki","zcffuv","srxuus","pydgmq" };
        final Master master = new Master("ccoyyo");
        final Instant start = Instant.now();
        new GuessTheWord().findSecretWord(words, master);
        System.out.println("Time taken: " + Duration.between(start, Instant.now()));
        System.out.println("Guesses: " + master.guesses);
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