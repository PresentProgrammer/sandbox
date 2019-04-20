/**
 * Problem #104
 * Time complexity: O(n), where n is number of vertices.
 * Space complexity: O(d) for stack, where d is max depth.
 **/
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
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