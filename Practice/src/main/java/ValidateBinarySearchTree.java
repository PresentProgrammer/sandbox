/**
 * Problem #98
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return isValidBst(root, true, 0, true, 0);
    }

    private static boolean isValidBst(TreeNode node, boolean noLowerBound, int lowerBound, boolean noUpperBound, int upperBound) {
        if (node == null) {
            return true;
        } else if ((noLowerBound || lowerBound < node.val) && (noUpperBound || node.val < upperBound)) {
            return isValidBst(node.left, noLowerBound, lowerBound, false, node.val)
                    && isValidBst(node.right, false, node.val, noUpperBound, upperBound);
        } else {
            return false;
        }
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