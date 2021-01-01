import java.util.ArrayList;
import java.util.List;

/**
 * Problem #315
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 **/
public class CountOfSmallerNumbersAfterSelf {

    private int[] indexes;
    private int[] tempIndexes;
    private int[] counts;
    private int[] nums;

    public List<Integer> countSmaller(final int[] nums) {
        this.indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        this.tempIndexes = new int[nums.length];
        this.counts = new int[nums.length];
        this.nums = nums;

        mergeSort(0, nums.length);
        final List<Integer> result = new ArrayList<>(nums.length);
        for (final int count : counts) {
            result.add(count);
        }
        return result;
    }

    private void mergeSort(final int start, final int endExcl) {
        if (endExcl - start <= 1) {
            return;
        }

        final int mid = (start + endExcl) / 2;
        mergeSort(start, mid);
        mergeSort(mid, endExcl);

        int rightCount = 0;
        int p1 = start, p2 = mid, p = start;
        while (p1 < mid && p2 < endExcl) {
            if (nums[indexes[p1]] > nums[indexes[p2]]) {
                rightCount++;
                tempIndexes[p++] = indexes[p2++];
            } else {
                counts[indexes[p1]] += rightCount;
                tempIndexes[p++] = indexes[p1++];
            }
        }
        while (p1 < mid) {
            counts[indexes[p1]] += rightCount;
            tempIndexes[p++] = indexes[p1++];
        }
        while (p2 < endExcl) {
            tempIndexes[p++] = indexes[p2++];
        }
        for (int i = start; i < endExcl; i++) {
            indexes[i] = tempIndexes[i];
        }
    }

    public static void main(final String[] args) {
        System.out.println("[2,1,1,0] == " + new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{5, 2, 6, 1,}));
    }
}