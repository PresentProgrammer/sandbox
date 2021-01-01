/**
 * Time: O(n): one pass over n for copying elements, one pass for setting random,
 * and one pass for separating original from copy.
 * Space: O(1): no additional space required.
 **/
@SuppressWarnings("Duplicates")
public class CopyListWithRandomPointer_Interweaving {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        interweaveCopies(head);
        final RandomListNode copyHead = head.next;
        deepCopyRandomReferences(head);
        separateOriginalFromCopy(head);
        return copyHead;
    }

    private void interweaveCopies(final RandomListNode head) {
        RandomListNode curr = head;
        while (curr != null) {
            final RandomListNode currNext = curr.next;
            curr.next = new RandomListNode(curr.label);
            curr.next.next = currNext;
            curr = currNext;
        }
    }

    private void deepCopyRandomReferences(final RandomListNode head) {
        RandomListNode curr = head;
        while (curr != null) {
            curr.next.random = curr.random == null ? null : curr.random.next;
            curr = curr.next.next;
        }
    }

    private void separateOriginalFromCopy(final RandomListNode head) {
        RandomListNode curr = head;
        while (curr != null) {
            final RandomListNode currCopy = curr.next;
            final RandomListNode currNext = currCopy.next;
            currCopy.next = currNext == null ? null : currNext.next;
            curr.next = currNext;
            curr = currNext;
        }
    }

    public static void main(final String... args) {
        final RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        final RandomListNode third = new RandomListNode(3);
        head.random = third;
        head.next.next = third;
        RandomListNode copy = new CopyListWithRandomPointer_Interweaving().copyRandomList(head);
        while (copy != null) {
            System.out.println(copy.label);
            copy = copy.next;
        }
        new CopyListWithRandomPointer_Interweaving().copyRandomList(null);
    }

    private static class RandomListNode {

        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }
}