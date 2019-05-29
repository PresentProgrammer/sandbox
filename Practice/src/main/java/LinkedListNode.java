/**
 * Implemented in scope of Cracking the Coding Interview course.
 */
public class LinkedListNode<T> {

    public T val;
    public LinkedListNode<T> next;

    public LinkedListNode(T val) {
        this.val = val;
    }

    public static <T> LinkedListNode<T> add(LinkedListNode<T> head, T val) {
        if (head == null) {
            return new LinkedListNode<>(val);
        } else {
            LinkedListNode<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new LinkedListNode<>(val);
            return head;
        }
    }

    public static <T> String print(final LinkedListNode<T> head) {
        final StringBuilder strBuilder = new StringBuilder();
        strBuilder.append('[');
        LinkedListNode<T> curr = head;
        while (curr != null) {
            strBuilder.append(curr.val).append(", ");
            curr = curr.next;
        }
        strBuilder.append(']');
        return strBuilder.toString();
    }
}