/**
 * Problem #328
 * Time complexity: O(n)
 * Space complexity: O(1) or O(n) depending on whether we count input change as additional space.
 **/
public class OddEvenLinkedList {

    public ListNode oddEvenList(final ListNode head) {
        ListNode prev = head;
        ListNode curr = head == null ? null : head.next;
        final ListNode firstEven = curr;
        ListNode lastOdd = head;
        boolean currIsOdd = false;
        while (curr != null) {
            lastOdd = currIsOdd ? curr : lastOdd;
            currIsOdd ^= true;
            prev.next = curr.next;
            prev = curr;
            curr = curr.next;
        }
        if (lastOdd != null) {
            lastOdd.next = firstEven;
        }
        return head;
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