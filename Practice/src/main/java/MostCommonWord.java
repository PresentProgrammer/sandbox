import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Problem #819
 * Time complexity: O(par.length() + banned.size())
 * Space complexity: O(par.length() + banned.size())
 **/
public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        final Set<String> bannedSet = Arrays.stream(banned)
                .collect(Collectors.toCollection(HashSet::new));
        return Pattern.compile("[^a-zA-Z]+").splitAsStream(paragraph)
                .map(String::toLowerCase)
                .filter(word -> !bannedSet.contains(word))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(IllegalArgumentException::new);
    }
}