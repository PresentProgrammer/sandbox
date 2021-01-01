import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem #42
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class TrappingRainWater_My {

    private static class LocalMaximum {
        final int height;
        final int index;

        private LocalMaximum(final int height, final int index) {
            this.height = height;
            this.index = index;
        }
    }

    private static class LocalMaximumList extends ArrayList<LocalMaximum> {

        LocalMaximumList(int initialSize) {
            super(initialSize);
        }

        int lastHeight() {
            return get(size() - 1).height;
        }

        int lastButOneHeight() {
            return get(size() - 2).height;
        }

        void removeLast() {
            remove(size() - 1);
        }
    }

    public int trap(final int[] heights) {
        final List<LocalMaximum> maximums = getMeaningfulLocalMaximums(heights);
        int result = 0;
        for (int i = 1; i < maximums.size(); i++) {
            final LocalMaximum left = maximums.get(i - 1);
            final LocalMaximum right = maximums.get(i);
            final int waterLevel = Math.min(left.height, right.height);
            for (int j = left.index + 1; j < right.index; j++) {
                final int diff = waterLevel - heights[j];
                if (diff > 0) {
                    result += diff;
                }
            }
        }
        return result;
    }

    private static List<LocalMaximum> getMeaningfulLocalMaximums(final int[] heights) {
        if (heights.length < 3) {
            return Collections.emptyList();
        }
        final LocalMaximumList maximums = new LocalMaximumList(heights.length / 2);
        if (heights[0] > heights[1]) {
            maximums.add(new LocalMaximum(heights[0], 0));
        }
        for (int i = 1; i < heights.length; i++) {
            final int currHeight = heights[i];
            if (heights[i - 1] <= currHeight) {
                if (i + 1 < heights.length) {
                    if (heights[i + 1] < currHeight) {
                        update(maximums, currHeight, i);
                    }
                } else {
                    if (currHeight > 0) {
                        update(maximums, currHeight, i);
                    }
                }
            }
        }
        return maximums;
    }

    private static void update(final LocalMaximumList maximums, final int currHeight, final int currIndex) {
        while (maximums.size() > 1 && maximums.lastHeight() <= currHeight && maximums.lastHeight() <= maximums.lastButOneHeight()) {
            maximums.removeLast();
        }
        maximums.add(new LocalMaximum(currHeight, currIndex));
    }

    public static void main(final String[] args) {
        System.out.println("6 == " + new TrappingRainWater_My().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println("1 == " + new TrappingRainWater_My().trap(new int[]{5, 4, 1, 2}));
    }
}