import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Problem #642
 * Time complexity: O()
 * Space complexity: O()
 **/
public class AutocompleteSystem {

    private final Map<String, Integer> sentCounts = new HashMap<>();
    private StringBuilder strBuilder = new StringBuilder();
    private Set<String> candidates;

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            this.sentCounts.put(sentences[i], this.sentCounts.getOrDefault(sentences[i], 0) + times[i]);
        }
        this.candidates = this.sentCounts.keySet();
    }

    public List<String> input(char input) {
        if (input == '#') {
            final String sentence = strBuilder.toString();
            sentCounts.put(sentence, sentCounts.getOrDefault(sentence, 0) + 1);
            strBuilder = new StringBuilder();
            candidates = sentCounts.keySet();
            return List.of();
        }

        strBuilder.append(input);
        final String prefix = strBuilder.toString();
        candidates = candidates.stream()
                .filter(c -> c.startsWith(prefix))
                .collect(Collectors.toUnmodifiableSet());

        return candidates.stream()
//                .sorted(Comparator.<String>comparingInt(sentCounts::get).reversed()
//                        .thenComparing(Comparator.naturalOrder()))
                .sorted((a, b) -> {
                    final int aCount = sentCounts.get(a);
                    final int bCount = sentCounts.get(b);
                    if (aCount == bCount) {
                        return a.compareTo(b);
                    } else {
                        return Integer.compare(bCount, aCount);
                    }
                })
                .limit(3)
                .collect(Collectors.toList());
    }
}