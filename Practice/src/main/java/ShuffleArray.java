import java.util.Arrays;
import java.util.Random;

/**
 * Problem #384
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ShuffleArray {

    private static class Solution {

        private final int[] original;

        public Solution(int[] nums) {
            this.original = Arrays.copyOf(nums, nums.length);
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return Arrays.copyOf(original, original.length);
        }

        /**
         * Returns a random shuffling of the array.
         */
        public int[] shuffle() {
            final int[] copy = Arrays.copyOf(original, original.length);
            final Random rand = new Random();
            for (int i = 1; i < copy.length; i++) {
                swap(copy, i, rand.nextInt(i + 1));
            }
            return copy;
        }

        private static void swap(final int[] nums, final int i, final int j) {
            final int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}