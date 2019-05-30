/**
 * Problem #2.2
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ReturnKthToLast {
    
    public LinkedListNode<Integer> kthToLast(final LinkedListNode<Integer> head, final int k) {
		if (head == null) {
		    return null;
        }
		LinkedListNode<Integer> fast = head;
		int ahead = 0;
		while (fast != null && ahead < k) {
		    fast = fast.next;
		    ahead++;
        }
		if (fast == null) {
		    return null;
        } else {
            LinkedListNode<Integer> slow = head;
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }
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