import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #155
 * Time complexity: O(1)
 * Space complexity: O(n)
 **/
public class MinStack {

    private final Deque<Integer> elements = new ArrayDeque<>();
    private final Deque<Integer> mins = new ArrayDeque<>();

    public void push(int x) {
        elements.push(x);
        if (mins.isEmpty() || mins.peek() >= x) {
            mins.push(x);
        }
    }

    public void pop() {
        if (mins.peek().equals(elements.pop())) {
            mins.pop();
        }
    }

    public int top() {
        return elements.peek();
    }

    public int getMin() {
        return mins.peek();
    }

    public static void main(final String[] args) {
    }
}