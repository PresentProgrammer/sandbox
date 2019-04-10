/**
 * Problem #206
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class ReverseLinkedList {

    public ListNode reverseList(final ListNode head) {
        if (head == null) {
            return null;
        } else {
            ListNode prev = head, curr = prev.next, next;
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            head.next = null;
            return prev;
        }
    }

    public ListNode m(final ListNode head) {
		if (head == null) {
		    return null;
        } else if (head.next == null) {
		    return head;
        } else {
		    final ListNode reversedList = reverseList(head.next);
            head.next.next = head;
		    head.next = null;
		    return reversedList;
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