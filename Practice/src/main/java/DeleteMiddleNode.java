/**
 * Problem #2.3
 * Time complexity: O(1)
 * Space complexity: O(1)
 **/
public class DeleteMiddleNode {
    
    public void removeThis(final LinkedListNode<Integer> node) {
        if (node != null && node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
    
    public static void main(final String[] args) {
        final LinkedListNode<Integer> head = new LinkedListNode<>(10);
        head.next = new LinkedListNode<>(20);
        head.next.next = new LinkedListNode<>(30);
        new DeleteMiddleNode().removeThis(head.next);
        System.out.println("[10, 30, ] == " + LinkedListNode.print(head));
        new DeleteMiddleNode().removeThis(head.next.next);
        System.out.println("[10, 30, ] == " + LinkedListNode.print(head));
	}
}