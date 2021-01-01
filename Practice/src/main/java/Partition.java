/**
 * Problem #2.4
 * Time complexity: O(n)
 * Space complexity: O(1)
 **/
public class Partition {

    public LinkedListNode<Integer> partition(final LinkedListNode<Integer> head, final int x) {
        if (head == null) {
            return null;
        }
        LinkedListNode<Integer> lo = head;
        LinkedListNode<Integer> hi = head;
        LinkedListNode<Integer> curr = head.next;
        while (curr != null) {
            final LinkedListNode<Integer> next = curr.next;
            if (curr.val < x) {
                curr.next = lo;
                lo = curr;
            } else {
                hi.next = curr;
                hi = curr;
            }
            curr = next;
        }
        hi.next = null;
        return lo;
    }

    public static void main(final String[] args) {
        LinkedListNode<Integer> head = new LinkedListNode<>(3);
        head = LinkedListNode.add(head, 5);
        head = LinkedListNode.add(head, 8);
        head = LinkedListNode.add(head, 5);
        head = LinkedListNode.add(head, 10);
        head = LinkedListNode.add(head, 2);
        head = LinkedListNode.add(head, 1);
        System.out.println(LinkedListNode.print(new Partition().partition(head, 5)));
        System.out.println(LinkedListNode.print(new Partition().partition(null, 5)));
        System.out.println(LinkedListNode.print(new Partition().partition(new LinkedListNode<>(3), 5)));
        System.out.println(LinkedListNode.print(new Partition().partition(new LinkedListNode<>(5), 5)));
        System.out.println(LinkedListNode.print(new Partition().partition(new LinkedListNode<>(10), 5)));
    }
}