import java.util.Arrays;

/**
 * Problem #493
 * Solved with modified Merge Sort.
 * Time complexity: O(N log N)
 * Space complexity: O(N)
 **/
public class ReversePairs_MergeSort {

    private int[] nums;
    private int[] aux;

    public int reversePairs(int[] nums) {
        this.nums = Arrays.copyOf(nums, nums.length);
        this.aux = new int[nums.length];
        return mergeSortWithCount(0, nums.length - 1);
    }

    public static void main(String[] args) {
        System.out.println("2 == " + new ReversePairs_MergeSort().reversePairs(new int[]{ 1, 3, 2, 3, 1 }));
    }

    private int mergeSortWithCount(int start, int endIncl) {
        if (start >= endIncl) {
            return 0;
        }

        final int mid = mid(start, endIncl);
        final int count = mergeSortWithCount(start, mid) + mergeSortWithCount(mid + 1, endIncl)
                + countImportantReversePairs(start, endIncl);
        merge(start, endIncl);
        return count;
    }

    private void merge(int start, int endIncl) {
        System.arraycopy(nums, start, aux, start, endIncl - start + 1);
        final int mid = mid(start, endIncl);
        int i = start;
        int j = mid + 1;
        int k = start;
        while (i <= mid && j <= endIncl) {
            if (aux[i] <= aux[j]) {
                nums[k++] = aux[i++];
            } else {
                nums[k++] = aux[j++];
            }
        }
        while (i <= mid) {
            nums[k++] = aux[i++];
        }
        // commented out since right part is copied from nums
        // while (j <= endIncl) {
        //     nums[k++] = aux[j++];
        // }
    }

    private int countImportantReversePairs(int start, int endIncl) {
        int count = 0;
        final int mid = mid(start, endIncl);
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= endIncl) {
            if (nums[i] <= (long) nums[j] * 2) {
                i++;
            } else {
                count += mid - i + 1;
                j++;
            }
        }
        return count;
    }

    private static int mid(int start, int endIncl) {
        return (start + endIncl) >>> 1;
    }
}