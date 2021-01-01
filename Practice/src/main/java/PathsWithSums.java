import java.util.ArrayList;
import java.util.List;

/**
 * Problem #4.12, #437
 * Time complexity: O(n log n) on average, and O(n ^ 2) in the worst case, when tree is one big path.
 * Space complexity: O(log n), and O(n) worst.
 **/
public class PathsWithSums {

    private int pathCount = 0;
    private final List<TreeNode> onPath = new ArrayList<>();
    private int interestingSum;

    public int pathSum(final TreeNode root, final int interestingSum) {
        this.interestingSum = interestingSum;
        searchPaths(root);
        return pathCount;
    }

    private void searchPaths(final TreeNode node) {
        if (node != null) {
            tryAllPaths(node);
            onPath.add(node);
            searchPaths(node.left);
            searchPaths(node.right);
            onPath.remove(onPath.size() - 1);
        }
    }

    private void tryAllPaths(final TreeNode node) {
        int sum = node.val;
        if (sum == interestingSum) {
            pathCount++;
        }
        for (int i = onPath.size() - 1; i >= 0; i--) {
            sum += onPath.get(i).val;
            if (sum == interestingSum) {
                pathCount++;
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
}