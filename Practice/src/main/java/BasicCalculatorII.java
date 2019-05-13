import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Problem #227
 * Time complexity: O(n)
 * Space complexity: O(n)
 **/
public class BasicCalculatorII {

    public int calculate(String s) {
        final List<Integer> operands = new ArrayList<>();
        final Matcher numberMatcher = Pattern.compile("\\d+").matcher(s);
        while (numberMatcher.find()) {
            operands.add(Integer.parseInt(numberMatcher.group()));
        }

        final List<Character> operators = new ArrayList<>();
        final Matcher operatorMatcher = Pattern.compile("[+\\-*/]").matcher(s);
        while (operatorMatcher.find()) {
            operators.add(operatorMatcher.group().charAt(0));
        }

        final Deque<Integer> operandQ = new ArrayDeque<>();
        operandQ.offer(operands.get(0));
        final Deque<Character> operatorQ = new ArrayDeque<>();
        for (int i = 0; i < operators.size(); i++) {
            final char operator = operators.get(i);
            if (operator == '+' || operator == '-') {
                operatorQ.offer(operator);
                operandQ.offer(operands.get(i + 1));
            } else if (operator == '*') {
                operandQ.offerLast(operandQ.pollLast() * operands.get(i + 1));
            } else {
                operandQ.offerLast(operandQ.pollLast() / operands.get(i + 1));
            }
        }
        while (!operatorQ.isEmpty()) {
            final char operator = operatorQ.pollFirst();
            if (operator == '+') {
                operandQ.offerFirst(operandQ.pollFirst() + operandQ.pollFirst());
            } else {
                operandQ.offerFirst(operandQ.pollFirst() - operandQ.pollFirst());
            }
        }
        return operandQ.poll();
    }
    
    public static void main(final String[] args) {
        System.out.println("7 == " + new BasicCalculatorII().calculate("3+2*2"));
        System.out.println("5 == " + new BasicCalculatorII().calculate(" 3+5 / 2 "));
        System.out.println("1 == " + new BasicCalculatorII().calculate(" 3/2 "));
        System.out.println("5 == " + new BasicCalculatorII().calculate(" 5 "));
        System.out.println("1 == " + new BasicCalculatorII().calculate("1-1+1"));
        System.out.println("-2147483647 == " + new BasicCalculatorII().calculate("0-2147483647"));
	}
}