import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #236
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class LowestCommonAncestorOfBinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        boolean oneFound = false;
        final Deque<NodeAndState> stack = new ArrayDeque<>();
        stack.push(new NodeAndState(root));
        while (!stack.isEmpty()) {
            final NodeAndState curr = stack.peek();
            if (!curr.leftVisited) {
                if (curr.node.val == p.val || curr.node.val == q.val) {
                    if (oneFound) {
                        NodeAndState candidate = stack.pop();
                        while (!candidate.possibleAcs) {
                            candidate = stack.pop();
                        }
                        return candidate.node;
                    } else {
                        oneFound = true;
                        curr.possibleAcs = true;
                    }
                }
                if (curr.node.left != null) {
                    stack.push(new NodeAndState(curr.node.left));
                }
                curr.leftVisited = true;
            } else if (!curr.rightVisited) {
                if (curr.node.right != null) {
                    stack.push(new NodeAndState(curr.node.right));
                }
                curr.rightVisited = true;
            } else {
                final boolean possibleAcs = stack.pop().possibleAcs;
                if (possibleAcs && !stack.isEmpty()) {
                    stack.peek().possibleAcs = true;
                }
            }
        }
        return null;
    }

    private static class NodeAndState {
        TreeNode node;
        boolean leftVisited = false;
        boolean rightVisited = false;
        boolean possibleAcs = false;

        NodeAndState(TreeNode node) {
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