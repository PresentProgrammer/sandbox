import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

/**
 * Problem #15
 * Time complexity: O(n * log(n)) for TreeSet creation + O(n) for Map creation + O(n^2 * log(n)^2) for finding results
 *     → O(n^2 * log(n)^2).
 * Space complexity: O(n) - for Map and O(n) for Tree → O(n).
 **/
public class ThreeSum {

    public List<List<Integer>> threeSum(final int[] originalNums) {
        if (originalNums.length < 3) {
            return Collections.emptyList();
        }
        final List<List<Integer>> result = new ArrayList<>();
        final TreeSet<Integer> nums = IntStream.of(originalNums)
                .boxed()
                .collect(toCollection(TreeSet::new));
		Map<Integer, Integer> numCount = IntStream.of(originalNums)
                .boxed()
                .collect(toMap(num -> num, ignoredNum -> 1, (oldValue, newValue) -> oldValue + newValue));
		int left = nums.first();
		int right = nums.last();
		while (left < 0 && right > 0) {
            if (right > -left * 2) {
                right = nums.lower(right);
            } else {
                int currRight = right;
                if (currRight == -left * 2) {
                    if (numCount.getOrDefault(left, 0) > 1) {
                        result.add(Arrays.asList(left, left, currRight));
                    }
                    currRight = nums.lower(currRight);
                }
                while (currRight * 2 > -left) {
                    final int mid = -(left + currRight);
                    if (numCount.get(mid) != null) {
                        result.add(Arrays.asList(left, mid, currRight));
                    }
                    currRight = nums.lower(currRight);
                }
                if (currRight * 2 == -left && numCount.getOrDefault(currRight, 0) > 1) {
                    result.add(Arrays.asList(left, currRight, currRight));
                }
                left = nums.higher(left);
            }
        }
		if (numCount.getOrDefault(0, 0) > 2) {
		    result.add(Arrays.asList(0, 0, 0));
        }
		return result;
    }
	
	public static void main(final String[] args) {
        System.out.println("[[-1, 0, 1], [-1, -1, 2]] == " + new ThreeSum().threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
        System.out.println("[[1, 1, -2]] == " + new ThreeSum().threeSum(new int[] { 1, 1, -2 }));
	}
}