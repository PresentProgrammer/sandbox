/**
 * Problem #2.1
 * Time complexity: O(n ^ 2)
 * Space complexity: O(1)
 **/
public class RemoveDuplicates {
    
    public void removeDuplicates(LinkedListNode<Integer> head) {
        LinkedListNode<Integer> left = head;
        while (left != null) {
            LinkedListNode<Integer> right = left;
            while (right.next != null) {
                if (right.next.val.equals(left.val)) {
                    right.next = right.next.next;
                } else {
                    right = right.next;
                }
            }
            left = left.next;
        }
    }
    
    public static void main(final String[] args) {
        LinkedListNode<Integer> head = new LinkedListNode<>(1);
        head = LinkedListNode.add(head, 3);
        head = LinkedListNode.add(head, 3);
        head = LinkedListNode.add(head, 2);
        head = LinkedListNode.add(head, 2);
        head = LinkedListNode.add(head, 1);
        head = LinkedListNode.add(head, 0);
        head = LinkedListNode.add(head, 5);
        head = LinkedListNode.add(head, 1);
        new RemoveDuplicates().removeDuplicates(head);
        System.out.println("[1, 3, 2, 0, 5, ] == " + LinkedListNode.print(head));

        LinkedListNode<Integer> head2 = null;
        new RemoveDuplicates().removeDuplicates(head2);
        System.out.println("[] == " + LinkedListNode.print(head2));
	}
}