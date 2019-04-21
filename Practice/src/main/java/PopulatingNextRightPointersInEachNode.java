/**
 * Problem #116
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class PopulatingNextRightPointersInEachNode {
    
    public Node connect(final Node root) {
        if (root != null) {
            connect(root, null);
        }
        return root;
    }

    private static void connect(final Node curr, final Node next) {
        curr.next = next;
        if (curr.left != null) {
            connect(curr.right, curr.next == null ? null : curr.next.left);
            connect(curr.left, curr.right);
        }
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node next;

        Node() {}

        Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}