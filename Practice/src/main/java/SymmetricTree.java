import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem #101
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        final Queue<TreeNode> leftQ = new LinkedList<>();
        final Queue<TreeNode> rightQ = new LinkedList<>();
        leftQ.offer(root);
        rightQ.offer(root);
        while (!leftQ.isEmpty()) {
            final TreeNode leftNode = leftQ.poll();
            final TreeNode rightNode = rightQ.poll();
            if (leftNode == null) {
                if (rightNode != null) {
                    return false;
                }
            } else {
                if (rightNode == null || leftNode.val != rightNode.val) {
                    return false;
                } else {
                    leftQ.offer(leftNode.right);
                    leftQ.offer(leftNode.left);
                    rightQ.offer(rightNode.left);
                    rightQ.offer(rightNode.right);
                }
            }
        }
        return rightQ.isEmpty();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(final String[] args) {
    }
}