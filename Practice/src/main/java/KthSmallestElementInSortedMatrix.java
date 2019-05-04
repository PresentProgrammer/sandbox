/**
 * Problem #378
 * Time complexity: O(n * log(max - min))
 * Space complexity: O(1)
 **/
public class KthSmallestElementInSortedMatrix {

    public int kthSmallest(final int[][] matrix, final int k) {
        int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix.length - 1];
        while (lo <= hi) {
            final int mid = lo + (hi - lo) / 2;
            int i = matrix.length - 1;
            int j = 0;
            int count = 0;
            while (i >= 0 && j < matrix.length) {
                if (matrix[i][j] > mid) {
                    i--;
                } else {
                    count += i + 1;
                    j++;
                }
            }
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }
    
    public static void main(final String[] args) {
	}
}