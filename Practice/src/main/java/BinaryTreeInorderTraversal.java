import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * Problem #94
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        final List<Integer> inorder = new ArrayList<>();
        final Deque<NodeWithState> stack = new ArrayDeque<>();
        stack.push(new NodeWithState(root));
        while (!stack.isEmpty()) {
            final NodeWithState curr = stack.peek();
            if (!curr.leftVisited && curr.node.left != null) {
                curr.leftVisited = true;
                stack.push(new NodeWithState(curr.node.left));
            } else if (!curr.rightVisited) {
                inorder.add(curr.node.val);
                curr.rightVisited = true;
                if (curr.node.right != null) {
                    stack.push(new NodeWithState(curr.node.right));
                }
            } else {
                stack.pop();
            }
        }
        return inorder;
    }

    private static class NodeWithState {
        TreeNode node;
        boolean leftVisited;
        boolean rightVisited;

        NodeWithState(TreeNode node) {
            this.node = node;
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