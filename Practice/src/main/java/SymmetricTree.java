/**
 * Problem #101
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private static boolean isSymmetric(TreeNode left, TreeNode right) {
        return left == null && right == null || left != null && right != null && left.val == right.val
                && isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
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