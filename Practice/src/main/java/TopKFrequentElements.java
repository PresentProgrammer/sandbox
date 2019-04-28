import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Problem #347
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 **/
public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
		final Map<Integer, Integer> frequencies = new HashMap<>();
		for (final int num : nums) {
		    frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }

		final TreeMap<Integer, List<Integer>> sortedByFrequency = new TreeMap<>();
		for (final Map.Entry<Integer, Integer> frequency : frequencies.entrySet()) {
			final List<Integer> elements = sortedByFrequency.getOrDefault(frequency.getValue(), new ArrayList<>());
			elements.add(frequency.getKey());
		    sortedByFrequency.put(frequency.getValue(), elements);
        }

		final List<Integer> result = new ArrayList<>();
		while (result.size() < k) {
			final List<Integer> elements = sortedByFrequency.pollLastEntry().getValue();
			for (final Integer element : elements) {
				result.add(element);
				if (result.size() >= k) {
					return result;
				}
			}
		}
		return result;
    }

    public static void main(final String[] args) {
		System.out.println("[1, 2] == " + new TopKFrequentElements().topKFrequent(new int[]{ 1,1,1,2,2,3 }, 2));
		System.out.println("[1] == " + new TopKFrequentElements().topKFrequent(new int[]{ 1 }, 1));
		System.out.println("[1, 2] == " + new TopKFrequentElements().topKFrequent(new int[]{ 1, 2 }, 2));
	}
}