import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SomeTests {

    @Test
    public void m() {
        final StringTokenizer tokenizer = new StringTokenizer("[1, null, 2, null]", "[, ]");
        final List<Integer> list = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();
            list.add("null".equals(token) ? null : Integer.parseInt(token));
        }
        System.out.println(list);
    }
}
