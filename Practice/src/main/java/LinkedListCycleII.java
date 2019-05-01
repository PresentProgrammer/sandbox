/**
 * Problem #142
 * Time complexity: O(n ^ 2)
 * Space complexity: O(1)
 **/
public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode hare = head.next;
        ListNode turtle = head;
        while (hare != null && hare != turtle) {
            turtle = turtle.next;
            hare = hare.next == null ? null : hare.next.next;
        }
        if (hare == null) {
            return null;
        } else {
            ListNode snail = head;
            while (hare != snail) {
                do {
                    hare = hare.next;
                } while (hare != snail && hare != turtle);
                if (hare != snail) {
                    snail = snail.next;
                }
            }
            return snail;
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