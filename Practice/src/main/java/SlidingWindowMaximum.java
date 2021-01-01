import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Problem #239
 * Time complexity: O(n)
 * Space complexity: O(k)
 **/
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        final DecreasingMonotonicQueue queue = new DecreasingMonotonicQueue();
        for (int i = 0; i < k - 1; i++) {
            queue.offer(nums[i]);
        }
        final int[] result = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            queue.offer(nums[i]);
            result[i - k + 1] = queue.poll();
        }
        return result;
    }

    private static class DecreasingMonotonicQueue {

        private final Deque<int[]> deque = new ArrayDeque<>();

        /**
         * Time complexity: O(1) amortized.
         */
        void offer(int val) {
            int countPopped = 0;
            while (!deque.isEmpty() && deque.peekLast()[0] <= val) {
                countPopped += deque.pollLast()[1] + 1;
            }
            deque.offerLast(new int[]{val, countPopped});
        }

        int poll() {
            final int[] max = deque.peekFirst();
            max[1]--;
            if (max[1] < 0) {
                deque.pollFirst();
            }
            return max[0];
        }
    }

    public static void main(final String[] args) {
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(new int[]{1, -1}, 1)));
    }
}