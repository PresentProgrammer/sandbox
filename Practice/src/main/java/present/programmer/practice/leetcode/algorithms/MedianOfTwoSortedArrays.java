

public class MedianOfTwoSortedArrays {
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return median(merge(nums1, nums2));
    }
    
    private static int[] merge(final int[] a, final int[] b) {
        final int[] m = new int[a.length + b.length];
        int ai = 0;
        int bi = 0;
        int mi = 0;
        while (ai < a.length && bi < b.length)
            if (a[ai] <= b[bi])
                m[mi++] = a[ai++];
            else
                m[mi++] = b[bi++];
        
        final int[] r;
        int ri;
        if (ai < a.length) {
            r = a;
            ri = ai;
        } else {
            r = b;
            ri = bi;
        }
        while (ri < r.length)
            m[mi++] = r[ri++];
        
        return m;
    }
    
    private static double median(final int[] a) {
        final int n = a.length;
        if (n == 0)
            return 0.0;
        else
            return n % 2 == 0 ? ((double) (a[n / 2 - 1] + a[n / 2])) / 2 : a[n / 2];
    }
    
    public static void main(final String... args) {
        System.out.println("Median of [1, 3] and [2] == " +
                new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        System.out.println("Median of [1, 2] and [3, 4] == " +
                new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
    }
}