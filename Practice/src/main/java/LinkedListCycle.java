/**
 * Problem #141
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                if (fast == slow) {
                    return true;
                } else {
                    fast = fast.next;
                    slow = slow.next;
                }
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