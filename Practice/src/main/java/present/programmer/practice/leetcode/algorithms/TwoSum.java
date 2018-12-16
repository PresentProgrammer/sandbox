import java.util.Arrays;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[] { i, j };
                }
            }
        }
	return new int[] {};
    }
    
    public static void main(final String... args) {
        System.out.println(Arrays.toString(new TwoSum().twoSum(new int[] { 2, 7, 11, 15 }, 9)));
    }
}