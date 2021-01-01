/**
 * Problem #2.2
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ReturnKthToLast {

    private LinkedListNode<Integer> result;
    private int k;

    public LinkedListNode<Integer> kthToLast(final LinkedListNode<Integer> head, final int k) {
        if (head != null) {
            this.k = k;
            backTrack(head);
        }
        return result;
    }

    private int backTrack(final LinkedListNode<Integer> node) {
        final int toLast = node.next == null ? 0 : backTrack(node.next) + 1;
        if (toLast == k) {
            result = node;
        }
        return toLast;
    }

    public static void main(final String[] args) {
        LinkedListNode<Integer> head = new LinkedListNode<>(1);
        head = LinkedListNode.add(head, 2);
        head = LinkedListNode.add(head, 3);
        head = LinkedListNode.add(head, 4);
        head = LinkedListNode.add(head, 5);
        head = LinkedListNode.add(head, 6);
        head = LinkedListNode.add(head, 7);
        head = LinkedListNode.add(head, 8);
        head = LinkedListNode.add(head, 9);
        head = LinkedListNode.add(head, 10);
        System.out.println("7 == " + new ReturnKthToLast().kthToLast(head, 3).val);
        System.out.println("1 == " + new ReturnKthToLast().kthToLast(head, 9).val);
        System.out.println("null == " + new ReturnKthToLast().kthToLast(head, 10));
    }
}