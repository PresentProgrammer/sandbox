import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * Problem #4.3
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ListOfDepths {
    
    public List<List<MinimalTree.Node>> generateLists(final MinimalTree.Node root) {
        if (root == null) {
            return emptyList();
        }
        final List<List<MinimalTree.Node>> result = new ArrayList<>();
        List<MinimalTree.Node> currLevel = singletonList(root);
        while (!currLevel.isEmpty()) {
            result.add(currLevel);
            final List<MinimalTree.Node> nextLevel = new ArrayList<>();
            for (final MinimalTree.Node node : currLevel) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            currLevel = nextLevel;
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println(new ListOfDepths().generateLists(new MinimalTree().construct(new int[]{1, 2, 3, 4, 5, 6, 7})));
        System.out.println(new ListOfDepths().generateLists(new MinimalTree().construct(new int[]{1, 2, 3, 4, 5, 6, 7, 8})));
        System.out.println(new ListOfDepths().generateLists(new MinimalTree().construct(new int[]{1})));
        System.out.println(new ListOfDepths().generateLists(new MinimalTree().construct(new int[]{})));
	}
}