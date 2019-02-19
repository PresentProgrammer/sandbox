/**
 * Problem #21
 * Time complexity: O()
 * Space complexity: O()
 **/
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(final ListNode list1, final ListNode list2) {
        final ListNode res;
        ListNode l1, l2;
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val <= list2.val) {
            res = list1;
            l1 = list1.next;
            l2 = list2;
        } else {
            res = list2;
            l1 = list1;
            l2 = list2.next;
        }
        ListNode curr = res;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;
                curr = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                curr = l2;
                l2 = l2.next;
            }
        }
        while (l1 != null) {
            curr.next = l1;
            curr = l1;
            l1 = l1.next;
        }
        while (l2 != null) {
            curr.next = l2;
            curr = l2;
            l2 = l2.next;
        }
        return res;
    }
    
    public static void main(final String[] args) {
        final ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        final ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        System.out.println("443211 == " + new MergeTwoSortedLists().mergeTwoLists(l1, l2));
	}
}