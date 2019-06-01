/**
 * Problem #160
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(final ListNode headA, final ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        final TailAndLength a = getTailAndLength(headA);
        final TailAndLength b = getTailAndLength(headB);
        if (a.tail == b.tail) {
            ListNode longer = a.length >= b.length ? headA : headB;
            ListNode shorter = a.length < b.length ? headA : headB;
            for (int i = 0; i < Math.abs(a.length - b.length); i++) {
                longer = longer.next;
            }
            while (longer != shorter) {
                longer = longer.next;
                shorter = shorter.next;
            }
            return longer;
        } else {
            return null;
        }
    }

    private static TailAndLength getTailAndLength(final ListNode head) {
        int length = 1;
        ListNode curr = head;
        while (curr.next != null) {
            length++;
            curr = curr.next;
        }
        return new TailAndLength(curr, length);
    }

    private static class TailAndLength {
        ListNode tail;
        int length;

        TailAndLength(ListNode tail, int length) {
            this.tail = tail;
            this.length = length;
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