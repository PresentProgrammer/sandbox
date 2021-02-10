import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

/**
 * Problem #394
 * Time complexity: O(N * max K * max Depth)
 * Space complexity: O(N * max K * max Depth)
 **/
public class DecodeString {

	public String decodeString(String str) {
		final Deque<Entry<Integer, StringBuilder>> stack = new ArrayDeque<>();
		stack.push(new SimpleEntry<>(1, new StringBuilder()));
		final char[] s = str.toCharArray();

		int i = 0;
		while (i < s.length) {
			if (Character.isDigit(s[i])) {
				int k = 0;
				while (Character.isDigit(s[i])) {
					k *= 10;
					k += Character.digit(s[i], 10);
					i++;
				}
				if (s[i] != '[') {
					throw new IllegalArgumentException("Expected [ after int");
				}
				stack.push(new SimpleEntry<>(k, new StringBuilder()));
			} else if (s[i] == ']') {
				final Entry<Integer, StringBuilder> top = stack.pop();
				for (int j = 0; j < top.getKey(); j++) {
					stack.peek().getValue().append(top.getValue());
				}
			} else {
				stack.peek().getValue().append(s[i]);
			}
			i++;
		}
		return stack.pop().getValue().toString();
	}

	public static void main(final String[] args) {
		System.out.println("accaccacc == " + new DecodeString().decodeString("3[a2[c]]"));
	}
}