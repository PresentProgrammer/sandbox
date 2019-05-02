import java.util.ArrayList;
import java.util.List;

/**
 * Problem #300
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 **/
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        final List<Integer> sequences = new ArrayList<>();
        for (int i = nums == null ? -1 : nums.length - 1; i >= 0; i--) {
            final int num = nums[i];
            int left = 0, right = sequences.size() - 1;
            while (left <= right) {
                final int mid = (left + right) / 2;
                final int midValue = sequences.get(mid);
                if (midValue == num) {
                    left = mid;
                    right = mid - 1;
                } else if (midValue < num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (left == sequences.size()) {
                sequences.add(num);
            } else {
                sequences.set(left, num);
            }
        }
        return sequences.size();
    }
    
    public static void main(final String[] args) {
        System.out.println("4 == " + new LongestIncreasingSubsequence().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println("2 == " + new LongestIncreasingSubsequence().lengthOfLIS(new int[]{18,18,20}));
        System.out.println("3 == " + new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1,2,1,3}));
        System.out.println("6 == " + new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1,3,6,7,9,4,10,5,6}));
	}
}