/**
 * Problem #19
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if (fast == null) {
            return head.next;
        } else {
            ListNode slow = head;
            fast = fast.next;
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;
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