/**
 * Problem #148
 * Time complexity: O(n log n)
 * Space complexity: O(n) for recursion.
 **/
public class SortList {

    private ListNode mergeHead = new ListNode(0);

    public ListNode sortList(ListNode head) {
        return head == null ? null : mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null) {
            return head;
        } else {
            ListNode slow = head, fast = head;
            while (fast != null) {
                fast = fast.next;
                if (fast != null) {
                    fast = fast.next;
                    if (fast != null) {
                        slow = slow.next;
                    }
                }
            }
            ListNode secondList = slow.next;
            slow.next = null;
            return merge(mergeSort(head), mergeSort(secondList));
        }
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode mergeTail = mergeHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                mergeTail.next = l1;
                l1 = l1.next;
            } else {
                mergeTail.next = l2;
                l2 = l2.next;
            }
            mergeTail = mergeTail.next;
        }
        mergeTail.next = l1 == null ? l2 : l1;
        return mergeHead.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4), p = head;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(1);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p = new SortList().mergeSort(head);
        while (p != null) {
            System.out.print(p.val + " -> ");
            p = p.next;
        }
    }
}