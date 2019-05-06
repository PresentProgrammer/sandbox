/**
 * Problem #234
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class PalindromeLinkedList {

    public boolean isPalindrome(final ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode tortoise = head;
        ListNode hair = head.next == null ? head : head.next;
        while (hair.next != null) {
            hair = hair.next;
            hair = hair.next == null ? hair : hair.next;
            tortoise = tortoise.next;
        }

        ListNode prev = tortoise;
        ListNode curr = prev.next;
        ListNode next = curr == null ? null : curr.next;
        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next == null ? null : next.next;
        }

        ListNode left = head;
        ListNode right = hair;
        while (left != right && left.next != right) {
            if (left.val == right.val) {
                left = left.next;
                right = right.next;
            } else {
                return false;
            }
        }
        return left.val == right.val;
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