/**
 * Problem #236
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class LowestCommonAncestorOfBinaryTree {

    private TreeNode lca;
    private TreeNode p;
    private TreeNode q;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.lca = null;
        this.p = p;
        this.q = q;
        findLowestCommonAncestor(root);
        return this.lca;
    }

    private int findLowestCommonAncestor(TreeNode curr) {
        if (curr == null) {
            return 0;
        } else {
            int foundFromCurr = 0;
            if (curr.val == p.val || curr.val == q.val) {
                foundFromCurr++;
            }
            foundFromCurr += findLowestCommonAncestor(curr.left);
            if (foundFromCurr > 1) {
                if (lca == null) {
                    lca = curr;
                }
                return foundFromCurr;
            } else {
                foundFromCurr += findLowestCommonAncestor(curr.right);
                if (foundFromCurr > 1 && lca == null) {
                    lca = curr;
                }
                return foundFromCurr;
            }
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