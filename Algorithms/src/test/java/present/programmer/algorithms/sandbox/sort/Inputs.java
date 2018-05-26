package present.programmer.algorithms.sandbox.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Thread.currentThread;
import static java.util.stream.Stream.generate;

class Inputs {

    private static final String UNSORTED_WORDS = "sort/unsorted-words.txt";

    String[] getUnsortedWords() {
        try (final Scanner scanner = getScannerOfUnsortedWords()) {
            final List<String> words = new ArrayList<>();
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
            return words.toArray(new String[0]);
        }
    }

    Integer[] getUnsortedIntegers(final int size) {
        final Random random = new Random();
        return generate(() -> random.nextInt(size)).limit(size).toArray(Integer[]::new);
    }

    // Auxiliary Methods

    private static Scanner getScannerOfUnsortedWords() {
        final ClassLoader classLoader = currentThread().getContextClassLoader();
        return new Scanner(classLoader.getResourceAsStream(UNSORTED_WORDS));
    }
}
