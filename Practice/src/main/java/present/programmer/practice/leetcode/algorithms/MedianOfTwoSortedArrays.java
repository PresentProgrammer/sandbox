import java.util.TreeSet;

public class MedianOfTwoSortedArrays {
    
    /**
     * Runtime: O(log(min(m, n)))
     * Space: O(1)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int[] a, b;
        if (nums1.length <= nums2.length) {
            a = nums1;
            b = nums2;
        } else {
            a = nums2;
            b = nums1;
        }
        return solveByDividingIntoLowerAndHigherSets(a, 0, a.length, b, 0, b.length);
    }
    
    private static double solveByDividingIntoLowerAndHigherSets(final int[] a, final int aLowerIncl, final int aHigherExcl,
            final int[] b, final int bLowerIncl, final int bHigherExcl) {
        if (aLowerIncl == aHigherExcl) {
            if (bLowerIncl == bHigherExcl) {
                final TreeSet<Integer> leftMed = new TreeSet<>();
                if (aLowerIncl > 0)
                    leftMed.add(a[aLowerIncl - 1]);
                if (bLowerIncl > 0)
                    leftMed.add(b[bLowerIncl - 1]);
                
                final TreeSet<Integer> rightMed = new TreeSet<>();
                if (aHigherExcl < a.length)
                    rightMed.add(a[aHigherExcl]);
                if (bHigherExcl < b.length)
                    rightMed.add(b[bHigherExcl]);
                
                return (leftMed.last() + rightMed.first()) / 2.0;                
            } else if (bHigherExcl - bLowerIncl == 1) {
                final int bMedian = b[bLowerIncl];
                if (aLowerIncl > 0 && a[aLowerIncl - 1] > bMedian) {
                    return a[aLowerIncl - 1];
                } else if (aHigherExcl < a.length && a[aHigherExcl] < bMedian) {
                    return a[aHigherExcl];
                } else {
                    return bMedian;
                }
            } else {
                final int outsideBorders = (bHigherExcl - bLowerIncl) / 2;
                return solveByDividingIntoLowerAndHigherSets(a, aLowerIncl, aHigherExcl,
                        b, bLowerIncl + outsideBorders, bHigherExcl - outsideBorders);
            }
        } else {
            final double aMedian = median(a, aLowerIncl, aHigherExcl);
            final double bMedian = median(b, bLowerIncl, bHigherExcl);
            if (aMedian == bMedian) {
                return aMedian;
            } else {
                final int length = aHigherExcl - aLowerIncl;
                final int outsideBorders = length % 2 == 0 ? length / 2 : length / 2 + 1;
                if (aMedian < bMedian) {
                    return solveByDividingIntoLowerAndHigherSets(a, aLowerIncl + outsideBorders, aHigherExcl,
                            b, bLowerIncl, bHigherExcl - outsideBorders);
                } else {
                    return solveByDividingIntoLowerAndHigherSets(a, aLowerIncl, aHigherExcl - outsideBorders,
                            b, bLowerIncl + outsideBorders, bHigherExcl);
                }
            }
        }
    }
    
    private static double median(final int[] a) {
        return median(a, 0, a.length);
    }
    
    private static double median(final int[] a, final int leftIncl, final int rightExcl) {
        if (rightExcl - leftIncl == 0)
            return 0.0;
        else {
            final int mid = (leftIncl + rightExcl) / 2;
            return (leftIncl + rightExcl) % 2 == 0 ? ((double) (a[mid - 1] + a[mid])) / 2 : a[mid];
        }
    }
    
    /**
     * Runtime: O(max(m, n))
     * Space: O(1)
     */
    public double findMedianSortedArrays_usingMergeSort(int[] nums1, int[] nums2) {
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
    
    public static void main(final String... args) {
        System.out.println("Median of [1, 3] and [2] == " +
                new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 }));
        System.out.println("Median of [1, 2] and [3, 4] == " +
                new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] { 1, 2 }, new int[] { 3, 4 }));
        System.out.println("Median of [2] and [1, 3, 4] == " +
                new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] { 2 }, new int[] { 1, 3, 4 }));
    }
}