package present.programmer.algorithms.sandbox.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.currentThread;

public class Inputs {

    private static final String INPUT_FOLDER = "sort";
    private static final String UNSORTED_WORDS = INPUT_FOLDER + "/unsorted-words.txt";
    private static final String UNSORTED_INTEGERS = INPUT_FOLDER + "/unsorted-integers.txt";

    String[] getUnsortedWords() {
        try (final Scanner scanner = getFileScanner(UNSORTED_WORDS)) {
            final List<String> words = new ArrayList<>();
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
            return words.toArray(new String[0]);
        }
    }

    Integer[] getUnsortedIntegers() {
        try (final Scanner scanner = getFileScanner(UNSORTED_INTEGERS)) {
            final List<Integer> integers = new ArrayList<>();
            while (scanner.hasNextInt()) {
                integers.add(scanner.nextInt());
            }
            return integers.toArray(new Integer[0]);
        }
    }

    // Auxiliary Methods

    private static Scanner getFileScanner(final String path) {
        final ClassLoader classLoader = currentThread().getContextClassLoader();
        return new Scanner(classLoader.getResourceAsStream(path));
    }
}
