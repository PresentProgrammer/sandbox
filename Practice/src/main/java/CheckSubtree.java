/**
 * Problem #4.10, #572
 * Time complexity: O(n)
 * Space complexity: O(log n), and O(n) the worst; or O(d), where d is depth.
 **/
public class CheckSubtree {

    private boolean subtreeFound = false;
    private TreeNode t2;
    private int t2Size;

    public boolean isSubtree(final TreeNode t1, final TreeNode t2) {
        this.t2 = t2;
        this.t2Size = size(t2);
        searchSubtree(t1);
        return subtreeFound;
    }

    private Integer searchSubtree(final TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            final Integer leftSize = searchSubtree(node.left);
            if (subtreeFound) {
                return null;
            }
            final Integer rightSize = searchSubtree(node.right);
            if (subtreeFound || leftSize == null || rightSize == null) {
                return null;
            }
            final int currSize = leftSize + 1 + rightSize;
            if (currSize < t2Size) {
                return currSize;
            } else if (currSize == t2Size && areIdentical(node, t2)) {
                subtreeFound = true;
                return null;
            } else {
                return null;
            }
        }
    }

    private static int size(final TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return size(node.left) + 1 + size(node.right);
        }
    }

    private static boolean areIdentical(final TreeNode t1, final TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        } else {
            return t1.val == t2.val && areIdentical(t1.left, t2.left) && areIdentical(t1.right, t2.right);
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
}