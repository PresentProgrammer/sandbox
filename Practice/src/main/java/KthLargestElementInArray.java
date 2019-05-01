import java.util.PriorityQueue;

/**
 * Problem #215
 * Time complexity: O(n log k)
 * Space complexity: O(k)
 **/
public class KthLargestElementInArray {

    public int findKthLargest(int[] nums, int k) {
		final PriorityQueue<Integer> Q = new PriorityQueue<>();
		for (final int num : nums) {
		    Q.offer(num);
		    if (Q.size() > k) {
		        Q.poll();
            }
        }
		return Q.peek();
    }
    
    public static void main(final String[] args) {
        System.out.println("5 == " + new KthLargestElementInArray().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println("4 == " + new KthLargestElementInArray().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
	}
}