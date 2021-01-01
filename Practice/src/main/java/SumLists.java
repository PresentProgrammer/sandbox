import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #2.5
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class SumLists {

    public LinkedListNode<Integer> sum(final LinkedListNode<Integer> l1, final LinkedListNode<Integer> l2) {
        final Deque<Integer> s1 = toStack(l1);
        final Deque<Integer> s2 = toStack(l2);
        final Deque<Integer> resultStack = new ArrayDeque<>();
        int rem = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            final int sum = s1.pop() + s2.pop() + rem;
            resultStack.push(sum % 10);
            rem = sum / 10;
        }
        final Deque<Integer> largerStack = s1.isEmpty() ? s2 : s1;
        while (!largerStack.isEmpty()) {
            resultStack.push(largerStack.pop() + rem);
            rem = 0;
        }
        if (rem != 0) {
            resultStack.push(rem);
        }
        return toList(resultStack);
    }

    private static Deque<Integer> toStack(final LinkedListNode<Integer> head) {
        final Deque<Integer> stack = new ArrayDeque<>();
        LinkedListNode<Integer> curr = head;
        while (curr != null && curr.val == 0) {
            curr = curr.next;
        }
        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }
        return stack;
    }

    private static LinkedListNode<Integer> toList(final Deque<Integer> stack) {
        final LinkedListNode<Integer> handle = new LinkedListNode<>(0);
        LinkedListNode<Integer> tail = handle;
        while (!stack.isEmpty()) {
            tail.next = new LinkedListNode<>(stack.pop());
            tail = tail.next;
        }
        return handle.next;
    }

    public static void main(final String[] args) {
        final LinkedListNode<Integer> l1 = new LinkedListNode<>(6);
        LinkedListNode.add(l1, 1);
        LinkedListNode.add(l1, 7);
        final LinkedListNode<Integer> l2 = new LinkedListNode<>(0);
        LinkedListNode.add(l2, 2);
        LinkedListNode.add(l2, 3);
        LinkedListNode.add(l2, 9);
        LinkedListNode.add(l2, 5);
        System.out.println(LinkedListNode.print(new SumLists().sum(l1, l2)));
    }
}