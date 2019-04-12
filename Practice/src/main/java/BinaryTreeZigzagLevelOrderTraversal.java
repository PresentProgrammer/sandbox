import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Problem #103
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		if (root == null) {
		    return Collections.emptyList();
        } else {
		    final List<List<TreeNode>> result = new ArrayList<>();
		    result.add(Collections.singletonList(root));
		    while (true) {
		        final List<TreeNode> nextLevel = new ArrayList<>();
		        for (final TreeNode node: result.get(result.size() - 1)) {
		            if (node.left != null) {
		                nextLevel.add(node.left);
                    }
		            if (node.right != null) {
		                nextLevel.add(node.right);
                    }
                }
                if (nextLevel.isEmpty()) {
                    for (int i = 0; i < result.size(); i++) {
                        if (i % 2 == 1) {
                            Collections.reverse(result.get(i));
                        }
                    }
                    return result.stream()
                            .map(nodeList -> nodeList.stream()
                                    .map(treeNode -> treeNode.val)
                                    .collect(Collectors.toList()))
                            .collect(Collectors.toList());
                } else {
                    result.add(nextLevel);
                }
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