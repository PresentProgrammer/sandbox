import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SomeTests {

    @Test
    public void m() {
        final String s = " 3+5 / 2 - 47 *22 ";
        final List<Integer> operands = new ArrayList<>();
        final Matcher numberMatcher = Pattern.compile("\\d+").matcher(s);
        while (numberMatcher.find()) {
            operands.add(Integer.parseInt(numberMatcher.group()));
        }
        System.out.println(operands);

        final List<Character> operators = new ArrayList<>();
        final Matcher operatorMatcher = Pattern.compile("[+\\-*/]").matcher(s);
        while (operatorMatcher.find()) {
            operators.add(operatorMatcher.group().charAt(0));
        }
        System.out.println(operators);

        System.out.println(Integer.MIN_VALUE);
    }
}
