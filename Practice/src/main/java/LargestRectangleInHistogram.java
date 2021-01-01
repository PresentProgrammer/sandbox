import java.util.ArrayList;
import java.util.List;

/**
 * Problem #84
 * Time complexity: O(n ^ 2)
 * Space complexity: O(n ^ 2)
 **/
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        return largestRectangleArea(heights, 0, heights.length);
    }

    private int largestRectangleArea(final int[] heights, final int left, final int rightExcl) {
        if (left == rightExcl) {
            return 0;
        } else if (rightExcl - left == 1) {
            return heights[left];
        } else {
            final List<Integer> minIndexes = new ArrayList<>();
            int min = Integer.MAX_VALUE;
            for (int i = left; i < rightExcl; i++) {
                if (heights[i] < min) {
                    min = heights[i];
                    minIndexes.clear();
                    minIndexes.add(i);
                } else if (heights[i] == min) {
                    minIndexes.add(i);
                }
            }
            int maxArea = min * (rightExcl - left);
            int newLeft = left;
            for (final int minIndex : minIndexes) {
                maxArea = Math.max(maxArea, largestRectangleArea(heights, newLeft, minIndex));
                newLeft = minIndex + 1;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights, newLeft, rightExcl));
            return maxArea;
        }
    }

    public static void main(final String[] args) {
        System.out.println("10 == " + new LargestRectangleInHistogram().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println("0 == " + new LargestRectangleInHistogram().largestRectangleArea(new int[]{}));
        System.out.println(Integer.MAX_VALUE + " == " + new LargestRectangleInHistogram().largestRectangleArea(new int[]{Integer.MAX_VALUE}));
    }
}