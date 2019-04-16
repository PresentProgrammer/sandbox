import java.util.ArrayList;
import java.util.List;

/**
 * Problem #53
 * Time complexity: O(n^2)
 * Space complexity: O(n)
 **/
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int maxElement = nums[0];
        final List<Integer> list = new ArrayList<>();
        for (final int num : nums) {
            maxElement = Math.max(maxElement, num);
            list.add(num);
        }

        if (maxElement <= 0) {
            return maxElement;
        }

        int i = 0;
        while (i + 1 < list.size()) {
            final int curr = list.get(i);
            final int next = list.get(i + 1);
            if (curr <= 0 && next <= 0 || curr >= 0 && next >= 0) {
                list.set(i, curr + next);
                list.remove(i + 1);
                maxElement = Math.max(maxElement, list.get(i));
            } else {
                i++;
            }
        }

        if (list.size() > 2) {
            int prevSize;
            do {
                prevSize = list.size();
                int j = 1;
                while (j < list.size() - 1) {
                    final int left = list.get(j - 1);
                    final int curr = list.get(j);
                    final int right = list.get(j + 1);
                    if (Math.abs(left) >= Math.abs(curr) && Math.abs(right) >= Math.abs(curr)) {
                        list.set(j - 1, left + curr + right);
                        list.remove(j);
                        list.remove(j);
                        maxElement = Math.max(maxElement, list.get(j - 1));
                    } else {
                        j++;
                    }
                }
            } while (list.size() < prevSize);
        }

        for (final int el : list) {
            maxElement = Math.max(maxElement, el);
        }
        return maxElement;
    }

    public static void main(final String[] args) {
        System.out.println("-1 == " + new MaximumSubarray().maxSubArray(new int[]{ -1, -2 }));
        System.out.println("6 == " + new MaximumSubarray().maxSubArray(new int[]{ -2,1,-3,4,-1,2,1,-5,4 }));
        System.out.println("33 == " + new MaximumSubarray().maxSubArray(new int[]{ -9,-2,1,8,7,-6,4,9,-9,-5,0,5,-2,5,9,7 }));
        System.out.println("16 == " + new MaximumSubarray().maxSubArray(new int[]{ -5,8,-5,1,1,-3,5,5,-3,-3,6,4,-7,-4,-8,0,-1,-6 }));
        System.out.println("3 == " + new MaximumSubarray().maxSubArray(new int[]{ -1,1,-3,-2,2,-1,-2,1,2,-3 }));
	}
}