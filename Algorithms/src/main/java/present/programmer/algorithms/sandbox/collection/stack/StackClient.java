package present.programmer.algorithms.sandbox.collection.stack;

import java.util.Scanner;

public class StackClient {

    public static void main(final String... args) {
        final Stack<String> stack = new ArrayBasedStack<>();
        interactWith(stack);
    }

    private static void interactWith(final Stack<String> stack) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                processInput(scanner.next(), stack);
            }
        }
    }

    private static void processInput(final String input, final Stack<String> stack) {
        if ("-".equals(input)) {
            System.out.println(stack.pop());
        } else if ("print".equals(input)) {
            print(stack);
        } else if ("size".equals(input)) {
            System.out.println(stack.size());
        } else if ("isEmpty".equals(input)) {
            System.out.println(stack.isEmpty());
        } else if ("iterate".equals(input)) {
            for (final String elem : stack) {
                System.out.println(elem);
            }
        } else {
            stack.push(input);
        }
    }

    private static void print(final Stack<String> stack) {
        for (final String element : stack) {
            System.out.println(element);
        }
    }
}
