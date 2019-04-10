/**
 * Problem #206
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ReverseLinkedList {

    public ListNode reverseList(final ListNode head) {
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