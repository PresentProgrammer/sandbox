/**
 * Problem #160
 * Time complexity: O(n)
 * Space complexity: O(n), but we modify only input structure.
 **/
public class IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(final ListNode headA, final ListNode headB) {
        final int lengthFromAToTail = pathLength(headA);
        final int lengthFromBToTail = pathLength(headB);
        final ListNode tail = reverse(headA);
        final ListNode intersectionNode;
        if (isPathBetween(headB, headA)) {
            final int lengthFromBToA = pathLength(headB);
            final int intersectionNodeNumber = (lengthFromAToTail + lengthFromBToTail - lengthFromBToA + 1) / 2;
            ListNode curr = tail;
            for (int i = 1; i < intersectionNodeNumber; i++) {
                curr = curr.next;
            }
            intersectionNode = curr;
        } else {
            intersectionNode = null;
        }
        reverse(tail);
        return intersectionNode;
    }

    private static int pathLength(final ListNode head) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    private static ListNode reverse(final ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            final ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private static boolean isPathBetween(final ListNode a, final ListNode b) {
        ListNode curr = a;
        while (curr != null && curr != b) {
            curr = curr.next;
        }
        return curr == b;
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