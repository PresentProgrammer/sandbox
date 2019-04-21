import java.util.HashMap;
import java.util.Map;

/**
 * Problem #105
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        final Map<Integer, TreeNode> nodes = new HashMap<>();
        final TreeNode root = new TreeNode(preorder[0]);
        nodes.put(root.val, root);
        TreeNode curr = root;
        int i = 1, j = 0;
        boolean inorderReached = false;
        while (i < preorder.length) {
            final TreeNode inorderNode = nodes.get(inorder[j]);
            if (inorderNode == null) {
                final TreeNode newNode = new TreeNode(preorder[i]);
                if (inorderReached) {
                    curr.right = newNode;
                    curr = curr.right;
                } else {
                    curr.left = newNode;
                    curr = curr.left;
                }
                inorderReached = false;
                i++;
            } else {
                curr = inorderNode;
                inorderReached = true;
                j++;
            }
            nodes.put(curr.val, curr);
        }
        return root;
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