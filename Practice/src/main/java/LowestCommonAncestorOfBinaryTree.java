/**
 * Problem #236
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class LowestCommonAncestorOfBinaryTree {

    private TreeNode result;
    private TreeNode first;
    private TreeNode second;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.first = p;
        this.second = q;
        lowestCommonAncestor(root, false);
        return result;
    }

    private boolean lowestCommonAncestor(final TreeNode node, final boolean oneFound) {
        if (node == null) {
            return false;
        } else if (node == first || node == second) {
            if (oneFound) {
                return true;
            } else if (lowestCommonAncestor(node.left, true) || lowestCommonAncestor(node.right, true)) {
                result = node;
                return true;
            } else {
                return true;
            }
        } else if (oneFound) {
            return lowestCommonAncestor(node.left, true) || lowestCommonAncestor(node.right, true);
        } else {
            final boolean foundInLeft = lowestCommonAncestor(node.left, false);
            if (result == null) {
                final boolean foundInRight = lowestCommonAncestor(node.right, foundInLeft);
                if (foundInLeft && foundInRight && result == null) {
                    result = node;
                }
                return foundInLeft || foundInRight;
            }
            return foundInLeft;
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