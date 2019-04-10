import java.util.TreeSet;

/**
 * Problem #230
 * Time complexity: O(n log k)
 * Space complexity: O(n + k)
 **/
public class KthSmallestElementInBst {

    private TreeSet<Integer> treeSet;
    private int k;

    public int kthSmallest(final TreeNode root, final int k) {
        this.treeSet = new TreeSet<>();
        this.k = k;
		traverseFillingTreeSet(root);
		return this.treeSet.last();
    }

    private void traverseFillingTreeSet(final TreeNode node) {
        if (node != null) {
            treeSet.add(node.val);
            if (treeSet.size() > k) {
                treeSet.pollLast();
            }
            traverseFillingTreeSet(node.left);
            traverseFillingTreeSet(node.right);
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