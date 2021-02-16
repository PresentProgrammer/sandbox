import java.util.ArrayList;
import java.util.List;

/**
 * Problem #1110
 * Time complexity: O(N)
 * Space complexity: O(N)
 **/
public class DeleteNodesAndReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] toDeleteInts) {
        return dfs(root, true, buildToDelete(toDeleteInts), new ArrayList<>());
    }

    private static List<TreeNode> dfs(TreeNode curr, boolean rootCandidate, boolean[] toDelete, List<TreeNode> result) {
        if (curr != null) {
            final TreeNode left = curr.left;
            if (left != null && toDelete[left.val]) {
                curr.left = null;
            }
            final TreeNode right = curr.right;
            if (right != null && toDelete[right.val]) {
                curr.right = null;
            }

            if (toDelete[curr.val]) {
                dfs(left, true, toDelete, result);
                dfs(right, true, toDelete, result);
            } else {
                if (rootCandidate) {
                    result.add(curr);
                }
                dfs(left, false, toDelete, result);
                dfs(right, false, toDelete, result);
            }
        }
        return result;
    }

    private static boolean[] buildToDelete(int[] toDeleteInts) {
        final boolean[] toDelete = new boolean[1001];
        for (int d : toDeleteInts) {
            toDelete[d] = true;
        }
        return toDelete;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}