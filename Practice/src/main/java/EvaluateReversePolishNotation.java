import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #150
 * Time complexity: O()
 * Space complexity: O()
 **/
public class EvaluateReversePolishNotation {

    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MULTIPLY = "*";
    private static final String DIVIDE = "/";

    public int evalRPN(final String[] tokens) {
		final Deque<Integer> st = new ArrayDeque<>();
		for (final String token : tokens) {
            final int left;
            final int right;
            switch (token) {
                case PLUS:
                    st.push(st.pop() + st.pop());
                    break;
                case MINUS:
                    right = st.pop();
                    left = st.pop();
                    st.push(left - right);
                    break;
                case MULTIPLY:
                    st.push(st.pop() * st.pop());
                    break;
                case DIVIDE:
                    right = st.pop();
                    left = st.pop();
                    st.push(left / right);
                    break;
                default:
                    st.push(Integer.parseInt(token));
                    break;
            }
        }
        return st.pop();
    }
    
    public static void main(final String[] args) {
        System.out.println("9 == " + new EvaluateReversePolishNotation().evalRPN(
                new String[]{ "2", "1", "+", "3", "*" }
        ));
        System.out.println("6 == " + new EvaluateReversePolishNotation().evalRPN(
                new String[]{ "4", "13", "5", "/", "+" }
        ));
        System.out.println("22 == " + new EvaluateReversePolishNotation().evalRPN(
                new String[]{ "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" }
        ));
	}
}