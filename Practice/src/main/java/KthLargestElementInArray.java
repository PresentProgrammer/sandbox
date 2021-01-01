import java.util.Random;

/**
 * Problem #215
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class KthLargestElementInArray {

    public int findKthLargest(int[] nums, int k) {
        return partitionUntilKLargestFound(shuffle(nums), k - 1);
    }

    private static int partitionUntilKLargestFound(final int[] nums, final int k) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            final int partitioningElement = nums[left];
            int i = left + 1, j = right;
            while (i <= j) {
                while (i <= j && nums[i] > partitioningElement) {
                    i++;
                }
                while (i <= j && nums[j] < partitioningElement) {
                    j--;
                }
                if (i < j) {
                    swap(nums, i, j);
                    i++;
                    j--;
                } else if (i == j) {
                    j--;
                }
            }
            swap(nums, left, j);
            if (j == k) {
                left = j;
                right = j;
            } else if (j < k) {
                left = j + 1;
            } else {
                right = j - 1;
            }
        }
        return nums[left];
    }

    private static int[] shuffle(final int[] original) {
        final Random rand = new Random();
        for (int i = 1; i < original.length; i++) {
            swap(original, i, rand.nextInt(i + 1));
        }
        return original;
    }

    private static void swap(final int[] nums, final int i, final int j) {
        final int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(final String[] args) {
        System.out.println("5 == " + new KthLargestElementInArray().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println("4 == " + new KthLargestElementInArray().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}