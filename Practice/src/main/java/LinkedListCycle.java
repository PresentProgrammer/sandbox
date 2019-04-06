import java.util.HashSet;
import java.util.Set;

/**
 * Problem #141
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
		final Set<ListNode> nodes = new HashSet<>();
		ListNode curr = head;
		while (curr != null) {
		    if (nodes.contains(curr)) {
		        return true;
            } else {
		        nodes.add(curr);
		        curr = curr.next;
            }
        }
		return false;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}