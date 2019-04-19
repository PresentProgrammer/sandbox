import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Problem #218
 * Time complexity: O(n ^ 2 log n)
 * Space complexity: O(n)
 **/
public class TheSkylineProblem {

    public List<int[]> getSkyline(int[][] buildings) {
		final TreeMap<Integer, Integer> keyPoints = new TreeMap<>();
		for (final int[] building : buildings) {
		    final int left = building[0];
		    final int right = building[1];
		    final int h = building[2];

		    int last = keyPoints.lowerEntry(left) == null ? 0 : keyPoints.lowerEntry(left).getValue();

		    if (keyPoints.containsKey(left)) {
		        last = keyPoints.get(left);
                if (keyPoints.get(left) < h) {
                    final Map.Entry<Integer, Integer> prev = keyPoints.lowerEntry(left);
                    if (prev != null && prev.getValue() == h) {
                        keyPoints.remove(left);
                    } else {
                        keyPoints.put(left, h);
                    }
                }
            } else {
		        final Map.Entry<Integer, Integer> prev = keyPoints.lowerEntry(left);
		        if (prev == null || prev.getValue() < h) {
		            keyPoints.put(left, h);
                }
            }

		    final Iterator<Map.Entry<Integer, Integer>> leftToRightIter = keyPoints.subMap(left + 1, right)
                    .entrySet()
                    .iterator();
		    while (leftToRightIter.hasNext()) {
		        final Map.Entry<Integer, Integer> curr = leftToRightIter.next();
		        last = curr.getValue();
		        final int prevH = keyPoints.lowerEntry(curr.getKey()).getValue();
                if (curr.getValue() == h) {
                    if (prevH >= h) {
                        leftToRightIter.remove();
                    }
                } else if (curr.getValue() < h) {
                    if (prevH == h) {
                        leftToRightIter.remove();
                    } else {
                        curr.setValue(h);
                    }
                }
            }

		    if (!keyPoints.containsKey(right) && last < h) {
		        keyPoints.put(right, last);
            }
        }

        return keyPoints.entrySet().stream()
                .map(entry -> new int[]{ entry.getKey(), entry.getValue() })
                .collect(Collectors.toList());
    }
    
    public static void main(final String[] args) {
        final List<int[]> keyPoints = new TheSkylineProblem().getSkyline(new int[][]{
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}
        });
        System.out.println("[2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] == ");
        for (final int[] keyPoint : keyPoints) {
            System.out.println(Arrays.toString(keyPoint));
        }
        System.out.println();

        final List<int[]> keyPoints2 = new TheSkylineProblem().getSkyline(new int[][]{
                {0, 2, 3},
                {2, 5, 3},
        });
        System.out.println("[0, 3], [5, 0] == ");
        for (final int[] keyPoint : keyPoints2) {
            System.out.println(Arrays.toString(keyPoint));
        }
        System.out.println();
	}
}