import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Problem #315
 * Time complexity: O()
 * Space complexity: O()
 **/
public class CountOfSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(final int[] nums) {
		final List<Integer> result = new ArrayList<>();
		final TreeMap<Integer, Integer> lessOrEqualCounts = new TreeMap<>();
		for (int i = nums.length - 1; i >= 0; i--) {
			final Map.Entry<Integer, Integer> less = lessOrEqualCounts.lowerEntry(nums[i]);
			final int lessCount = less == null ? 0 : less.getValue();
			result.add(lessCount);
			lessOrEqualCounts.put(nums[i], lessOrEqualCounts.getOrDefault(nums[i], lessCount) + 1);
			for (final Map.Entry<Integer, Integer> tailEntry : lessOrEqualCounts.tailMap(nums[i], false).entrySet()) {
				tailEntry.setValue(tailEntry.getValue() + 1);
			}
        }
        Collections.reverse(result);
		return result;
    }
    
    public static void main(final String[] args) {
        System.out.println("[2,1,1,0] == " + new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{ 5, 2, 6, 1, }));
	}
}