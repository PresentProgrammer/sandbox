import java.util.Stack;

/**
 * Problem #20
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class ValidParenthesis {

    public boolean isValid(final String str) {
        final Stack<Character> stack = new Stack<>();
        for (final char ch : str.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (stack.isEmpty()) {
                return false;
            } else {
                final char top = stack.peek();
                if (ch == ')' && top == '(' || ch == '}' && top == '{' || ch == ']' && top == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(final String[] args) {
        System.out.println("true == " + new ValidParenthesis().isValid("()"));
        System.out.println("true == " + new ValidParenthesis().isValid("()[]{}"));
        System.out.println("false == " + new ValidParenthesis().isValid("(]"));
        System.out.println("false == " + new ValidParenthesis().isValid("([)]"));
        System.out.println("true == " + new ValidParenthesis().isValid("{[]}"));
    }
}