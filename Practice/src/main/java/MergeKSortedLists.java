import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem #23
 * Time complexity: O(n * log k + k), where n — number of elements in all lists; k — number of lists.
 * Space complexity: O(k)
 **/
public class MergeKSortedLists {

    public ListNode mergeKLists(final ListNode[] lists) {
        final ListNode dummyHead = new ListNode(0);
        final PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
        for (final ListNode list : lists) {
            if (list != null) {
                q.offer(list);
            }
        }
        ListNode resultTail = dummyHead;
        while (!q.isEmpty()) {
            final ListNode curr = q.poll();
            resultTail.next = new ListNode(curr.val);
            resultTail = resultTail.next;
            if (curr.next != null) {
                q.offer(curr.next);
            }
        }
        return dummyHead.next;
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