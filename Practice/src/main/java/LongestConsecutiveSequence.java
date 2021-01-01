import java.util.HashMap;
import java.util.Map;

/**
 * Problem #128
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null) {
            return 0;
        }
        final Map<Integer, Node> map = new HashMap<>();
        int max = 0;
        for (final int num : nums) {
            if (!map.containsKey(num)) {
                final Node left = map.get(num - 1);
                final Node right = map.get(num + 1);
                final int sum = 1 + (left == null ? 0 : left.val) + (right == null ? 0 : right.val);
                final Node curr = new Node(sum);
                if (left != null) {
                    left.leftBorder.val = sum;
                    left.leftBorder.rightBorder = right == null ? curr : right.rightBorder;
                    curr.leftBorder = left.leftBorder;
                }
                if (right != null) {
                    right.rightBorder.val = sum;
                    right.rightBorder.leftBorder = left == null ? curr : left.leftBorder;
                    curr.rightBorder = right.rightBorder;
                }
                map.put(num, curr);
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    private static class Node {

        int val;
        Node leftBorder;
        Node rightBorder;

        public Node(int val) {
            this.val = val;
            this.leftBorder = this;
            this.rightBorder = this;
        }
    }

    public static void main(final String[] args) {
    }
}