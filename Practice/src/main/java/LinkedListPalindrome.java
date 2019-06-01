import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #2.6
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class LinkedListPalindrome {
    
    public boolean isPalindrome(final LinkedListNode<Integer> head) {
		if (head == null) {
		    return false;
        }

		int size = 0;
		LinkedListNode<Integer> curr = head;
		while (curr != null) {
		    size++;
		    curr = curr.next;
        }

		final Deque<Integer> stack = new ArrayDeque<>();
		curr = head;
		for (int i = 0; i < size / 2; i++) {
		    stack.push(curr.val);
		    curr = curr.next;
        }
		if (size % 2 == 1) {
		    curr = curr.next;
        }
		while (curr != null) {
		    if (!curr.val.equals(stack.pop())) {
		        return false;
            }
		    curr = curr.next;
        }
		return true;
    }
    
    public static void main(final String[] args) {
        final LinkedListNode<Integer> l1 = new LinkedListNode<>(1);
        LinkedListNode.add(l1, 2);
        LinkedListNode.add(l1, 3);
        LinkedListNode.add(l1, 2);
        LinkedListNode.add(l1, 1);
        System.out.println("true == " + new LinkedListPalindrome().isPalindrome(l1));

        final LinkedListNode<Integer> l2 = new LinkedListNode<>(1);
        LinkedListNode.add(l2, 2);
        LinkedListNode.add(l2, 3);
        LinkedListNode.add(l2, 3);
        LinkedListNode.add(l2, 2);
        LinkedListNode.add(l2, 1);
        System.out.println("true == " + new LinkedListPalindrome().isPalindrome(l2));

        final LinkedListNode<Integer> l3 = null;
        System.out.println("false == " + new LinkedListPalindrome().isPalindrome(l3));

        final LinkedListNode<Integer> l4 = new LinkedListNode<>(1);
        LinkedListNode.add(l4, 2);
        System.out.println("false == " + new LinkedListPalindrome().isPalindrome(l4));

        final LinkedListNode<Integer> l5 = new LinkedListNode<>(1);
        LinkedListNode.add(l5, 1);
        System.out.println("true == " + new LinkedListPalindrome().isPalindrome(l5));
    }
}