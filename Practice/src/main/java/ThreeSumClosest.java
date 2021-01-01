import java.util.Arrays;

/**
 * Problem #16
 * Time complexity: O(n^2 * log(n))
 * Space complexity: O(n) for sorting.
 **/
public class ThreeSumClosest {

    public int threeSumClosest(final int[] nums, final int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                final int twoSum = nums[i] + nums[j];
                final int thirdIndex = Arrays.binarySearch(nums, j + 1, nums.length, target - twoSum);
                if (thirdIndex > 0) {
                    return target;
                } else {
                    final int insertionPoint = -(thirdIndex + 1);
                    if (insertionPoint > j + 1) {
                        final int threeSum = twoSum + nums[insertionPoint - 1];
                        if (Math.abs(threeSum - target) < Math.abs(result - target)) {
                            result = threeSum;
                        }
                    }
                    if (insertionPoint < nums.length) {
                        final int threeSum = twoSum + nums[insertionPoint];
                        if (Math.abs(threeSum - target) < Math.abs(result - target)) {
                            result = threeSum;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println("2 == " + new ThreeSumClosest().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }
}