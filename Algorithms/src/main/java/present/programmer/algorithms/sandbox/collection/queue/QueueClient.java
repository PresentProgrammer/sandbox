package present.programmer.algorithms.sandbox.collection.queue;

import java.util.Scanner;

public class QueueClient {

    public static void main(final String... args) {
        final Queue<String> queue = new ArrayBasedQueue<>();
        interactWith(queue);
    }

    private static void interactWith(final Queue<String> queue) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNext()) {
                processInput(scanner, queue);
            }
        }
    }

    private static void processInput(final Scanner scanner, final Queue<String> queue) {
        final String choice = scanner.next();
        if ("enqueue".equals(choice) || "en".equals(choice)) {
            queue.enqueue(scanner.next());
        } else if ("dequeue".equals(choice) || "de".equals(choice)) {
            System.out.println(queue.dequeue());
        } else if ("sample".equals(choice)) {
            System.out.println(queue.sample());
        } else if ("print".equals(choice)) {
            print(queue);
        } else if ("size".equals(choice)) {
            System.out.println(queue.size());
        } else if ("isEmpty".equals(choice)) {
            System.out.println(queue.isEmpty());
        } else if ("toString".equals(choice)) {
            System.out.println(queue.toString());
        }
    }

    private static void print(final Iterable<String> iterable) {
        for (final String element : iterable) {
            System.out.println(element);
        }
    }
}
