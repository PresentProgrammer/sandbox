import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Problem #692
 * Time complexity: O(n log k)
 * Space complexity: O(n)
 **/
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        final Map<String, Integer> map = new HashMap<>();
        for (final String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        final PriorityQueue<String> heap = new PriorityQueue<>((left, right) -> {
            final Integer leftCount = map.get(left);
            final Integer rightCount = map.get(right);
            return leftCount.equals(rightCount) ? -left.compareTo(right) : leftCount - rightCount;
        });
        for (final String word : map.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        final List<String> result = new ArrayList<>(k);
        while (heap.size() > 0) {
            result.add(heap.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(final String[] args) {
        System.out.println("[\"i\", \"love\"] == " + new TopKFrequentWords().topKFrequent(
                new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println("[\"the\", \"is\", \"sunny\", \"day\"] == " + new TopKFrequentWords().topKFrequent(
                new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
    }
}