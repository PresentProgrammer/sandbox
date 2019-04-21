import java.util.ArrayList;
import java.util.List;

/**
 * Problem #19
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        final List<ListNode> nodes = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            nodes.add(curr);
            curr = curr.next;
        }

        if (n == nodes.size()) {
            return head.next;
        } else {
            final ListNode prev = nodes.get(nodes.size() - n - 1);
            prev.next = n > 1 ? nodes.get(nodes.size() - n + 1) : null;
            return head;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(final String[] args) {
    }
}