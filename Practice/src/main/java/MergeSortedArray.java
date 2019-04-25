import java.util.Arrays;

/**
 * Problem #88
 * Time complexity: O(m + n)
 * Space complexity: O(m)
 **/
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
		final int[] nums1Copy = Arrays.copyOf(nums1, m);
        int p1 = 0;
		int p2 = 0;
		int p = 0;
		while (p1 < m && p2 < n) {
		    nums1[p++] = nums1Copy[p1] <= nums2[p2] ? nums1Copy[p1++] : nums2[p2++];
        }
		while (p1 < m) {
            nums1[p++] = nums1Copy[p1++];
        }
		while (p2 < n) {
            nums1[p++] = nums2[p2++];
        }
    }
    
    public static void main(final String[] args) {
	}
}