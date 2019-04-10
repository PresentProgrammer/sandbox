import java.util.HashMap;
import java.util.Map;

/**
 * Problem #230
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class KthSmallestElementInBst {

    private Map<TreeNode, Integer> nodeCounts;
    private int k;

    public int kthSmallest(final TreeNode root, final int k) {
        this.nodeCounts = new HashMap<>();
        this.k = k;
        return kthSmallest(root);
    }

    private int kthSmallest(final TreeNode node) {
        final int leftNodeCount = node.left == null ? 0 : nodeCounts.computeIfAbsent(node.left, this::countNodes);
        if (k <= leftNodeCount) {
            return kthSmallest(node.left);
        } else if (k > leftNodeCount + 1) {
            k -= leftNodeCount + 1;
            return kthSmallest(node.right);
        } else {
            return node.val;
        }
    }

    private int countNodes(final TreeNode node) {
        return (node.left == null ? 0 : nodeCounts.computeIfAbsent(node.left, this::countNodes))
                + (node.right == null ? 0 : countNodes(node.right))
                + 1;
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