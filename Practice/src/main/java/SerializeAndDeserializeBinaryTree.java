import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Problem #297
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class SerializeAndDeserializeBinaryTree {

    public String serialize(final TreeNode root) {
        final List<Integer> result = new ArrayList<>();
        final Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            final TreeNode curr = q.poll();
            if (curr != null) {
                q.offer(curr.left);
                q.offer(curr.right);
            }
            result.add(curr == null ? null : curr.val);
        }
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result.toString();
    }

    public TreeNode deserialize(final String data) {
        final Iterator<Integer> valueIter = getNodeValues(data).iterator();
        if (!valueIter.hasNext()) {
            return null;
        }
        final Queue<TreeNode> q = new ArrayDeque<>();
        final TreeNode root = new TreeNode(valueIter.next());
        q.offer(root);
        while (!q.isEmpty()) {
            final TreeNode curr = q.poll();
            if (valueIter.hasNext()) {
                final Integer val = valueIter.next();
                if (val != null) {
                    curr.left = new TreeNode(val);
                    q.offer(curr.left);
                }
            }
            if (valueIter.hasNext()) {
                final Integer val = valueIter.next();
                if (val != null) {
                    curr.right = new TreeNode(val);
                    q.offer(curr.right);
                }
            }
        }
        return root;
    }

    private List<Integer> getNodeValues(String data) {
        final StringTokenizer tokenizer = new StringTokenizer(data, "[, ]");
        final List<Integer> nodeValues = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();
            nodeValues.add("null".equals(token) ? null : Integer.parseInt(token));
        }
        return nodeValues;
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