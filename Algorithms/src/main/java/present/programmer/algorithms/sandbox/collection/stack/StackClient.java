package present.programmer.algorithms.sandbox.collection.stack;

import java.util.Scanner;

public class StackClient {

    public static void main(final String... args) {
        final ArrayBasedStack<String> stack = new ArrayBasedStack<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                final String input = scanner.next();
                if ("-".equals(input)) {
                    System.out.println(stack.pop() + " ");
                } else if ("print".equals(input)) {
                    for (final String element : stack) {
                        System.out.println(element);
                    }
                } else {
                    stack.push(input);
                }
            }
        }
    }
}
