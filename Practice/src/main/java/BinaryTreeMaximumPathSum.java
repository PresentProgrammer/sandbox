/**
 * Problem #124
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class BinaryTreeMaximumPathSum {

    private int max;

    public int maxPathSum(final TreeNode root) {
        max = root.val;
        maxOf(root);
        return max;
    }

    private int maxOf(final TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            final int leftMax = maxOf(node.left);
            final int rightMax = maxOf(node.right);

            final int subTreeMax = (leftMax > 0 ? leftMax : 0) + (rightMax > 0 ? rightMax : 0) + node.val;
            max = Math.max(max, subTreeMax);

            final int branchMax = Math.max(leftMax, rightMax);
            return (branchMax > 0 ? branchMax : 0) + node.val;
        }
    }

    private static class TreeNode {
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