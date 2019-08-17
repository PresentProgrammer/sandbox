/**
 * Problem #222
 * Time: O((log N)^2)
 * Space: O(1)
 */
public class CountNodesInCompleteBinaryTree {

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        final int completeLevel = findCompleteLevel(root);
        int count = 0;
        TreeNode currRoot = root;
        int currLevel = 1;
        while (currLevel < completeLevel) {
            if (rightBranchHasLastLevelNodes(currRoot, currLevel, completeLevel)) {
                count += Math.pow(2, completeLevel - currLevel + 1);
                currRoot = currRoot.right;
            } else {
                count += Math.pow(2, completeLevel - currLevel);
                currRoot = currRoot.left;
            }
            currLevel++;
        }
        return count + 1
                + (currRoot.right == null ? 0 : 1)
                + (currRoot.left == null ? 0 : 1);
    }

    private static int findCompleteLevel(final TreeNode root) {
        TreeNode curr = root;
        int level = 1;
        while (curr.right != null) {
            curr = curr.right;
            level++;
        }
        return level;
    }

    private static boolean rightBranchHasLastLevelNodes(
            final TreeNode currRoot, final int currLevel, final int completeLevel) {
        TreeNode node = currRoot.right;
        int nodeLevel = currLevel + 1;
        while (nodeLevel < completeLevel) {
            node = node.left;
            nodeLevel++;
        }
        return node.left != null;
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
