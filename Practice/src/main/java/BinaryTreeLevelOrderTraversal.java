import java.util.ArrayList;
import java.util.List;

/**
 * Problem #102
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        final List<List<Integer>> result = new ArrayList<>();
        List<TreeNode> next = new ArrayList<>();
        if (root != null) {
            next.add(root);
        }
        while (!next.isEmpty()) {
            final List<TreeNode> curr = next;
            next = new ArrayList<>();
            final List<Integer> currValues = new ArrayList<>();
            for (final TreeNode node : curr) {
                currValues.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            result.add(currValues);
        }
        return result;
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