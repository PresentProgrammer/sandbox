import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #350
 * Time complexity: O(n log n)
 * Space complexity: O(n)
 **/
public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
		    return new int[]{};
        }
		final int[] a = Arrays.copyOf(nums1, nums1.length);
		Arrays.sort(a);
        final int[] b = Arrays.copyOf(nums2, nums2.length);
        Arrays.sort(b);

        final List<Integer> intersection = new ArrayList<>();
        int aInd = 0, bInd = 0;
        while (aInd < a.length && bInd < b.length) {
            if (a[aInd] < b[bInd]) {
                aInd++;
            } else if (a[aInd] > b[bInd]) {
                bInd++;
            } else {
                intersection.add(a[aInd]);
                aInd++;
                bInd++;
            }
        }
        return intersection.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    public static void main(final String[] args) {
	}
}