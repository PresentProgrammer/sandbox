package present.programmer.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    public static final int EXPECTED_ELEMENT_COUNT = 10_000;

    public int[] readIntArray(String resourceName) {
        final List<Integer> list = new ArrayList<>(EXPECTED_ELEMENT_COUNT);
        try (Scanner scanner = new Scanner(asInputStream(resourceName))) {
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        }
        return list.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    private InputStream asInputStream(String resourceName) {
        return getClass().getResourceAsStream(resourceName);
    }
}
