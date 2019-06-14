/**
 * Problem #4.10
 * Time complexity: O(n)
 * Space complexity: O(log n)
 **/
public class CheckSubtree {

    private boolean subtreeFound = false;
    private MinimalTree.Node t2;
    private int t2Size;
    
    public boolean check(final MinimalTree.Node t1, final MinimalTree.Node t2) {
        this.t2 = t2;
        this.t2Size = size(t2);
		searchSubtree(t1);
		return subtreeFound;
    }

    private Integer searchSubtree(final MinimalTree.Node node) {
        if (node == null) {
            return 0;
        } else {
            final Integer leftSize = searchSubtree(node.left);
            if (subtreeFound) {
                return null;
            }
            final Integer rightSize = searchSubtree(node.right);
            if (subtreeFound || leftSize == null || rightSize == null) {
                return null;
            }
            final int currSize = leftSize + 1 + rightSize;
            if (currSize < t2Size) {
                return currSize;
            } else if (currSize == t2Size && areIdentical(node, t2)) {
                subtreeFound = true;
                return null;
            } else {
                return null;
            }
        }
    }

    private static int size(final MinimalTree.Node node) {
        if (node == null) {
            return 0;
        } else {
            return size(node.left) + 1 + size(node.right);
        }
    }

    private static boolean areIdentical(final MinimalTree.Node t1, final MinimalTree.Node t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        } else {
            return t1.val == t2.val && areIdentical(t1.left, t2.left) && areIdentical(t1.right, t2.right);
        }
    }

    public static void main(final String[] args) {
        final MinimalTree.Node t1 = new MinimalTree().construct(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("true == " + new CheckSubtree().check(t1, new MinimalTree().construct(new int[]{5, 6, 7})));
        System.out.println("true == " + new CheckSubtree().check(t1, new MinimalTree().construct(new int[]{5})));
        System.out.println("false == " + new CheckSubtree().check(t1, new MinimalTree().construct(new int[]{6})));
        System.out.println("false == " + new CheckSubtree().check(t1, new MinimalTree().construct(new int[]{7, 6, 5})));
        System.out.println("false == " + new CheckSubtree().check(t1, null));

        final MinimalTree.Node t2 = new MinimalTree.Node(7,
                new MinimalTree.Node(5,
                        null,
                        new MinimalTree.Node(6, null, null)),
                null);
        System.out.println("false == " + new CheckSubtree().check(t1, t2));
	}
}