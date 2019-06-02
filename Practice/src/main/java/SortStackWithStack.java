import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #3.5
 * Time complexity: O(n ^ 2)
 * Space complexity: O(n)
 **/
public class SortStackWithStack {
    
    public void sort(final Deque<Integer> stack) {
		final Deque<Integer> auxStack = new ArrayDeque<>();
		while (!stack.isEmpty()) {
		    final Integer curr = stack.pop();
		    while (auxStack.peek() != null && auxStack.peek() > curr) {
		        stack.push(auxStack.pop());
            }
		    auxStack.push(curr);
        }
		while (!auxStack.isEmpty()) {
		    stack.push(auxStack.pop());
        }
    }
    
    public static void main(final String[] args) {
        final Deque<Integer> stack1 = new ArrayDeque<>();
        stack1.push(10);
        stack1.push(2);
        stack1.push(3);
        stack1.push(1);
        new SortStackWithStack().sort(stack1);
        System.out.println(stack1);

        final Deque<Integer> stack2 = new ArrayDeque<>();
        new SortStackWithStack().sort(stack2);
        System.out.println(stack2);
	}
}