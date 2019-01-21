package present.programmer.practice.leetcode.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        final Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            final Integer current = nums[i];
            final Integer needed = target - current;
            if (map.get(needed) == null) {
                map.put(current, i);
            } else {
                return new int[] { map.get(needed), i };
            }
        }
        return new int[] {};        
    }
    
    public static void main(final String... args) {
        System.out.println(Arrays.toString(new TwoSum().twoSum(new int[] { 2, 7, 11, 15 }, 9)));
    }
}