import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem #347
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class TopKFrequentElements {

    @SuppressWarnings("unchecked")
    public List<Integer> topKFrequent(int[] nums, int k) {
        final Map<Integer, Integer> frequencies = new HashMap<>();
        for (final int num : nums) {
            frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        }

        final List<Integer>[] buckets = new List[nums.length + 1];

        for (final Map.Entry<Integer, Integer> frequency : frequencies.entrySet()) {
            List<Integer> bucket = buckets[frequency.getValue()] == null ? new ArrayList<>() : buckets[frequency.getValue()];
            bucket.add(frequency.getKey());
            buckets[frequency.getValue()] = bucket;
        }

        final List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                for (final Integer element : buckets[i]) {
                    result.add(element);
                    if (result.size() >= k) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println("[1, 2] == " + new TopKFrequentElements().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
        System.out.println("[1] == " + new TopKFrequentElements().topKFrequent(new int[]{1}, 1));
        System.out.println("[1, 2] == " + new TopKFrequentElements().topKFrequent(new int[]{1, 2}, 2));
    }
}