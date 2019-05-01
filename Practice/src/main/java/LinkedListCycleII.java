/**
 * Problem #142
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class LinkedListCycleII {

    /**
     * See https://leetcode.com/problems/linked-list-cycle-ii/discuss/44774/Java-O(1)-space-solution-with-detailed-explanation.
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        final ListNode handle = new ListNode(-1);
        ListNode hare = head.next.next;
        ListNode turtle = head.next;
        while (hare != null && hare != turtle) {
            turtle = turtle.next;
            hare = hare.next == null ? null : hare.next.next;
        }
        if (hare == null) {
            return null;
        } else {
            turtle = head;
            while (hare != turtle) {
                hare = hare.next;
                turtle = turtle.next;
            }
            return turtle;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(final String[] args) {
    }
}