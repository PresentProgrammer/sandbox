import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Problem #347
 * Time complexity: O(n log k)
 * Space complexity: O(n)
 **/
public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
		final Map<Integer, Integer> frequencies = new HashMap<>();
		for (final int num : nums) {
		    frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }

		final PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
		    @Override
            public int compare(Integer n1, Integer n2) {
		        return frequencies.get(n1) - frequencies.get(n2);
            }
        });
		for (final Integer element : frequencies.keySet()) {
			queue.offer(element);
			if (queue.size() > k) {
				queue.poll();
			}
        }

		final List<Integer> result = new ArrayList<>();
		while (!queue.isEmpty()) {
			result.add(queue.poll());
		}
		Collections.reverse(result);
		return result;
    }

    public static void main(final String[] args) {
		System.out.println("[1, 2] == " + new TopKFrequentElements().topKFrequent(new int[]{ 1,1,1,2,2,3 }, 2));
		System.out.println("[1] == " + new TopKFrequentElements().topKFrequent(new int[]{ 1 }, 1));
		System.out.println("[1, 2] == " + new TopKFrequentElements().topKFrequent(new int[]{ 1, 2 }, 2));
	}
}