import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Problem #843
 * Time complexity: O(? * N ^ 2)
 * Space complexity: O(N ^ 2)
 **/
public class GuessTheWord {

    private static final int STR_LENGTH = 6;

    public void findSecretWord(String[] wordList, Master master) {
        final int[][] matches = initMatches(wordList);
        List<Integer> currWords = IntStream.range(0, wordList.length)
                .boxed()
                .collect(Collectors.toList());
        while (true) {
            int maxChildren = Integer.MAX_VALUE;
            int maxWord = -1;
            List<List<Integer>> maxWordChildren = null;
            for (final Integer currWord : currWords) {
                List<List<Integer>> children = initEmptyChildren();
                for (final Integer secondWord : currWords) {
                    children.get(matches[currWord][secondWord]).add(secondWord);
                }
                final int currMaxChildren = children.stream()
                        .map(List::size)
                        .max(Comparator.naturalOrder())
                        .orElseThrow(RuntimeException::new);
                if (currMaxChildren < maxChildren) {
                    maxChildren = currMaxChildren;
                    maxWord = currWord;
                    maxWordChildren = children;
                }
            }
            final int guessed = master.guess(wordList[maxWord]);
            if (guessed == STR_LENGTH) {
                return;
            } else {
                currWords = maxWordChildren.get(guessed);
            }
        }
    }

    private int[][] initMatches(String[] words) {
        final int[][] matches = new int[words.length][words.length];
        for (int i = 0; i < words.length; i++) {
            matches[i][i] = STR_LENGTH;
            for (int j = i + 1; j < words.length; j++) {
                final int ijMatches = countMatches(words[i], words[j]);
                matches[i][j] = ijMatches;
                matches[j][i] = ijMatches;
            }
        }
        return matches;
    }

    private static int countMatches(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < STR_LENGTH; i++) {
            count += s1.charAt(i) == s2.charAt(i) ? 1 : 0;
        }
        return count;
    }

    private static List<List<Integer>> initEmptyChildren() {
        return Stream.generate(ArrayList<Integer>::new)
                .limit(STR_LENGTH + 1)
                .collect(Collectors.toList());
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