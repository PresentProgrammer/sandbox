import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;

/**
 * Problem #4.9
 * Time complexity: O((V / 2 + 1)!)
 * Space complexity: O((V / 2)! * V)
 **/
public class BstSequence {

    public List<List<Integer>> allPossibleArrays(final Node root) {
        return root == null ? emptyList() : allPossibleArrays(singleton(root), emptyList());
    }

    private static List<List<Integer>> allPossibleArrays(final Set<Node> openNodes, final List<Integer> arraySoFar) {
        if (openNodes.isEmpty()) {
            return singletonList(arraySoFar);
        } else {
            final List<List<Integer>> result = new ArrayList<>();
            for (final Node node : openNodes) {
                final Set<Node> next = new HashSet<>(openNodes);
                next.remove(node);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }

                final List<Integer> nextArraySoFar = new ArrayList<>(arraySoFar);
                nextArraySoFar.add(node.val);

                result.addAll(allPossibleArrays(next, nextArraySoFar));
            }
            return result;
        }
    }

    private static class Node {

        int val;
        Node left;
        Node right;

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(final String[] args) {
        System.out.println(new BstSequence().allPossibleArrays(
                new Node(4,
                        new Node(1,
                                new Node(0, null, null),
                                new Node(3, null, null)),
                        new Node(6,
                                new Node(7, null, null),
                                new Node(8, null, null)))
        ));
    }
}