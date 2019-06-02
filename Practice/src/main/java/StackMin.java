import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Problem #3.2
 * Time complexity: O(1)
 * Space complexity: O(n)
 **/
public class StackMin {
    
    private final Deque<Integer> elements = new ArrayDeque<>();
    private final Deque<Integer> mins = new ArrayDeque<>();

    public void push(final int val) {
        elements.push(val);
        if (mins.peek() == null || mins.peek() >= val) {
            mins.push(val);
        }
    }

    public Integer pop() {
        final Integer popped = elements.pop();
        if (popped.equals(mins.peek())) {
            mins.pop();
        }
        return popped;
    }

    public Integer min() {
        return mins.peek();
    }
    
    public static void main(final String[] args) {
        final StackMin stackMin = new StackMin();
        System.out.println("null == " + stackMin.min());
        stackMin.push(1);
        stackMin.push(2);
        stackMin.push(3);
        System.out.println("1 == " + stackMin.min());
        stackMin.push(10);
        stackMin.push(0);
        stackMin.push(0);
        System.out.println("0 == " + stackMin.min());
        stackMin.pop();
        System.out.println("0 == " + stackMin.min());
        stackMin.pop();
        System.out.println("1 == " + stackMin.min());
        stackMin.pop();
        stackMin.pop();
        stackMin.pop();
        stackMin.pop();
        System.out.println("null == " + stackMin.min());
        try {
            stackMin.pop();
        } catch (final NoSuchElementException e) {
            System.out.println("Threw NoSuchElementException as expected");
        }
	}
}