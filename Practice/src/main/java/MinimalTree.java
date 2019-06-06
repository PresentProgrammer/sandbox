import static java.util.Optional.ofNullable;

/**
 * Problem #4.2
 * Time complexity: O(n)
 * Space complexity: O(log n)
 **/
public class MinimalTree {
    
    public Node construct(final int[] array) {
		return array == null || array.length == 0 ? null : construct(array, 0, array.length);
    }

    private static Node construct(final int[] array, final int left, final int rightExcl) {
        if (left == rightExcl) {
            return null;
        }
        final int mid = (left + rightExcl) / 2;
        return new Node(array[mid], construct(array, left, mid), construct(array, mid + 1, rightExcl));
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

        String inOrderWithDepth() {
            final StringBuilder strBuilder = new StringBuilder();
            inOrderWithDepth(strBuilder, this, 0);
            return strBuilder.toString();
        }

        void inOrderWithDepth(final StringBuilder strBuilder, final Node node, final int depth) {
            if (node != null) {
                inOrderWithDepth(strBuilder, node.left, depth + 1);
                strBuilder.append(node.val).append(":").append(depth).append(", ");
                inOrderWithDepth(strBuilder, node.right, depth + 1);
            }
        }
    }
    
    public static void main(final String[] args) {
        System.out.println(new MinimalTree().construct(new int[]{1, 2, 3, 4, 5, 6, 7}).inOrderWithDepth());
        System.out.println(new MinimalTree().construct(new int[]{1, 2, 3, 4, 5, 6, 7, 8}).inOrderWithDepth());
        System.out.println(new MinimalTree().construct(new int[]{1}).inOrderWithDepth());
        System.out.println(new MinimalTree().construct(new int[]{1, 2}).inOrderWithDepth());
        System.out.println(ofNullable(new MinimalTree().construct(new int[]{})).map(Node::inOrderWithDepth).orElse(null));
	}
}