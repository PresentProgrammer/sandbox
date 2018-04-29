package present.programmer.algorithms.sandbox.collection.stack;

import java.util.Scanner;

public class FixedCapacityStackClient {

    private static final int SIZE = 10;

    public static void main(final String... args) {
        final FixedCapacityStack<String> stack = new FixedCapacityStack<>(SIZE);
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                final String input = scanner.next();
                if ("-".equals(input)) {
                    System.out.println(stack.pop() + " ");
                } else {
                    stack.push(input);
                }
            }
        }
    }
}
