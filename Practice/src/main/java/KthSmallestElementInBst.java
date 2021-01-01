/**
 * Problem #230
 * Time complexity: O(n)
 * Space complexity: O(log n)
 **/
public class KthSmallestElementInBst {

    private int result;
    private int count;

    public int kthSmallest(final TreeNode root, final int k) {
        count = k;
        search(root);
        return result;
    }

    private void search(final TreeNode node) {
        if (count > 0 && node != null) {
            search(node.left);
            count--;
            if (count == 0) {
                result = node.val;
            } else if (count > 0) {
                search(node.right);
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